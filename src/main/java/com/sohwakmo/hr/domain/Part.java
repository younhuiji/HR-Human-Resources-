package com.sohwakmo.hr.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class Part implements Serializable {
    @Column(name = "DEPARTMENT_NAME",nullable = false)
    private String department; // 부서명

    @Column(name = "TEAM_NAME",nullable = false)
    private String team; // 팀명

    @Column(name = "ASSIGNED_WORK",nullable = false)
    private String work; // 맡은일
}
