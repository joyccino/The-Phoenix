package com.phoenix.qpproject.dto;

import lombok.Data;

@Data
public class UniversitiesDTO {
    private int UId;
    private String universitiesSchoolSystem;
    private String universitiesSchoolName;
    private String universitiesMainOrBranch;
    private String universitiesProvince;
    private String universitiesCity;
    private String universitiesFoundation;
    private int universitiesTotalFaculty;
    private int universitiesTotalStudents;
    private int universitiesTotalFemales;
    private int universitiesTotalMales;
    private String universitiesWebsiteDomain;
}
