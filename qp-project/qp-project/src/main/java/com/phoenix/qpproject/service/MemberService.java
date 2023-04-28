package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    SqlSession sqlSession;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MembersDTO login(String memberId, String memberPw) {
        return memberMapper.login(memberId, memberPw);
    }
//    public MembersDTO getUserById(String memberId) {
//        return memberMapper.getUserById(memberId);
//    }

    public List<MembersDTO> getMemberList(){
        List<MembersDTO> memberList = memberMapper.getMemberList();
        return memberList;
    }
    @Transactional
    public void addMember(MembersDTO member) {
        memberMapper.addMember(member);
    }

    public Integer checkMemberById(String memberId) {
        return memberMapper.checkMemberById(memberId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        MembersDTO userVo = memberMapper.getUserAccount(username);
        if (userVo == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return (UserDetails) userVo;
    }
}
