package com.pragma.ms_report.domain.api;

import com.pragma.ms_report.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface IBootcampReportServicePort {
    Mono<BootcampReport> save(BootcampReport bootcampReport);

    Mono<BootcampReport> incrementPersonCount(Long bootcampId);
}
