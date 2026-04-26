package com.pragma.ms_report.domain.usecase;

import com.pragma.ms_report.domain.api.IBootcampReportServicePort;
import com.pragma.ms_report.domain.model.BootcampReport;
import com.pragma.ms_report.domain.model.BootcampReportDetail;
import com.pragma.ms_report.domain.model.PersonInfo;
import com.pragma.ms_report.domain.spi.IBootcampReportPersistencePort;
import com.pragma.ms_report.domain.spi.IPersonClientPort;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public class BootcampReportUseCase implements IBootcampReportServicePort {

    private final IBootcampReportPersistencePort bootcampReportPersistencePort;
    private final IPersonClientPort personClientPort;

    public BootcampReportUseCase(IBootcampReportPersistencePort bootcampReportPersistencePort,
                                 IPersonClientPort personClientPort) {
        this.bootcampReportPersistencePort = bootcampReportPersistencePort;
        this.personClientPort = personClientPort;
    }

    @Override
    public Mono<BootcampReport> save(BootcampReport bootcampReport) {
        bootcampReport.setPersonCount(0);
        bootcampReport.setCreatedAt(LocalDateTime.now());
        bootcampReport.setCapacityCount(
                bootcampReport.getCapacities() != null
                        ? bootcampReport.getCapacities().size()
                        : 0
        );
        bootcampReport.setTechnologyCount(
                bootcampReport.getCapacities() != null
                        ? bootcampReport.getCapacities().stream()
                          .mapToInt(c -> c.getTechnologies() != null
                                         ? c.getTechnologies().size() : 0)
                          .sum()
                        : 0
        );
        return bootcampReportPersistencePort.save(bootcampReport);
    }

    @Override
    public Mono<BootcampReport> incrementPersonCount(Long bootcampId) {
        return bootcampReportPersistencePort.findByBootcampId(bootcampId)
                .flatMap(report -> {
                    report.setPersonCount(report.getPersonCount() + 1);
                    return bootcampReportPersistencePort.save(report);
                });
    }

    @Override
    public Mono<BootcampReportDetail> findMostPopular() {
        return bootcampReportPersistencePort.findTopByOrderByPersonCountDesc()
                .flatMap(report ->
                        personClientPort.findEnrolledPersonsByBootcampId(report.getBootcampId())
                                .collectList()
                                .map(persons -> toDetail(report, persons))
                );
    }

    private BootcampReportDetail toDetail(BootcampReport report, List<PersonInfo> persons) {
        BootcampReportDetail detail = new BootcampReportDetail();
        detail.setId(report.getId());
        detail.setBootcampId(report.getBootcampId());
        detail.setBootcampName(report.getBootcampName());
        detail.setBootcampDescription(report.getBootcampDescription());
        detail.setLaunchDate(report.getLaunchDate());
        detail.setDurationMonths(report.getDurationMonths());
        detail.setCapacityCount(report.getCapacityCount());
        detail.setTechnologyCount(report.getTechnologyCount());
        detail.setPersonCount(report.getPersonCount());
        detail.setCapacities(report.getCapacities());
        detail.setPersons(persons);
        detail.setCreatedAt(report.getCreatedAt());
        return detail;
    }
}
