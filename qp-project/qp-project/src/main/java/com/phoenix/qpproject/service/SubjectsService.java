package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.SubjectsDTO;
import com.phoenix.qpproject.mapper.SubjectsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {

    @Autowired
    private SubjectsMapper subjectsMapper;

    public List<SubjectsDTO> getSubjectList(){
        List<SubjectsDTO> subjectList = subjectsMapper.getSubjectList();
        return subjectList;
    }
}
