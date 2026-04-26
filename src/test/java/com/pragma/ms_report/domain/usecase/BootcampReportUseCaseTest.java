package com.pragma.ms_report.domain.usecase;

import com.pragma.ms_report.domain.model.BootcampReport;
import com.pragma.ms_report.domain.model.CapacityReport;
import com.pragma.ms_report.domain.model.PersonInfo;
import com.pragma.ms_report.domain.model.TechnologyReport;
import com.pragma.ms_report.domain.spi.IBootcampReportPersistencePort;
import com.pragma.ms_report.domain.spi.IPersonClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampReportUseCaseTest {

    @Mock
    private IBootcampReportPersistencePort bootcampReportPersistencePort;

    @Mock
    private IPersonClientPort personClientPort;

    @InjectMocks
    private BootcampReportUseCase bootcampReportUseCase;

    private BootcampReport bootcampReport;

    @BeforeEach
    void setUp() {
        List<TechnologyReport> technologies = List.of(
                new TechnologyReport(1L, "Java"),
                new TechnologyReport(2L, "Spring")
        );
        List<CapacityReport> capacities = List.of(
                new CapacityReport(1L, "Backend", technologies),
                new CapacityReport(2L, "Frontend", List.of(
                        new TechnologyReport(3L, "React")
                ))
        );
        bootcampReport = new BootcampReport(
                null, 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                1, 2, 3, capacities, LocalDateTime.now()
        );
    }

    @Test
    void save_validReport_success() {
        BootcampReport saved = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 0, bootcampReport.getCapacities(), LocalDateTime.now()
        );
        when(bootcampReportPersistencePort.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(bootcampReportUseCase.save(bootcampReport))
                .expectNextMatches(r ->
                        r.getCapacityCount().equals(2) &&
                                r.getTechnologyCount().equals(3) &&
                                r.getPersonCount().equals(0)
                )
                .verifyComplete();
    }

    @Test
    void save_calculatesCapacityCount() {
        BootcampReport saved = new BootcampReport(
                "id", 1L, "Java Bootcamp", "Desc",
                LocalDate.now(), 3, 2, 3, 0,
                bootcampReport.getCapacities(), LocalDateTime.now()
        );
        when(bootcampReportPersistencePort.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(bootcampReportUseCase.save(bootcampReport))
                .expectNextMatches(r -> r.getCapacityCount() == 2)
                .verifyComplete();
    }

    @Test
    void save_calculatesTechnologyCount() {
        BootcampReport saved = new BootcampReport(
                "id", 1L, "Java Bootcamp", "Desc",
                LocalDate.now(), 3, 2, 3, 0,
                bootcampReport.getCapacities(), LocalDateTime.now()
        );
        when(bootcampReportPersistencePort.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(bootcampReportUseCase.save(bootcampReport))
                .expectNextMatches(r -> r.getTechnologyCount() == 3)
                .verifyComplete();
    }

    @Test
    void save_personCountStartsAtZero() {
        BootcampReport saved = new BootcampReport(
                "id", 1L, "Java Bootcamp", "Desc",
                LocalDate.now(), 3, 2, 3, 0,
                bootcampReport.getCapacities(), LocalDateTime.now()
        );
        when(bootcampReportPersistencePort.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(bootcampReportUseCase.save(bootcampReport))
                .expectNextMatches(r -> r.getPersonCount() == 0)
                .verifyComplete();
    }

    @Test
    void save_nullCapacities_countsZero() {
        bootcampReport.setCapacities(null);
        BootcampReport saved = new BootcampReport(
                "id", 1L, "Java Bootcamp", "Desc",
                LocalDate.now(), 3, 0, 0, 0, null, LocalDateTime.now()
        );
        when(bootcampReportPersistencePort.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(bootcampReportUseCase.save(bootcampReport))
                .expectNextMatches(r ->
                        r.getCapacityCount() == 0 &&
                                r.getTechnologyCount() == 0
                )
                .verifyComplete();
    }

    @Test
    void incrementPersonCount_existingReport_success() {
        BootcampReport existing = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 0, bootcampReport.getCapacities(), LocalDateTime.now()
        );
        BootcampReport updated = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 1, bootcampReport.getCapacities(), LocalDateTime.now()
        );

        when(bootcampReportPersistencePort.findByBootcampId(1L))
                .thenReturn(Mono.just(existing));
        when(bootcampReportPersistencePort.save(any()))
                .thenReturn(Mono.just(updated));

        StepVerifier.create(bootcampReportUseCase.incrementPersonCount(1L))
                .expectNextMatches(r -> r.getPersonCount() == 1)
                .verifyComplete();
    }

    @Test
    void incrementPersonCount_incrementsFromZero() {
        BootcampReport existing = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 0, bootcampReport.getCapacities(), LocalDateTime.now()
        );
        BootcampReport updated = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 1, bootcampReport.getCapacities(), LocalDateTime.now()
        );

        when(bootcampReportPersistencePort.findByBootcampId(1L))
                .thenReturn(Mono.just(existing));
        when(bootcampReportPersistencePort.save(any()))
                .thenReturn(Mono.just(updated));

        StepVerifier.create(bootcampReportUseCase.incrementPersonCount(1L))
                .expectNextMatches(r -> r.getPersonCount().equals(1))
                .verifyComplete();
    }

    @Test
    void incrementPersonCount_incrementsFromExistingValue() {
        BootcampReport existing = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 4, bootcampReport.getCapacities(), LocalDateTime.now()
        );
        BootcampReport updated = new BootcampReport(
                "mongo-id", 1L, "Java Bootcamp", "Description",
                LocalDate.of(2026, 6, 1), 3,
                2, 3, 5, bootcampReport.getCapacities(), LocalDateTime.now()
        );

        when(bootcampReportPersistencePort.findByBootcampId(1L))
                .thenReturn(Mono.just(existing));
        when(bootcampReportPersistencePort.save(any()))
                .thenReturn(Mono.just(updated));

        StepVerifier.create(bootcampReportUseCase.incrementPersonCount(1L))
                .expectNextMatches(r -> r.getPersonCount().equals(5))
                .verifyComplete();
    }

    @Test
    void findMostPopular_success() {
        List<PersonInfo> persons = List.of(
                new PersonInfo(1L, "John", "john@example.com"),
                new PersonInfo(2L, "Jane", "jane@example.com")
        );

        when(bootcampReportPersistencePort.findTopByOrderByPersonCountDesc()).thenReturn(Mono.just(bootcampReport));
        when(personClientPort.findEnrolledPersonsByBootcampId(1L)).thenReturn(Flux.fromIterable(persons));

        StepVerifier.create(bootcampReportUseCase.findMostPopular())
                .expectNextMatches(detail ->
                        detail.getBootcampName().equals("Java Bootcamp") &&
                                detail.getPersons().size() == 2 &&
                                detail.getPersonCount() == 3
                )
                .verifyComplete();
    }

    @Test
    void findMostPopular_noReports_completesEmpty() {
        when(bootcampReportPersistencePort.findTopByOrderByPersonCountDesc())
                .thenReturn(Mono.empty());

        StepVerifier.create(bootcampReportUseCase.findMostPopular())
                .verifyComplete();
    }

    @Test
    void findMostPopular_personsListEmptyWhenNoEnrollments() {
        when(bootcampReportPersistencePort.findTopByOrderByPersonCountDesc()).thenReturn(Mono.just(bootcampReport));
        when(personClientPort.findEnrolledPersonsByBootcampId(1L)).thenReturn(Flux.empty());

        StepVerifier.create(bootcampReportUseCase.findMostPopular())
                .expectNextMatches(detail ->
                        detail.getPersons() != null &&
                                detail.getPersons().isEmpty()
                )
                .verifyComplete();
    }
}
