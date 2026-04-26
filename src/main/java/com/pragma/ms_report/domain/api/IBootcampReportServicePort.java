package com.pragma.ms_report.domain.api;

import com.pragma.ms_report.domain.model.BootcampReport;
import com.pragma.ms_report.domain.model.BootcampReportDetail;
import reactor.core.publisher.Mono;

public interface IBootcampReportServicePort {
    Mono<BootcampReport> save(BootcampReport bootcampReport);

    Mono<BootcampReport> incrementPersonCount(Long bootcampId);

    Mono<BootcampReportDetail> findMostPopular();
}
