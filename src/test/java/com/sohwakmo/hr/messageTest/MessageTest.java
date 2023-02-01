package com.sohwakmo.hr.messageTest;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Message;
import com.sohwakmo.hr.repository.EmployeeRepository;
import com.sohwakmo.hr.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class MessageTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

//    @Test
//    public void testReceiveFind() {
//        List<Message> messageList = messageRepository.findByReceiveNoOrderByMessageNoDesc(2);
//        for(Message m : messageList) {
//            log.info("m = {}", m);
//        }
//    }

//    @Test
//    public void testSenderFind1() {
//        Employee employee = employeeRepository.findByEmployeeNo(1L);
//        log.info("employee = {}", employee);
//    }

    @Test
    public void test() {
        List<Message> message = messageRepository.searchBySenderName(2, "테스트");

        for(Message m : message) {
            log.info("m = {}", m);
        }
    }


}
