package com.phoenix.qpproject.dto;

import lombok.Data;

@Data
public class QuestionsHistoryDTO {
    private int questionsHistoryId;
    private int questionsHistoryQuizHistoryId;
    private int questionsHistoryQuestionId;
    private String questionsHistoryUserResponse;
    private boolean questionsHistoryIsCorrected;
}
