package com.pragma.ms_report.domain.model;

import java.util.List;

public class CapacityReport {
    private Long id;
    private String name;
    private List<TechnologyReport> technologies;

    public CapacityReport() {
    }

    public CapacityReport(Long id, String name, List<TechnologyReport> technologies) {
        this.id = id;
        this.name = name;
        this.technologies = technologies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TechnologyReport> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyReport> technologies) {
        this.technologies = technologies;
    }
}
