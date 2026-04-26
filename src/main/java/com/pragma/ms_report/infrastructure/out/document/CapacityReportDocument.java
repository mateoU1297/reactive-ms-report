package com.pragma.ms_report.infrastructure.out.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapacityReportDocument {
    private Long id;
    private String name;
    private List<TechnologyReportDocument> technologies;
}
