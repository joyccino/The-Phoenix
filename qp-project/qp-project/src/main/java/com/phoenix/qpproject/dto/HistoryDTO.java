package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class HistoryDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date historySubmittedDate;
    private int historyGrade;
}
