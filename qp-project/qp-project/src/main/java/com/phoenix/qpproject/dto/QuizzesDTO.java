package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class QuizzesDTO {
    private int quizzesId;
    private int quizzesMemberId;
    private String quizzesTitle;
    private int quizzesSubjectId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date quizzesQuizCreateDateTime;
    private boolean quizzesShuffleQuestions;
    private boolean quizzesShuffleOptions;
    private boolean quizzesAllowedAttempts;
    private int quizzesTotalQuestions;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date quizzesExamStartDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date quizzesExamEndDateTime;
    private int quizzesTotalExaminee;
    private int quizzesAverageScore;
    private QuizHistoryDTO quizHistoryDTO;
    private SubjectsDTO subjectsDTO;
    private MembersDTO membersDTO;

}