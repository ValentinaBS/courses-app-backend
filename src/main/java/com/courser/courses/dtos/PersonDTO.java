package com.courser.courses.dtos;

import com.courser.courses.models.enums.Role;
import com.courser.courses.models.supclass.Person;

public class PersonDTO {
    private Long id;
    private String fullName, email, password;
    private Role role;
    private Boolean active;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.email = person.getEmail();
        this.password = person.getPassword();
        this.role = person.getRole();
        this.active = person.getActive();
    }

    public Long getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
    public Boolean getActive() {
        return active;
    }
}
