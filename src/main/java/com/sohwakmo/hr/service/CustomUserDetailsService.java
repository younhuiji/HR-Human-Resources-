package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.dto.EmployeeSecurityDto;
import com.sohwakmo.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<Employee> entity = Optional.ofNullable(employeeRepository.findByEmployeeNo(username));
        if (entity.isPresent()) { // 로그인 아이디가 일치하는 엔터티가 있는 경우
            return EmployeeSecurityDto.fromEntity(entity.get());
        } else { // 해당 아이디 정보가 없는 경우
            throw new UsernameNotFoundException(username + ": not found!");
        }
    }
}
