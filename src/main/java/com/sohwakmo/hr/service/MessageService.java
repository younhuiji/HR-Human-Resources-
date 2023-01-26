package com.sohwakmo.hr.service;

import com.sohwakmo.hr.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;


}
