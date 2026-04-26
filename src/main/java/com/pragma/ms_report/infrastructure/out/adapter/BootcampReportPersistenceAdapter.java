package com.pragma.ms_report.infrastructure.out.adapter;

import com.pragma.ms_report.domain.model.BootcampReport;
import com.pragma.ms_report.domain.spi.IBootcampReportPersistencePort;
import com.pragma.ms_report.infrastructure.out.mapper.IBootcampReportDocumentMapper;
import com.pragma.ms_report.infrastructure.out.repository.BootcampReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampReportPersistenceAdapter implements IBootcampReportPersistencePort {

    private final BootcampReportRepository bootcampReportRepository;
    private final IBootcampReportDocumentMapper mapper;

    @Override
    public Mono<BootcampReport> save(BootcampReport report) {
        return bootcampReportRepository.save(mapper.toDocument(report))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<BootcampReport> findByBootcampId(Long bootcampId) {
        return bootcampReportRepository.findByBootcampId(bootcampId)
                .map(mapper::toDomain);
    }
}
