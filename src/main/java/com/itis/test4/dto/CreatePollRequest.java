package com.itis.test4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreatePollRequest {
    @NotBlank
    @Size(min = 5, max = 200)
    private String question;

    @NotEmpty
    private List<String> options;
}