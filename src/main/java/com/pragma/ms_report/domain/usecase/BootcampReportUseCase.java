package com.pragma.ms_report.domain.usecase;

import com.pragma.ms_report.domain.api.IBootcampReportServicePort;
import com.pragma.ms_report.domain.model.BootcampReport;
import com.pragma.ms_report.domain.spi.IBootcampReportPersistencePort;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class BootcampReportUseCase implements IBootcampReportServicePort {

    private final IBootcampReportPersistencePort bootcampReportPersistencePort;

    public BootcampReportUseCase(IBootcampReportPersistencePort bootcampReportPersistencePort) {
        this.bootcampReportPersistencePort = bootcampReportPersistencePort;
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
}
