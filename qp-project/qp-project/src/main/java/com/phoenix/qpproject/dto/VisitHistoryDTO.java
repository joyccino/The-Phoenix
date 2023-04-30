package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class VisitHistoryDTO {
    private int visitHistoryId;
    private int visitHistoryMemberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date visitHistoryVisitDateTime;
    private MembersDTO membersDTO;
}
