package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.MemberDTO;
import com.phoenix.qpproject.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public MemberService memberService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "/pages/authentication/card/login";
    }

    @RequestMapping(value = "authentication/register", method = RequestMethod.GET)
    public String registerDefault() {
        return "/pages/authentication/card/register";
    }

    @RequestMapping(value = "authentication/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword() {
        return "/pages/authentication/card/forgot-password";
    }

    @PostMapping("/authentication/addMember")
    public String addMember(MemberDTO member) {
        memberService.addMember(member);
        return "redirect:/";
    }
    @PostMapping("/memberLogin")
    public String memberLogin(MemberDTO member, Model model) {
        log.info("로그인폼에서 입력받은 데이터: {}", member.getMemberId());
        
        // id 비교
        int memberCount = memberService.findMemberById(member.getMemberId());
        System.out.println("조회된 멤버수: " + memberCount);

        if (memberCount > 0) {
            // pw 비교

            // admin 여부 확인

            // recent visit 기록

            return "redirect:/memberList";
        }
        else {
            String msg = "아이디 또는 비밀번호를 확인해주세요.";
            model.addAttribute("msgLoginFailed",msg);
            return "/pages/authentication/card/login";
        }

    }
}