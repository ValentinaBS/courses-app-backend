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

                .antMatchers(HttpMethod.POST, "/api/clients", "/api/login", "/api/logout").permitAll()
                .antMatchers("/web/index.html", "/web/global.css", "/web/styles/**", "/web/js/**", "/web/images/**", "/api/loans").permitAll()

                .antMatchers(HttpMethod.POST, "/api/loans/create").hasAuthority("ADMIN")
                .antMatchers("/rest/**", "/h2-console/**", "/web/adminPages/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/loans", "/api/transactions").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.PATCH, "/api/clients/current/cards/{id}", "/api/clients/current/accounts/{id}", "/api/loans/{id}").hasAuthority("CLIENT")
                .antMatchers("/web/pages/**", "/api/clients/current", "/api/clients/current/**", "/api/accounts/{id}", "/api/transactions").hasAuthority("CLIENT");

                //.anyRequest().denyAll();

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
