package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QuizHistoryDTO {

    private int quizHistoryId;
    private int quizHistoryMemberId;
    private int quizHistoryQuizId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date quizHistoryStartDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date quizHistoryEndDateTime;
    private int quizHistoryGrade;

}
