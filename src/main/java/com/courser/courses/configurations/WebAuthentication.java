package com.courser.courses.configurations;

import com.courser.courses.models.subclass.Admin;
import com.courser.courses.models.subclass.Student;
import com.courser.courses.models.subclass.Teacher;
import com.courser.courses.models.supclass.Person;
import com.courser.courses.repositories.PersonRepository;
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
    private PersonRepository personRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputEmail-> {

            Person person = personRepository.findByEmail(inputEmail);

            if (person instanceof Admin) {
                return new User(person.getEmail(), person.getPassword(),
                        AuthorityUtils.createAuthorityList("ADMIN"));
            }
            if (person instanceof Teacher) {
                return new User(person.getEmail(), person.getPassword(),
                        AuthorityUtils.createAuthorityList("TEACHER"));
            }
            if (person instanceof Student) {
                return new User(person.getEmail(), person.getPassword(),
                        AuthorityUtils.createAuthorityList("STUDENT"));
            }

            throw new UsernameNotFoundException("Unknown user email: " + inputEmail);
        });

    }

}
