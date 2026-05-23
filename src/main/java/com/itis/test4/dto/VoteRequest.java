package com.itis.test4.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long optionId;
}