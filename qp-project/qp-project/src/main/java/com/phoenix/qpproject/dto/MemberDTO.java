package com.phoenix.qpproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

public class MemberDTO {
    private int Id;
    private String memberId;
    private String memberEmail;
    private String memberSurname;
    private String memberFirstname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBDate;
    private Date memberRegDate;
    private int memberGender;
    private int memberMemberTypeId;
    private int memberInstitutionId;
    private Date memberMembershipDate;
    private String memberCountry;
    private String memberCity;
    private int memberMajorId;
    private int memberGrade;
    private String memberPw;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberSurname() {
        return memberSurname;
    }

    public void setMemberSurname(String memberSurname) {
        this.memberSurname = memberSurname;
    }

    public String getMemberFirstname() {
        return memberFirstname;
    }

    public void setMemberFirstname(String memberFirstname) {
        this.memberFirstname = memberFirstname;
    }

    public Date getMemberBDate() {
        return memberBDate;
    }

    public void setMemberBDate(Date memberBDate) {
        this.memberBDate = memberBDate;
    }

    public Date getMemberRegDate() {
        return memberRegDate;
    }

    public void setMemberRegDate(Date memberRegDate) {
        this.memberRegDate = memberRegDate;
    }

    public int getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(int memberGender) {
        this.memberGender = memberGender;
    }

    public int getMemberMemberTypeId() {
        return memberMemberTypeId;
    }

    public void setMemberMemberTypeId(int memberMemberTypeId) {
        this.memberMemberTypeId = memberMemberTypeId;
    }

    public int getMemberInstitutionId() {
        return memberInstitutionId;
    }

    public void setMemberInstitutionId(int memberInstitutionId) {
        this.memberInstitutionId = memberInstitutionId;
    }

    public Date getMemberMembershipDate() {
        return memberMembershipDate;
    }

    public void setMemberMembershipDate(Date memberMembershipDate) {
        this.memberMembershipDate = memberMembershipDate;
    }

    public String getMemberCountry() {
        return memberCountry;
    }

    public void setMemberCountry(String memberCountry) {
        this.memberCountry = memberCountry;
    }

    public String getMemberCity() {
        return memberCity;
    }

    public void setMemberCity(String memberCity) {
        this.memberCity = memberCity;
    }

    public int getMemberMajorId() {
        return memberMajorId;
    }

    public void setMemberMajorId(int memberMajorId) {
        this.memberMajorId = memberMajorId;
    }

    public int getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(int memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }
}