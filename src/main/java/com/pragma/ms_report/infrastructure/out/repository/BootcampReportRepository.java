package com.pragma.ms_report.infrastructure.out.repository;

import com.pragma.ms_report.infrastructure.out.document.BootcampReportDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BootcampReportRepository extends ReactiveMongoRepository<BootcampReportDocument, String> {
    Mono<BootcampReportDocument> findByBootcampId(Long bootcampId);

    Mono<BootcampReportDocument> findTopByOrderByPersonCountDesc();
}
