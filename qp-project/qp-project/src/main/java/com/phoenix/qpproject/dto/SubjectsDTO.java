package com.phoenix.qpproject.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SubjectsDTO {

    private int subjectId;
    private String subjectInstitutionLevel;
    private int subjectYear;
    private String subjectGeneralSubject;
    private String subjectDetailSubject;
    private String subjectSubjectCode;

}
