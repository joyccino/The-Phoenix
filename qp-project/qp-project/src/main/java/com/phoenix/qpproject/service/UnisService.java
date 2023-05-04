package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.UnisDTO;
import com.phoenix.qpproject.mapper.UnisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnisService {
    @Autowired
    private UnisMapper unisMapper;

    public List<UnisDTO> uniList(){
        List<UnisDTO> uniList = unisMapper.getUniList();
        return uniList;
    }
    public UnisDTO getUniByDomain(String unisDomain){
        UnisDTO uni = unisMapper.getUniByDomain(unisDomain);
        return uni;
    }

}
