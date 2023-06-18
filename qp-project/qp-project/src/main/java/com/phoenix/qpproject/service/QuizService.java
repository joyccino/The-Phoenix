package com.phoenix.qpproject.service;

import com.phoenix.qpproject.dto.*;
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

    public List<QuizzesDTO> getQuizListByMember(){
        List<QuizzesDTO> quizList = quizMapper.getQuizListByMember();
        return quizList;
    }

    public void quizDelete(int quizID) {
        quizMapper.quizDelete(quizID);
    }

    public List<HistoryDTO> getHistoryOfUser(int qId, int userId){
        return quizMapper.getHistoryOfUser(qId,userId);
    }


    public QuizDetailDTO getQuizDetail(int qid) {
        return quizMapper.getQuizDetail(qid);
    }

    public List<QuizzesDTO> getQuizList(){
        List<QuizzesDTO> quizList = quizMapper.getQuizList();
        return quizList;
    }

    public QuizzesDTO getQuiz(int qid){
        return quizMapper.getQuiz(qid);
    }


    public void addQuiz(QuizzesDTO quiz) {
        quizMapper.addQuiz(quiz);
    }

    public int getRecentQuizIdOfMember(int memberId){
        return quizMapper.getRecentQuizIdOfMember(memberId);
    }

    public void addQuestion(QuestionsDTO question){
        quizMapper.addQuestion(question);
    }

    public int getRecentQuestionIdOfQuiz(int memberId){
        return quizMapper.getRecentQuestionIdOfQuiz(memberId);
    }

    public void addOptions(QuestionOptionsDTO qo){
        quizMapper.addOptions(qo);
    }
    public List<QuestionsDTO> getQsWhereQuizId(int quizId){
        return quizMapper.getQsWhereQuizId(quizId);
    }

//    public void addQuizHistory(QuizHistoryDTO quizHistoryDTO){
//        quizMapper.addQuizHistory(quizHistoryDTO);
//    };

    public int getRecentQuizHistoryIdOfMember(int memberId){
        return quizMapper.getRecentQuizHistoryIdOfMember(memberId);
    }

    public void addQH(QuizHistoryDTO qh){
        quizMapper.addQH(qh);
    }

    public void addQuestionHistory(QuestionsHistoryDTO questionsHistoryDTO){
        quizMapper.addQuestionHistory(questionsHistoryDTO);
    }

    public List<HomeDTO> getQuizListForHome(){
        return quizMapper.getQuizListForHome();
    }

    public void updateTotalQuestions(int newTotal, int quizId){
        quizMapper.updateTotalQuestions(newTotal, quizId);
    }

    public void updateTotalExaminee(int quizId) {
        quizMapper.updateTotalExaminee(quizId);
    }

    public List<ResultsDTO> getQuestionOptionsByQuizId(int quizId){
        return quizMapper.getQuestionOptionsByQuizId(quizId);
    }

    public void updateUserScore(int grade, int quizHistoryId) {
        System.out.println("grade: "+grade);
        quizMapper.updateUserScore(grade, quizHistoryId);
    }

    public void questionDelete(int questionId) {
        quizMapper.questionDelete(questionId);
    }

    public void questionOptionDelete(int questionId) {quizMapper.questionOptionDelete(questionId);}


    public int getAverageScore(int quizId){
        return quizMapper.getAverageScore(quizId);
    }


    public void updateAverageScore(int averageScore, int quizId){
        quizMapper.updateAverageScore(averageScore, quizId);
    }


    public List<HomeDTO> getMainQuizList(String orderBy){
        List<HomeDTO> mainQuizList = quizMapper.getMainQuizList(orderBy);
        return mainQuizList;
    }

    public String getOptionByQuestionId(int questionId){
        return quizMapper.getOptionByQuestionId(questionId);
    }

    public void updateQuestion(QuestionsDTO question){
        quizMapper.updateQuestion(question);
    }
    public void updateQuestionOption(QuestionOptionsDTO questionOption){
        quizMapper.updateQuestionOption(questionOption);
    }
}
