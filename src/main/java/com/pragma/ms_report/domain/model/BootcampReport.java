package com.pragma.ms_report.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BootcampReport {
    private String id;
    private Long bootcampId;
    private String bootcampName;
    private String bootcampDescription;
    private LocalDate launchDate;
    private Integer durationMonths;
    private Integer capacityCount;
    private Integer technologyCount;
    private Integer personCount;
    private List<CapacityReport> capacities;
    private LocalDateTime createdAt;

    public BootcampReport() {
    }

    public BootcampReport(String id, Long bootcampId, String bootcampName, String bootcampDescription,
                          LocalDate launchDate, Integer durationMonths, Integer capacityCount, Integer technologyCount,
                          Integer personCount, List<CapacityReport> capacities, LocalDateTime createdAt) {
        this.id = id;
        this.bootcampId = bootcampId;
        this.bootcampName = bootcampName;
        this.bootcampDescription = bootcampDescription;
        this.launchDate = launchDate;
        this.durationMonths = durationMonths;
        this.capacityCount = capacityCount;
        this.technologyCount = technologyCount;
        this.personCount = personCount;
        this.createdAt = createdAt;
        this.capacities = capacities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBootcampId() {
        return bootcampId;
    }

    public void setBootcampId(Long bootcampId) {
        this.bootcampId = bootcampId;
    }

    public String getBootcampName() {
        return bootcampName;
    }

    public void setBootcampName(String bootcampName) {
        this.bootcampName = bootcampName;
    }

    public String getBootcampDescription() {
        return bootcampDescription;
    }

    public void setBootcampDescription(String bootcampDescription) {
        this.bootcampDescription = bootcampDescription;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public Integer getCapacityCount() {
        return capacityCount;
    }

    public void setCapacityCount(Integer capacityCount) {
        this.capacityCount = capacityCount;
    }

    public Integer getTechnologyCount() {
        return technologyCount;
    }

    public void setTechnologyCount(Integer technologyCount) {
        this.technologyCount = technologyCount;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public List<CapacityReport> getCapacities() {
        return capacities;
    }

    public void setCapacities(List<CapacityReport> capacities) {
        this.capacities = capacities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
