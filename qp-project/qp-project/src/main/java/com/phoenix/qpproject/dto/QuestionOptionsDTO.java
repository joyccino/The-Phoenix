package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QuestionOptionsDTO {
    private int questionOptionId;
    private int questionOptionQuestionId;
    private String questionOptionOptionContent;
    private boolean questionOptionIsAnswer;

}
