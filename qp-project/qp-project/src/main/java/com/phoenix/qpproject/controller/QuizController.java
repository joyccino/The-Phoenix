package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.QuizzesDTO;
import com.phoenix.qpproject.dto.SubjectsDTO;
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

import java.util.List;

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
        Object qpUser = session.getAttribute("qpUser");

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
        Object qpUser = session.getAttribute("qpUser");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        MembersDTO member = (MembersDTO) qpUser;
        List<SubjectsDTO> generalSubjectList = subjectsService.getGeneralSubjectList();
        System.out.println("조회된 과목들 수: "+generalSubjectList.size());
        model.addAttribute("generalSubjectList", generalSubjectList);
        return "/pages/quiz/quiz_setting";
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


    @RequestMapping(value = "quizList", method = RequestMethod.GET)
    public String quizList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("qpUser");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        else {
            System.out.println("퀴즈리스트 호출");
            return "/pages/quiz/quiz_list";
        }
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String quizDashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("qpUser");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }

        List<QuizzesDTO> quizList = quizService.getQuizList();

        System.out.println("퀴즈 목록을 요청합니다: "+quizList.toString());

        model.addAttribute("title", "퀴즈목록조회");
        model.addAttribute("quizList", quizList);

        return "/quizDashboard";
    }

    @RequestMapping(value = "quizDetails", method = RequestMethod.GET)
    public String quizDetailsTest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("qpUser");
        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        else {
            return "/pages/quiz/quizDetailsTest";
        }
    }
}