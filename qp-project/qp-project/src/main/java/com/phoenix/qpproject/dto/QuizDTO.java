package com.phoenix.qpproject.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class QuizDTO {
    private int Id;
    private String quizId;
    private String quizTitle;
    private String quizSubject;
    private String memberId;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuizSubject() {
        return quizSubject;
    }

    public void setQuizSubject(String quizSubject) {
        this.quizSubject = quizSubject;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizId() { return quizId; }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getMemberId() { return memberId; }

    public void setMemberId(String memberId) { this.memberId = memberId; }
}