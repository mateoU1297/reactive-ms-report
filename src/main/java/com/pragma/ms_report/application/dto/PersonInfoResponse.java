package com.pragma.ms_report.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfoResponse {
    private Long id;
    private String name;
    private String email;
}
