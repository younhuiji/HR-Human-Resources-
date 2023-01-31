package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Post;
import com.sohwakmo.hr.dto.post.PostCreateDto;
import com.sohwakmo.hr.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> read() {
        log.info("read()");

        return postRepository.findByOrderByPostNoDesc();
    }

    public Post create(PostCreateDto dto){
        log.info("create(dto= {})", dto);

        Post entity= postRepository.save(dto.toEntity());

        return entity;
    }
}
