package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.*;
import com.phoenix.qpproject.service.QuizService;
import com.phoenix.qpproject.service.SubjectsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public QuizService quizService;
    @Autowired
    public SubjectsService subjectsService;

    @RequestMapping(value = "createQuiz", method = RequestMethod.GET)
    public String test() {
        return "/pages/quiz/quiz_form";
    }

    @RequestMapping(value = "createQuiz1", method = RequestMethod.GET)
    public String test1() {
        return "/pages/quiz/quizFrom";
    }

    @PostMapping(value="generateQuiz")
    @ResponseBody
    public void generateQuiz(@ModelAttribute QuizzesDTO quiz, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");

        MembersDTO member = (MembersDTO) qpUser;

        System.out.println("넘겨온 퀴즈 "+quiz.getQuizzesTitle());

        quiz.setQuizzesMemberId(member.getId());

        quizService.addQuiz(quiz);

        System.out.println("퀴즈 생성 성공");
        
        // 세션에 퀴즈 아이디 포함한 정보 추가 (해당 멤버가 가장 최근에 추가한 quiz 가져오기);

        int generatedQuizId = quizService.getRecentQuizIdOfMember(member.getId());

        session.setAttribute("quizId", generatedQuizId);
//
        session.setMaxInactiveInterval(-1);
    }
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String quizsetting(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        Object unSavedQuiz = session.getAttribute("quizId");
        if(!ObjectUtils.isEmpty(unSavedQuiz)) {
            session.removeAttribute("quizId");
        }

        MembersDTO member = (MembersDTO) qpUser;
        List<SubjectsDTO> generalSubjectList = subjectsService.getGeneralSubjectList();
        System.out.println("조회된 과목들 수: "+generalSubjectList.size());
        model.addAttribute("generalSubjectList", generalSubjectList);
        model.addAttribute("title", "새 퀴즈 만들기");
        model.addAttribute("sub", "퀴즈 정보를 작성한 뒤 '퀴즈 생성하기' 버튼을 클릭해주세요.");
        return "/pages/quiz/create";
    }


    @PostMapping(value="getDetailSubjects")
    @ResponseBody
    public List<SubjectsDTO> getDetailSubjects(@RequestParam("selectedGeneralSubject") String selectedGeneralSubject, Model model) {
        List<SubjectsDTO> detailSubjectList = subjectsService.getDetailSubjectList(selectedGeneralSubject);
        System.out.println("조회된 세부 과목들 수: "+detailSubjectList.size());
        System.out.println("넘겨온값 " + selectedGeneralSubject);
        //model.addAttribute("detailSubjectList", detailSubjectList);
        return detailSubjectList;
    }
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String quizDetail(){
        return "/pages/quiz/quizDetailsTest";
    }



