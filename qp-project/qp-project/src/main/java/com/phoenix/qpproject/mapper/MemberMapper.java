package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MembersDTO> getMemberList();
    //회원 가입
    public int addMember(MembersDTO member);

    public int checkMemberById(String findById);

    //public int checkMemberByEmail(String findById);
}
