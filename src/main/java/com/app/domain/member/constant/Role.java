package com.app.domain.member.constant;

public enum Role {

    USER, EMPLOYEE, ADMIN;

    public static Role from(String role) {
        return Role.valueOf(role);
    }
    public boolean isEmployee(){
        return this.equals(Role.EMPLOYEE) || this.equals(Role.ADMIN);
    }
}
