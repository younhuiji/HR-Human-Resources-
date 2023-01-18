package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.dto.BusinessTripCreateDto;
import com.sohwakmo.hr.service.BusinessTripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/businessTrip")
public class BusinessTripController {

    private final BusinessTripService businessTripService;

    @GetMapping("/create")
    public String create(){
        return "/businessTrip/create";
    }

    @PostMapping("/create")
    public String create(BusinessTripCreateDto dto){
        BusinessTrip businessTrip = BusinessTrip.builder()
                .title(dto.getTitle()).state(dto.getState()).employeeName(dto.getEmployeeName()).employeeNo(1L).category(dto.getCategory())
                .returnReason(dto.getReturnReason()).writeDate(dto.getWriteDate()).effectiveDate(dto.getEffectiveDate())
                .expirationDate(dto.getExpirationDate()).place(dto.getPlace()).approverNo(0L).reason(dto.getReason()).build();

        BusinessTrip businessTrips = businessTripService.create(businessTrip);

        return "/businessTrip/create";
    }

}
