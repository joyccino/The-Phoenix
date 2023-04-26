package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class MemberService {

    //DI 의존성 주입 생성자 메서드 주입방식
    private MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<MembersDTO> getMemberList(){
        List<MembersDTO> memberList = memberMapper.getMemberList();
        return memberList;
    }

    public void addMember(MembersDTO member) {
        memberMapper.addMember(member);
    }

    public Integer checkMemberById(String memberId) {
        return memberMapper.checkMemberById(memberId);
    }

}
