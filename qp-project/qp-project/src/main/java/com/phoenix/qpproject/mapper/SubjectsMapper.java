package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.SubjectsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectsMapper {
    public List<SubjectsDTO> getSubjectList();
}
