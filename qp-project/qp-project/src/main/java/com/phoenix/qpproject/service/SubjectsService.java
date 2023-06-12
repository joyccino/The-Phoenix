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

    public List<SubjectsDTO> getGeneralSubjectList(){
        List<SubjectsDTO> generalSubjectList = subjectsMapper.getGeneralSubjectList();
        return generalSubjectList;
    }

    public List<SubjectsDTO> getDetailSubjectList(String selectedGeneralSubject){
        List<SubjectsDTO> detailSubjectList = subjectsMapper.getDetailSubjectList(selectedGeneralSubject);
        return detailSubjectList;
    }

    public List<SubjectsDTO> filterGeneralSubjectList(String institutionLevel){
        List<SubjectsDTO> generalSubjectList = subjectsMapper.filterGeneralSubjectList(institutionLevel);
        return generalSubjectList;
    }
}
