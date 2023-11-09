package com.courser.courses.models.supclass;

import com.courser.courses.models.enums.Role;

import javax.persistence.*;

@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName, email, password;
    private Role role;
    private Boolean active;

    public Person(){}

    public Person(String fullName, String email, String password, Role role, Boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}
