package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.VisitHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface MemberMapper {

    public MembersDTO login(MembersDTO member);
    public MembersDTO getUserAccount(String memberId);

    public List<MembersDTO> getMemberList();
    //회원 가입
    public int addMember(MembersDTO member);

    public int addVisitHistory(int mId);

    public int checkMemberById(String findById);

    public int checkMemberByEmail(String findByEmail);

    public  void passReset(String resetPass, String memberEmail);

    public MembersDTO checkMemberByUUId(String uuid);

    public void memberDeactivateByUserId(String memberId);

    public void memberVerify(String memberUUId);


}
