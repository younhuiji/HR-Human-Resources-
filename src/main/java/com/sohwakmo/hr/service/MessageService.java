package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Message;
import com.sohwakmo.hr.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    /**
     * 파일 첨부없이 메세지를 보낼 때 사용
     * @param message
     */
    public void create(Message message) {
        log.info("create(Message = {})", message);
        
        messageRepository.save(message);
    }

    /**
     * 파일 첨부와 함께 메세지를 보낼 때 사용
     * @param message
     * @param file
     */
    public void create(Message message, MultipartFile file) throws IOException {
        log.info("create(message = {}, file = {})", message, file);

        // 파일 저장 경로
        String projectFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\message";
        log.info("projectFilePath = {}", projectFilePath);

        // 파일 이름
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + "_" + file.getOriginalFilename();
        log.info("fileName = {}", fileName);

        File saveFile = new File(projectFilePath, fileName);

        // 파일을 해당경로에 저장
        file.transferTo(saveFile);

        if (message.getFilePath1() == null) {
            message.setFilePath1(projectFilePath + "\\" + fileName);
            message.setFileName1(file.getOriginalFilename());
            log.info("message = {}", message);
        } else if (message.getFilePath2() == null) {
            message.setFilePath2(projectFilePath + "\\" + fileName);
            message.setFileName2(file.getOriginalFilename());
            log.info("message = {}", message);
        } else if (message.getFilePath3() == null) {
            message.setFilePath3(projectFilePath + "\\" + fileName);
            message.setFileName3(file.getOriginalFilename());
            log.info("message = {}", message);
        }

        messageRepository.save(message);
    }

}
