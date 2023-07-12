package com.example.toyotaprojesi.Model;

import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "UserRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long roleId;


    public long getRoleId() {
        return roleId;
    }

    public UserRole(ERole name) {
        this.name = name;

    }

    public UserRole() {
    }


    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;


}
