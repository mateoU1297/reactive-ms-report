package com.pragma.ms_report.infrastructure.out.http;

import com.pragma.ms_report.domain.model.PersonInfo;
import com.pragma.ms_report.domain.spi.IPersonClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class PersonWebClientAdapter implements IPersonClientPort {

    private final WebClient webClient;

    @Override
    public Flux<PersonInfo> findEnrolledPersonsByBootcampId(Long bootcampId) {
        return webClient.get()
                .uri("/api/v1/persons/enrollments/{bootcampId}", bootcampId)
                .retrieve()
                .bodyToFlux(PersonInfo.class);
    }
}
