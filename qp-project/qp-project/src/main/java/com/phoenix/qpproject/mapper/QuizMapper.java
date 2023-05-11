package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.QuestionOptionsDTO;
import com.phoenix.qpproject.dto.QuestionsDTO;
import com.phoenix.qpproject.dto.QuizzesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuizMapper {

    public List<QuizzesDTO> getQuizListByMember();

    public List<QuizzesDTO> getQuizList();

    public QuizzesDTO getQuiz(int qid);

    public int findQuizById(String findById);

    public void addQuiz(QuizzesDTO quiz);

    public int getRecentQuizIdOfMember(int memberId);

    public void addQuestion(QuestionsDTO question);

    public int getRecentQuestionIdOfQuiz(int quizId);

    public void addOptions(QuestionOptionsDTO qo);
    public List<QuestionsDTO> getQsWhereQuizId(int quizId);
}
