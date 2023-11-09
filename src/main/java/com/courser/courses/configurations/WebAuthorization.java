package com.courser.courses.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/students", "/api/teachers", "/api/login", "/api/logout").permitAll()
                .antMatchers("/api/courses").authenticated()

                .antMatchers(HttpMethod.POST, "/api/admins", "/api/courses").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/admins/**", "/api/courses/**", "/api/students/**", "/api/teachers/**", "/api/studentCourse/**", "/api/teacherCourse/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admins/**", "/api/courses/**", "/api/students/**", "/api/teachers/**", "/api/teacherCourse", "/api/studentCourse").hasAuthority("ADMIN")
                .antMatchers("/rest/**", "/h2-console/**", "/api/admins", "/admins/**", "/api/teachers", "/api/students", "/api/students/search", "/api/studentCourse", "/api/teacherCourse").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/teacherCourse").hasAuthority("TEACHER")
                .antMatchers("/api/teachers/current").hasAuthority("TEACHER")

                .antMatchers(HttpMethod.POST, "/api/studentCourse").hasAuthority("STUDENT")
                .antMatchers("/api/students/current").hasAuthority("STUDENT")

                .anyRequest().denyAll();

        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

    }
}
