package com.sohwakmo.hr.domain;

public enum EmployeePosition {
    LEVEL_1("LEVEL_1"), // 일반 사원
    LEVEL_2("LEVEL_2"), // 대리, 차장, 과장
    LEVEL_3("LEVEL_3"), // 팀장 이상
    LEVEL_4("LEVEL_4"); // Admin ;

    private String role;

    EmployeePosition(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
