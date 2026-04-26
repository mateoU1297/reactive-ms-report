package com.pragma.ms_report.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampReportDetailResponse {
    private String id;
    private Long bootcampId;
    private String bootcampName;
    private String bootcampDescription;
    private LocalDate launchDate;
    private Integer durationMonths;
    private Integer capacityCount;
    private Integer technologyCount;
    private Integer personCount;
    private List<CapacityReportResponse> capacities;
    private List<PersonInfoResponse> persons;
    private LocalDateTime createdAt;
}
