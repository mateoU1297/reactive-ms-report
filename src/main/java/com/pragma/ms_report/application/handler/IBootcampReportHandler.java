package com.pragma.ms_report.application.handler;

import com.pragma.ms_report.application.dto.BootcampReportRequest;
import com.pragma.ms_report.application.dto.BootcampReportResponse;
import reactor.core.publisher.Mono;

public interface IBootcampReportHandler {
    Mono<BootcampReportResponse> save(BootcampReportRequest request);

    Mono<BootcampReportResponse> incrementPersonCount(Long bootcampId);
}
