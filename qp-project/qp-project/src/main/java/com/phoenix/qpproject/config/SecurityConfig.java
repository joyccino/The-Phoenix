package com.phoenix.qpproject.config;

import com.phoenix.qpproject.service.MemberService;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/authentication/**", "/resources/**", "/vendors/**", "/assets/**", "/favicon/**").permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
                )
                .formLogin()
                    .loginPage("/authentication/login") // 사용자 정의 로그인 페이지
                    .defaultSuccessUrl("/quiz/quizList", true) // 로그 인 성공 후 이동 페이지
                    .failureUrl("/authentication/login?error=true")	        // 로그인 실패 후 이동 페이지
                    .usernameParameter("memberId") // 아이디 파라미터명 설정
                    .passwordParameter("memberPw") // 패스워드 파라미터명 설정
                    .loginProcessingUrl("authentication/memberLogin") // 로그인 Form Action Url
                    .successHandler(
                            new AuthenticationSuccessHandler() {
                                @Override
                                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
                                    System.out.println("authentication : " + authentication.getName());
                                    response.sendRedirect("/quiz/quizList"); // 인증이 성공한 후에는 root로 이동
                                }
                            }
                    )		// 로그인 성공 후 핸들러
                    .failureHandler(new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                System.out.println("exception : " + exception.getMessage());
                                response.sendRedirect("/login");
                            }
                        }
                    )		// 로그인 실패 후 핸들러// form 방식 로그인 사용

                .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
                .and()
                .logout(logout -> logout
                    .logoutUrl("./logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/authentication/login?logout"));

        return http.build();

    }

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
