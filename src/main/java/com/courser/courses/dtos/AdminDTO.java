package com.courser.courses.dtos;

import com.courser.courses.models.subclass.Admin;
import com.courser.courses.models.enums.Role;

public class AdminDTO {
    private long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Boolean active;
    private String area;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.fullName = admin.getFullName();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.role = admin.getRole();
        this.active = admin.getActive();
        this.area = admin.getArea();
    }

    public long getId() {
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

    public String getArea() {
        return area;
    }
}
