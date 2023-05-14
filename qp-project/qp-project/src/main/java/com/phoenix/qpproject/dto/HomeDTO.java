package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class HomeDTO {
    // quizId from quizzes
    private int homeQuizId;
    // 응시여부
    private int homeIsTaken;
    // 등록날짜 from quizzes
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date homePostedDate;
    // 출제자 아이디 from members
    private String homeCreatorId;
    // 과목명 from subjects
    private String homeDetailSubject;
    // 문제 제목 from quizzes
    private String homeQuizTitle;
    // 문제 수
    private int homeQuestionTotal;
    // 응시 횟수
    private int homeTotalExaminee;
    // 정답률
    private int correctedRate;
}