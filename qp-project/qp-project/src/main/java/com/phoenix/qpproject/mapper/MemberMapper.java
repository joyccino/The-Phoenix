package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.VisitHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface MemberMapper {

    // Select
    public MembersDTO getUserAccount(String memberId);
    public MembersDTO login(MembersDTO member);
    public List<MembersDTO> getMemberList();
    public int checkMemberById(String findById);

    public int checkMemberByEmail(String findByEmail);

    public MembersDTO checkMemberByUUId(String uuid);

    // Insert
    public void addMember(MembersDTO member);
    public void addVisitHistory(int mId);


    // Update
    public  void passReset(String resetPass, String memberEmail);
    public void memberDeactivateByUserId(String memberId);
    public void memberVerify(String memberUUId);
    public void memberInfoUpdate(MembersDTO member);

    public int getRecentQuizIdOfMember(int memberId);

}
