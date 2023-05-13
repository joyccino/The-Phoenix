package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.*;
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
    public void addQuizHistory(QuizHistoryDTO quizHistoryDTO);

    public int getRecentQuizHistoryIdOfMember(int memberId);

    public void addQuestionHistory(QuestionsHistoryDTO questionsHistoryDTO);

    public List<HomeDTO> getQuizListForHome();
}
