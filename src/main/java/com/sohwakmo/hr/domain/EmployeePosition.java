package com.sohwakmo.hr.domain;

import java.io.Serializable;

public enum EmployeePosition implements Serializable {
    LEVEL_1, // 일반 사원
    LEVEL_2, // 대리, 과장, 차장
    LEVEL_3, // 팀장, 상무 이상
    LEVEL_4 // Admin
}
