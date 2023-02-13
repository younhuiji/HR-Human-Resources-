package com.sohwakmo.hr.messageTest;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Message;
import com.sohwakmo.hr.dto.MessageSearchDto;
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

    @Test
    public void test() {
        List<MessageSearchDto> message = messageRepository.findByReceiveNoAndSenderName("2", "테");
        for(MessageSearchDto m : message) {
            log.info("m = {}", m);
        }
    }


}
