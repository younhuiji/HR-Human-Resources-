package com.sohwakmo.hr.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
