package com.sohwakmo.hr.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Part {

    @Column(name = "DEPARTMENT_NAME",nullable = false)
    private String department; // 부서명

    @Column(name = "TEAM_NAME",nullable = false)
    private String team; // 팀명

    @Column(name = "ASSIGNED_WORK",nullable = false)
    private String work; // 맡은일

}
