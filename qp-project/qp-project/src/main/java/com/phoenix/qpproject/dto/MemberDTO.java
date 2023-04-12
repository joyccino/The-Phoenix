package com.phoenix.qpproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

public class MemberDTO {
    private int memberId;
    private String memberEmail;
    private String memberSurname;
    private String memberLastname;
    private Date memberBDate;
    private Date memberRegDate;
    private Boolean memberGender;
    private int memberMemberTypeId;
    private int memberInstitutionId;
    private Date memberMembershipDate;
    private String memberCountry;
    private String memberCity;
    private int memberMajorId;
    private int memberGrade;
    private String memberPw;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
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

    public String getMemberLastname() {
        return memberLastname;
    }

    public void setMemberLastname(String memberLastname) {
        this.memberLastname = memberLastname;
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

    public Boolean getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(Boolean memberGender) {
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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberId=" + memberId +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberSurname='" + memberSurname + '\'' +
                ", memberLastname='" + memberLastname + '\'' +
                ", memberBDate=" + memberBDate +
                ", memberRegDate=" + memberRegDate +
                ", memberGender=" + memberGender +
                ", memberMemberTypeId=" + memberMemberTypeId +
                ", memberInstitutionId=" + memberInstitutionId +
                ", memberMembershipDate=" + memberMembershipDate +
                ", memberCountry='" + memberCountry + '\'' +
                ", memberCity='" + memberCity + '\'' +
                ", memberMajorId=" + memberMajorId +
                ", memberGrade=" + memberGrade +
                ", memberPw='" + memberPw + '\'' +
                '}';
    }
}
