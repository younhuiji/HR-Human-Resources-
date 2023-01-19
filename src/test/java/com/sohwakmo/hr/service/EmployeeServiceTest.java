package com.sohwakmo.hr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void changePhone(){
        Assertions.assertNotNull(employeeService);

        String phone = "010-1111-1111";
        String result = phone.replaceAll("-", "");
        Assertions.assertEquals("01011111111",result);
        System.out.println(result);
    }

}