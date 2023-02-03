package com.sohwakmo.hr.domain;

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

    // 처음에는 전부 사원으로 해놓고 나중에 관리자가 직책과 레벨변경
    private String position; // 직책
}
