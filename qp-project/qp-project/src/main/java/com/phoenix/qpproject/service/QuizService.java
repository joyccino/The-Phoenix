package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.MemberDTO;
import com.phoenix.qpproject.dto.QuizDTO;
import com.phoenix.qpproject.mapper.MemberMapper;
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

    public List<QuizDTO> getQuizList(){
        List<QuizDTO> quizList = quizMapper.getQuizList();
        return quizList;
    }

}
