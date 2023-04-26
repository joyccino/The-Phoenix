package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.QuizzesDTO;
import com.phoenix.qpproject.mapper.QuizMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizService {

    //DI 의존성 주입 생성자 메서드 주입방식
    private QuizMapper quizMapper;

    public QuizService(QuizMapper quizMapper) {
        this.quizMapper = quizMapper;
    }

    public List<QuizzesDTO> getQuizList(){
        List<QuizzesDTO> quizList = quizMapper.getQuizList();
        return quizList;
    }

}
