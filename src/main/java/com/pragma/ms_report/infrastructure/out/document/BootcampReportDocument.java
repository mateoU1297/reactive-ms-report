package com.pragma.ms_report.infrastructure.out.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bootcamp_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampReportDocument {

    @Id
    private String id;
    private Long bootcampId;
    private String bootcampName;
    private String bootcampDescription;
    private LocalDate launchDate;
    private Integer durationMonths;
    private Integer capacityCount;
    private Integer technologyCount;
    private Integer personCount;
    private List<CapacityReportDocument> capacities;
    private LocalDateTime createdAt;
}
