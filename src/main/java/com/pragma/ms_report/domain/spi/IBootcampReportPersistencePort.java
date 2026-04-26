package com.pragma.ms_report.domain.spi;

import com.pragma.ms_report.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface IBootcampReportPersistencePort {
    Mono<BootcampReport> save(BootcampReport bootcampReport);

    Mono<BootcampReport> findByBootcampId(Long bootcampId);
}