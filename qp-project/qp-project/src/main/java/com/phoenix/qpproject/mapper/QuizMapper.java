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

    public void quizDelete(int quizID);

    public List<HomeDTO> getQuizListForHome();

    public QuizDetailDTO getQuizDetail(int qid);

    public void updateTotalQuestions(int newTotal, int quizId);

    public void updateTotalExaminee(int quizId);

    public List<ResultsDTO> getQuestionOptionsByQuizId(int quizId);

    public void updateUserScore(int grade, int quizHistoryId);

    public void updateAverageScore(int averageScore, int quizId);

    public int getAverageScore(int quizId);

    List<HomeDTO> getMainQuizList(String orderBy);
    //유진 아이디(나중에추가-풀었는지여부를 서브쿼리날려야함), 정렬 조건
}
