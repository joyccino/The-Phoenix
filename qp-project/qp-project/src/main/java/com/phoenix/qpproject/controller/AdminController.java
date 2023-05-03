package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public MemberService memberService;



    /**
    @PostMapping("/authentication/memberLogin")
    public String memberLogin(MemberDTO member, Model model) {
        log.info("로그인폼에서 입력받은 데이터: {}", member.getMemberId());


        // id 비교
        int memberCount = memberService.findMemberById(member.getMemberId());
        System.out.println("조회된 멤버수: " + memberCount);

        if (memberCount > 0) {
            // pw 비교

            // admin 여부 확인

            // recent visit 기록

            return "redirect:/admin/memberList";
        }
        else {
            String msg = "아이디 또는 비밀번호를 확인해주세요.";
            model.addAttribute("msgLoginFailed",msg);
            return "/pages/authentication/card/login";
        }

    }
     **/
}