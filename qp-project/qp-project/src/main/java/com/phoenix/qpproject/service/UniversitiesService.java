package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.UniversitiesDTO;
import com.phoenix.qpproject.mapper.UniversitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversitiesService {
    @Autowired
    private UniversitiesMapper unisMapper;

    public List<UniversitiesDTO> uniList(){
        List<UniversitiesDTO> uniList = unisMapper.getUniList();
        return uniList;
    }
    public UniversitiesDTO getUniByDomain(String unisDomain){
        UniversitiesDTO uni = unisMapper.getUniByDomain(unisDomain);
        return uni;
    }

}
