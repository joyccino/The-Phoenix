package com.phoenix.qpproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "createQuiz", method = RequestMethod.GET)
    public String test() {
        return "/pages/quiz/quiz_form";
    }

}
