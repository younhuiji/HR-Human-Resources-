package com.sohwakmo.hr.domain;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
