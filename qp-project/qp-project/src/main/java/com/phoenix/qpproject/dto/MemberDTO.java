package com.phoenix.qpproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberDTO {
    private int id;
    private String Email;
    private String SurName;
    private String LastName;
    private Date BirthDate;
    private Date RegisterDate;
    private Boolean Gender;
    private int MemberTypeId;
    private int InstitutionId;
    private Date MemberExpirationDate;
    private String Country;
    private String City;
    private int MajorId;
    private int Grade;
    private String Password;

}
