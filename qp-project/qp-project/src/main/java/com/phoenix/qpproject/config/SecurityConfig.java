package com.phoenix.qpproject.config;

import com.phoenix.qpproject.dto.MemberDetail;
import com.phoenix.qpproject.service.MemberService;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.lang.reflect.Member;

//@Configuration
@Configuration
//@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/auth/**", "/resources/**", "/vendors/**", "/assets/**", "/favicon/**", "/quiz/**", "/survey/**", "/mypage/**", "/admin/**").permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                                .requestMatchers("/quiz/quizList").hasRole("USER")
//                                .requestMatchers("/quiz/quizList").hasRole("ADMIN")

                ).formLogin().loginPage("/auth/login").permitAll(); // 기본 로그인 페이지
//
        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService).passwordEncoder(new BCryptPasswordEncoder());
//    }



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .requestMatchers("/quiz/**", "/survey/**", "/authentication/**", "/resources/**", "/vendors/**", "/assets/**", "/favicon/**").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
//                    .anyRequest().authenticated()
//                    .and()
//                    .csrf().disable()
//                .formLogin()
//                    .loginPage("/authentication/login") // 기본 로그인 페이지
//                    .loginProcessingUrl("/authentication/login")
//                    .failureUrl("/authentication/login?error=true")
//                    .usernameParameter("username") // 아이디 파라미터명 설정 (기본값은 "username")
//                    .passwordParameter("password") // 비밀번호 파라미터명 설정 (기본값은 "password")
//                    .defaultSuccessUrl("/admin/memberList")
//                    .permitAll()
//                    .and()
//                .logout(logout -> logout
//                    .logoutUrl("/authentication/logout")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/authentication/login?logout"));
//        return http.build();
//    }

}
