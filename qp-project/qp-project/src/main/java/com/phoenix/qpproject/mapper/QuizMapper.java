package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.MemberDTO;
import com.phoenix.qpproject.dto.QuizDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuizMapper {

    public List<QuizDTO> getQuizList();

    public int findQuizById(String findById);

}
