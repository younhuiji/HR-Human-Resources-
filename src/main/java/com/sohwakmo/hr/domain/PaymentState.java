package com.sohwakmo.hr.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum PaymentState {
    진행중("PROGRESS"), // 결재 진행중
    반려("RETURN"), // 결재 반려
    승인("COMPLETE"); // 결재 승인

    private String role;

    PaymentState(String role){
        this.role = role;
    }


}
