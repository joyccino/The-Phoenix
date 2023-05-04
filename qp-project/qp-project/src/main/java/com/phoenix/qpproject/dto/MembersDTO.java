package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class MembersDTO {
    private int Id;
    private String memberId;
    private String memberEmail;
    private String memberSurname;
    private String memberFirstname;
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
    private Boolean memberIsReported;
    private Boolean memberIsBlocked;
    private Boolean memberIsRemoved;
    private Date memberIsReportedDateTime;
    private Date memberIsBlockedDateTime;
    private Date memberIsRemovedDateTime;
    private int memberReportedMemberId;
}