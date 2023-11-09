package com.courser.courses.models.subclass;

import com.courser.courses.models.enums.Role;
import com.courser.courses.models.supclass.Person;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin extends Person {

    private String area;

    public Admin() {}

    public Admin(String fullName, String email, String password, Role role, Boolean active, String area) {
        super(fullName, email, password, role, active);
        this.area = area;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
}
