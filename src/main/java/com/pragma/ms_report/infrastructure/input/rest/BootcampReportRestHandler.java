package com.pragma.ms_report.infrastructure.input.rest;

import com.pragma.ms_report.application.dto.BootcampReportRequest;
import com.pragma.ms_report.application.handler.IBootcampReportHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BootcampReportRestHandler {

    private final IBootcampReportHandler bootcampReportHandler;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(BootcampReportRequest.class)
                .flatMap(bootcampReportHandler::save)
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> incrementPersonCount(ServerRequest request) {
        Long bootcampId = Long.parseLong(request.pathVariable("bootcampId"));
        return bootcampReportHandler.incrementPersonCount(bootcampId)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> findMostPopular(ServerRequest request) {
        return bootcampReportHandler.findMostPopular()
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
