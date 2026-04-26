package com.pragma.ms_report.infrastructure.config;

import com.pragma.ms_report.domain.api.IBootcampReportServicePort;
import com.pragma.ms_report.domain.spi.IBootcampReportPersistencePort;
import com.pragma.ms_report.domain.spi.IPersonClientPort;
import com.pragma.ms_report.domain.usecase.BootcampReportUseCase;
import com.pragma.ms_report.infrastructure.out.adapter.BootcampReportPersistenceAdapter;
import com.pragma.ms_report.infrastructure.out.http.PersonWebClientAdapter;
import com.pragma.ms_report.infrastructure.out.mapper.IBootcampReportDocumentMapper;
import com.pragma.ms_report.infrastructure.out.repository.BootcampReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final BootcampReportRepository bootcampReportRepository;
    private final IBootcampReportDocumentMapper documentMapper;

    @Bean
    public WebClient personWebClient(@Value("${clients.person.url}") String personUrl) {
        return WebClient.builder()
                .baseUrl(personUrl)
                .build();
    }

    @Bean
    public IPersonClientPort personClientPort(WebClient personWebClient) {
        return new PersonWebClientAdapter(personWebClient);
    }

    @Bean
    public IBootcampReportPersistencePort bootcampReportPersistencePort() {
        return new BootcampReportPersistenceAdapter(bootcampReportRepository, documentMapper);
    }

    @Bean
    public IBootcampReportServicePort bootcampReportServicePort(IPersonClientPort personClientPort) {
        return new BootcampReportUseCase(bootcampReportPersistencePort(), personClientPort);
    }
}
