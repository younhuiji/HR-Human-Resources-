package com.sohwakmo.hr.service;

import com.sohwakmo.hr.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void changePhone(){
        Assertions.assertNotNull(employeeService);

        String phone = "010-1111-1111";
        String result = phone.replaceAll("-", "");
        Assertions.assertEquals("01011111111",result);
        System.out.println(result);
    }

    @Test
    public void checkEmail(){
        String email = "asdcz@naver.com";
        boolean result = employeeRepository.existsByEmail(email);
        Assertions.assertTrue(result);
    }

}