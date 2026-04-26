package com.pragma.ms_report.application.handler.impl;

import com.pragma.ms_report.application.dto.BootcampReportRequest;
import com.pragma.ms_report.application.dto.BootcampReportResponse;
import com.pragma.ms_report.application.handler.IBootcampReportHandler;
import com.pragma.ms_report.application.mapper.IBootcampReportMapper;
import com.pragma.ms_report.domain.api.IBootcampReportServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BootcampReportHandlerImpl implements IBootcampReportHandler {

    private final IBootcampReportServicePort bootcampReportServicePort;
    private final IBootcampReportMapper bootcampReportMapper;

    @Override
    public Mono<BootcampReportResponse> save(BootcampReportRequest request) {
        return bootcampReportServicePort.save(bootcampReportMapper.toDomain(request))
                .map(bootcampReportMapper::toResponse);
    }

    @Override
    public Mono<BootcampReportResponse> incrementPersonCount(Long bootcampId) {
        return bootcampReportServicePort.incrementPersonCount(bootcampId)
                .map(bootcampReportMapper::toResponse);
    }
}
