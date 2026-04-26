package com.pragma.ms_report.domain.spi;

import com.pragma.ms_report.domain.model.PersonInfo;
import reactor.core.publisher.Flux;

public interface IPersonClientPort {
    Flux<PersonInfo> findEnrolledPersonsByBootcampId(Long bootcampId);
}
