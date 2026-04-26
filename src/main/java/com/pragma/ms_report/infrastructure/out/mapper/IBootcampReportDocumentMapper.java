package com.pragma.ms_report.infrastructure.out.mapper;

import com.pragma.ms_report.domain.model.BootcampReport;
import com.pragma.ms_report.infrastructure.out.document.BootcampReportDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBootcampReportDocumentMapper {
    BootcampReportDocument toDocument(BootcampReport report);
    BootcampReport toDomain(BootcampReportDocument document);
}