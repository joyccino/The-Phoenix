package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MemberDTO> getMemberList();
    //회원 가입
    public int addMember(MemberDTO member);

    public int checkMemberById(String findById);

    //public int checkMemberByEmail(String findById);
}
