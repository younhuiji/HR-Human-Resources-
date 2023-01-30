package com.sohwakmo.hr.domain;

import lombok.Getter;

@Getter
public enum PaymentState {
    PROGRESS, // 결재 진행중
    RETURN, // 결재 반려
    COMPLETE // 결재 승인
}
