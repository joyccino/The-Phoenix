package com.phoenix.qpproject.dao;

import com.phoenix.qpproject.dto.MemberDTO;

import java.util.List;

public interface MemberDAO {
    List<MemberDTO> getMembers();
}
