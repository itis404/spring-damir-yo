package com.itis.test4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PollResultDto {
    private String option;
    private Long votes;
    private Double percentage;
}