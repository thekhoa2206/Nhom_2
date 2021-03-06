package com.vuw17.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity{
    @Column(name = "username", length = 100, nullable = true)
    private String username;

    @Column(name = "password", length = 100, nullable = true)
    private String password;

    @Column(name = "name", length = 100, nullable = true)
    private String name;

    @Column(name = "address", length = 100, nullable = true)
    private String address;

    @Column(name = "email", length = 100, nullable = true)
    private String email;

    @Column(name = "phone", length = 20, nullable = true)
    private String phone;

    @Column(name = "sex", length = 100)
    private Boolean sex;

    @Column(name = "salary_day")
    private Double salaryDay;

    @Column(name = "id_card", length = 20, nullable = true)
    private String idCard;
    @Column(name = "status")
    private Integer status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<Role>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
