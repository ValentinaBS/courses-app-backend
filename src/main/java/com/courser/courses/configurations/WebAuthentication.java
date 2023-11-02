package com.courser.courses.configurations;

import com.courser.courses.models.Admin;
import com.courser.courses.models.Student;
import com.courser.courses.models.Teacher;
import com.courser.courses.repositories.AdminRepository;
import com.courser.courses.repositories.StudentRepository;
import com.courser.courses.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputEmail-> {

            Admin admin = adminRepository.findByEmail(inputEmail);
            Teacher teacher = teacherRepository.findByEmail(inputEmail);
            Student student = studentRepository.findByEmail(inputEmail);

            if (admin != null) {
                return new User(admin.getEmail(), admin.getPassword(),
                        AuthorityUtils.createAuthorityList("ADMIN"));
            }
            if (teacher != null) {
                return new User(teacher.getEmail(), teacher.getPassword(),
                        AuthorityUtils.createAuthorityList("TEACHER"));
            }
            if (student != null) {
                return new User(student.getEmail(), student.getPassword(),
                        AuthorityUtils.createAuthorityList("STUDENT"));
            }

            throw new UsernameNotFoundException("Unknown user email: " + inputEmail);
        });

    }

}
