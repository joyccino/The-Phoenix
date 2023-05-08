package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.VisitHistoryDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    SqlSession sqlSession;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MembersDTO getUserAccount(String memberId) {

        MembersDTO member = memberMapper.getUserAccount(memberId);

        return member;
    }

    public MembersDTO memberLogin(MembersDTO member) {
        String memberId = member.getMemberId();

        System.out.println("mid is: "+memberId);

        MembersDTO mDTO = memberMapper.getUserAccount(memberId);

        Boolean passBool = passwordEncoder.matches(member.getMemberPw(), mDTO.getMemberPw());

        System.out.println("패스워드 입력받은: "+member.getMemberPw());
        System.out.println("패스워드 저장된: "+mDTO.getMemberPw());
        System.out.println("패스워드 일치 여부: "+passBool);

        if (mDTO != null && passwordEncoder.matches(member.getMemberPw(), mDTO.getMemberPw())) {
            return mDTO;
        }
        else {
            System.out.println("패스워드 틀림");
            MembersDTO emptyMember = new MembersDTO();
            return emptyMember;
        }
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

    public void addVisitHistory(int mId) {
        memberMapper.addVisitHistory(mId);
    }

    public Integer checkMemberById(String memberId) {
        return memberMapper.checkMemberById(memberId);
    }

    public Integer checkMemberByEmail(String memberEmail) {
        return memberMapper.checkMemberByEmail(memberEmail);
    }

    public void resetPass(String newPassword, String memberEmail) { memberMapper.passReset(newPassword, memberEmail); }

    public MembersDTO checkMemberByUUId(String uuid) {
        MembersDTO member = memberMapper.checkMemberByUUId(uuid);
        return member;
    }

    public void memberDeactivateByUserId(String memberId){
        memberMapper.memberDeactivateByUserId(memberId);
    }


    public void memberVerify(String memberUUId){
        memberMapper.memberVerify(memberUUId);
    };

    public void memberInfoUpdate(MembersDTO member){
        memberMapper.memberInfoUpdate(member);
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
