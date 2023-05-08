package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.QuizzesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuizMapper {

    public List<QuizzesDTO> getQuizList();

    public int findQuizById(String findById);

    public void addQuiz(QuizzesDTO quiz);
}
