package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService memberService;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit( HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("user");
        System.out.println(qpUser);

        MembersDTO member = (MembersDTO) qpUser;
        System.out.println(member.getMemberGender());

        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in. get register page %%");
            return "/pages/authentication/card/login";
        }
        else {
            model.addAttribute("user", qpUser);
            return "/pages/user/settings.html";
        }

    }
}
