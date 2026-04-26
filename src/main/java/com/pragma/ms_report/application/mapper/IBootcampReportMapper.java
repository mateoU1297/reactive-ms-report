package com.pragma.ms_report.application.mapper;

import com.pragma.ms_report.application.dto.BootcampReportRequest;
import com.pragma.ms_report.application.dto.BootcampReportResponse;
import com.pragma.ms_report.domain.model.BootcampReport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBootcampReportMapper {
    BootcampReport toDomain(BootcampReportRequest request);
    BootcampReportResponse toResponse(BootcampReport report);
}
