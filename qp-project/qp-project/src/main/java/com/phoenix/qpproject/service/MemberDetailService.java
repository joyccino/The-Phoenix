package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MemberDetail;
import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailService implements UserDetailsService {
    @Autowired
    MemberMapper userRepository;
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        MembersDTO user = userRepository.getUserAccount(memberId);
        if (user != null) {
            return new MemberDetail(user);
        }
        return null;
    }
}
