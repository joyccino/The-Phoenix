package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QuizDetailDTO {
    // quizId from quizzes
    private int quizzesId;
    // quiz title from quizzes
    private String quizzesTitle;
    // average score from quizzes
    private int averageScore;
    // 전체문제수 from quizzes
    private int totalQuestions;
    // 등록날짜 from quizzes
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedDate;

    // 학교 레벨 (subjects)
    private String educationalLevel;
    // 학년 (subjects)
    private int year;
    // general subject (subjects)
    private String generalSubject;
    // detail subject (subjects)
    private String detailSubject;
    // tags
//    private String tags;

    // 출제자 아이디 from members
    private String creatorId;
    // canEdit?
    private Boolean canEdit;
}
