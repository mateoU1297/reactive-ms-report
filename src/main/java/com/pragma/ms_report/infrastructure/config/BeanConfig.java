package com.pragma.ms_report.infrastructure.config;

import com.pragma.ms_report.domain.api.IBootcampReportServicePort;
import com.pragma.ms_report.domain.spi.IBootcampReportPersistencePort;
import com.pragma.ms_report.domain.usecase.BootcampReportUseCase;
import com.pragma.ms_report.infrastructure.out.adapter.BootcampReportPersistenceAdapter;
import com.pragma.ms_report.infrastructure.out.mapper.IBootcampReportDocumentMapper;
import com.pragma.ms_report.infrastructure.out.repository.BootcampReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final BootcampReportRepository bootcampReportRepository;
    private final IBootcampReportDocumentMapper documentMapper;

    @Bean
    public IBootcampReportPersistencePort bootcampReportPersistencePort() {
        return new BootcampReportPersistenceAdapter(bootcampReportRepository, documentMapper);
    }

    @Bean
    public IBootcampReportServicePort bootcampReportServicePort() {
        return new BootcampReportUseCase(bootcampReportPersistencePort());
    }
}
