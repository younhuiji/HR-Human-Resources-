package com.sohwakmo.hr.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Part {

    @Column(name = "DEPARTMENT_NAME",nullable = false)
    private String department; // 부서명

    @Column(name = "TEAM_NAME",nullable = false)
    private String team;

    @Column(name = "ASSIGNED_WORK",nullable = false)
    private String work;

    @Column(nullable = false)
    private String position; // 직책
}
