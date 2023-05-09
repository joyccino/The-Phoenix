package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QuestionsDTO {
    private int questionsId;
    private int questionsQuizId;
    private String questionsQuestion;
    private String questionQuestionType;
}
