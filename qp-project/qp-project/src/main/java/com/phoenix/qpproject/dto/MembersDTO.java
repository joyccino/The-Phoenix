package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
public class MembersDTO {
    private int Id;
    private String memberId;
    private String memberEmail;
    private String memberEmailIsChanged;
    private String memberSurname;
    private String memberFirstname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBDate;
    private Date memberRegDate;
    private Date memberVisit;
    private int memberGender;
    private int memberMemberTypeId;
    private int memberInstitutionId;
    private Date memberMembershipDate;
    private String memberCountry;
    private String memberCity;
    private int memberMajorId;
    private int memberGrade;
    private String memberPw;
    private boolean memberIsReported;
    private boolean memberIsBlocked;
    private boolean memberIsRemoved;
    private Date memberIsReportedDateTime;
    private Date memberIsBlockedDateTime;
    private Date memberIsRemovedDateTime;
    private int memberReportedMemberId;
    private String memberUUId;
}