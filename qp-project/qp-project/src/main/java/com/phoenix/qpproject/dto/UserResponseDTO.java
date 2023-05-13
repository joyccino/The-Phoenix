package com.phoenix.qpproject.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponseDTO {
    private String quizId;
    private Date startTime;
    private Date endTime;
    private List questionIds;
    private List responses;
}
