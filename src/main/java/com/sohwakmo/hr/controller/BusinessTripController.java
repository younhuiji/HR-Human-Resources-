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

    // 출장(Bs trip) create page
    @GetMapping("/create")
    public String create(){
        return "/businessTrip/create";
    }

    // 출장(Bs trip) create
    @PostMapping("/create")
    public String create(BusinessTripCreateDto dto){

        String no = "11111111";

        BusinessTrip businessTrip = BusinessTrip.builder()
                .employeeNo(no).approverNo(no).title(dto.getTitle()).reason(dto.getReason())
                .category(dto.getCategory()).effectiveDate(dto.getEffectiveDate()).expirationDate(dto.getExpirationDate())
                .place(dto.getPlace()).companionNO(no).build();


        BusinessTrip businessTrips = businessTripService.create(businessTrip);

        return "/businessTrip/create";
    }

}