//유진
    @RequestMapping(value = "quizList", method = RequestMethod.GET)
    public String quizList( Model model, HttpServletRequest request, @RequestParam(value = "s", required = false) String sortValue) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        System.out.println("sortValue");
        //select 값으로 변수 저장
        String orderBy = "quizCreateDateTime DESC";
        // n-new최신순  a-averageScore 평균정답률순   e-totalExaminee 응시자순
        if ("n".equals(sortValue)) orderBy = "quizCreateDateTime DESC";
        if ("a".equals(sortValue)) orderBy = "averageScore DESC";
        if ("e".equals(sortValue)) orderBy = "totalExaminee DESC";

        
        System.out.println("퀴즈리스트 호출");
        List<HomeDTO> quizList = quizService.getMainQuizList(orderBy);

        System.out.println("퀴즈 목록을 요청합니다: "+quizList.toString());
        model.addAttribute("title", "퀴즈목록조회");
        model.addAttribute("quizList", quizList);


        return "/pages/quiz/quiz_list";

    }


    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String quizDashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }

        List<QuizzesDTO> quizList = quizService.getQuizListByMember();

        System.out.println("퀴즈 목록을 요청합니다: "+quizList.toString());

        model.addAttribute("title", "퀴즈목록조회");
        model.addAttribute("quizList", quizList);

        return "/quizDashboard";
    }
    @RequestMapping(value = "take/{quizzesId}", method = RequestMethod.GET)
    public String takeExam(@PathVariable("quizzesId") int quizzesId, Model model){
        System.out.println("넘겨온 quizID:"+quizzesId);

        // 응시할 퀴즈 로딩해오기
        QuizzesDTO quiz = quizService.getQuiz(quizzesId);
        int quizId = quiz.getQuizzesId();

        List<QuestionsDTO> questions = quizService.getQsWhereQuizId(quizId);

        model.addAttribute("quiz",quiz);

        model.addAttribute("questions", questions);

        model.addAttribute("title", "퀴즈 생성하기");
        model.addAttribute("sub", "퀴즈 정보를 작성한 뒤 '퀴즈 생성하기' 버튼을 클릭해주세요.");

        // 응시할 questions 로딩해오기
        return "/pages/quiz/quiz";
    }

    @RequestMapping(value = "edit/{quizzesId}", method = RequestMethod.GET)
    public String editExam(@PathVariable("quizzesId") int quizzesId, Model model){
        System.out.println("넘겨온 quizID:"+quizzesId);

        // 응시할 퀴즈 로딩해오기
        QuizzesDTO quiz = quizService.getQuiz(quizzesId);
        int quizId = quiz.getQuizzesId();

        List<QuestionsDTO> questions = quizService.getQsWhereQuizId(quizId);

        List<ResultsDTO> options = quizService.getQuestionOptionsByQuizId(quiz.getQuizzesId());

        model.addAttribute("quiz",quiz);

        model.addAttribute("questions", questions);

        model.addAttribute("options", options);

        model.addAttribute("title", "퀴즈 수정하기");
        model.addAttribute("sub", "퀴즈 정보를 수정해주세요.");


        return "/pages/quiz/create";
    }



    @RequestMapping(value = "quizDetails", method = RequestMethod.GET)
    public String quizDetailsTest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        else {
            return "/pages/quiz/quizDetailsTest";
        }
    }
    @PostMapping(value = "addQuestion")
    @ResponseBody
    public List<QuestionsDTO> addQuestion(@ModelAttribute QODTO question_info, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");

        MembersDTO member = (MembersDTO) qpUser; // 멤버

        int quizId = (int)session.getAttribute("quizId");

        System.out.println("퀴즈아이디 "+quizId);
        System.out.println("que? "+question_info.getQuestion());

        QuestionsDTO newQuestion = new QuestionsDTO();
        newQuestion.setQuestionsQuizId(quizId);
        newQuestion.setQuestionsQuestion(question_info.getQuestion());
        newQuestion.setQuestionQuestionType(question_info.getQuestionType());

        quizService.addQuestion(newQuestion);

        int generatedQuestionId = quizService.getRecentQuestionIdOfQuiz(quizId);

        QuestionOptionsDTO qo = new QuestionOptionsDTO();
        qo.setQuestionOptionQuestionId(generatedQuestionId);
        qo.setQuestionOptionIsAnswer(true);
        qo.setQuestionOptionOptionContent(question_info.getOption());

        quizService.addOptions(qo);

        System.out.println("완성");

        List<QuestionsDTO> qlist = quizService.getQsWhereQuizId(quizId);

        System.out.println("qlist: "+qlist.size());

        // quiz 의 totalQuestion update
        quizService.updateTotalQuestions(qlist.size(), quizId);

        return qlist;
    }

    @PostMapping("/loadDetail")
    @ResponseBody
    public QuizDetailDTO loadDetail(int qId, HttpServletRequest request) {
        System.out.println("넘겨받은 퀴즈 아이디: "+qId);
        // 응시할 퀴즈 로딩해오기
        QuizDetailDTO quizDetail = quizService.getQuizDetail(qId);

        // 수정 자격 있는지?
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        MembersDTO user = (MembersDTO)qpUser;
        String userId = user.getMemberId();
        System.out.println("퀴즈디테일1:" + quizDetail);

        System.out.println("로그인한 사용자:"+userId+"게시자:"+quizDetail.getCreatorId());

        if (userId.equals(quizDetail.getCreatorId()) || user.getMemberMemberTypeId() == 0) {
            System.out.println("일치!");
            quizDetail.setCanEdit(true);
        }
        else {
            quizDetail.setCanEdit(false);
        }

        System.out.println("퀴즈디테일2:" + quizDetail);

        return quizDetail;
    }

    @PostMapping("/loadHistory")
    @ResponseBody
    public List<HistoryDTO> loadHistory(int qId, HttpServletRequest request) {
        System.out.println("넘겨받은 퀴즈 아이디: "+qId);
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        MembersDTO user = (MembersDTO)qpUser;
        int userId = user.getId();
        // 응시할 퀴즈 로딩해오기
        List<HistoryDTO> quizHistory = quizService.getHistoryOfUser(qId, userId);

        System.out.println("퀴즈히스토리:" + quizHistory);

        return quizHistory;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String quizList2(HttpServletRequest request,  Model model) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        else {
            List<QuizzesDTO> qList = quizService.getQuizList();
            List<HomeDTO> quizList = quizService.getQuizListForHome();

            model.addAttribute("quizList",quizList);
            //System.out.println("퀴즈리스트2 호출:"+quizList.size());
            return "/pages/quiz/home-backup";
            //return "/pages/quiz/quiz_list";
        }
    }

    @RequestMapping(value = "remove/{quizzesId}", method = RequestMethod.GET)
    public String quizDelete(@PathVariable("quizzesId") int quizzesId, Model model){
        System.out.println("넘겨온 quizID:"+quizzesId);

        // 선택한 퀴즈 삭제
        quizService.quizDelete(quizzesId);
        
        System.out.println("삭제 완료");

        // 응시할 questions 로딩해오기
        return "redirect:/quiz/home-backup";
    }

    @PostMapping(value="submit")
    @ResponseBody
    public List<ResultsDTO> submitQuiz(@RequestBody UserResponseDTO userResponse, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        System.out.println("response: "+userResponse);
        MembersDTO member = (MembersDTO) qpUser;

        //quiz history 추가
        QuizHistoryDTO quizHistory = new QuizHistoryDTO();
        quizHistory.setQuizHistoryMemberId(member.getId());
        quizHistory.setQuizHistoryQuizId(userResponse.getQuizId());
        quizHistory.setQuizHistoryStartDateTime(userResponse.getStartTime());
        quizHistory.setQuizHistoryEndDateTime(userResponse.getEndTime());
        quizHistory.setQuizHistoryGrade(userResponse.getAchievement());

        System.out.println("qh grade: "+quizHistory.getQuizHistoryGrade());
        quizService.addQH(quizHistory);

        //recently inserted quizHistoryId;
        int quizHistoryId = quizService.getRecentQuizHistoryIdOfMember(member.getId());

        for (int i = 0; i < userResponse.getResponses().size(); i++) {
            //question history 추가
            QuestionsHistoryDTO questionsHistory = new QuestionsHistoryDTO();
            questionsHistory.setQuestionsHistoryQuizHistoryId(quizHistoryId);
            questionsHistory.setQuestionsHistoryQuestionId((int) userResponse.getQuestionIds().get(i));
            questionsHistory.setQuestionsHistoryUserResponse((String) userResponse.getResponses().get(i));
            questionsHistory.setQuestionsHistoryIsCorrected(false); // 구현 x
            quizService.addQuestionHistory(questionsHistory);
        }
        System.out.println("adding quiz history + questionHistories done");

        // total examinee update
        quizService.updateTotalExaminee(userResponse.getQuizId());

        // get original answers
        List<ResultsDTO> results = quizService.getQuestionOptionsByQuizId(userResponse.getQuizId());

        // get user response
        List<String> userResponseList = userResponse.getResponses();

        int corrects = 0;

        for(int i = 0; i < results.size(); i++ ) {
            String originalR = results.get(i).getResultOptionContent();
            String userR = userResponseList.get(i);
            if (new String(originalR).equals(userR)) {
                corrects ++;
            }
        }

        int rLen = results.size();
        int result = 100 * corrects / rLen;

        System.out.println("corrects:"+corrects+" rLen:"+rLen+" result:"+result);

        quizService.updateUserScore(result, quizHistoryId);

        // average score update
        int quizAvg = quizService.getAverageScore(userResponse.getQuizId());

        quizService.updateAverageScore(quizAvg,userResponse.getQuizId());
        System.out.println("done 3");

        return results;
    }
}