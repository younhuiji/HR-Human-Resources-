package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.Post;
import com.sohwakmo.hr.dto.post.PostCreateDto;
import com.sohwakmo.hr.dto.post.PostUpdateDto;
import com.sohwakmo.hr.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> readPost() {
        log.info("readPost()");

        return postRepository.findByOrderByNoticeYnAscPostNoDesc();
    }

    public Post createPost(PostCreateDto dto){
        log.info("createPost(dto= {})", dto);

        return postRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Post readPost(Integer postNo) {
        log.info("readPost(postNo= {})", postNo);
        return postRepository.findByPostNo(postNo);
    }

    @Transactional
    public Integer updatePost(PostUpdateDto dto) {
        log.info("updatePost(dto= {})", dto);

        Post entity = postRepository.findByPostNo(dto.getPostNo());
        entity.update(dto.getTitle(), dto.getContent());

        return entity.getPostNo();
    }

    public Integer deletePost(Integer postNo) {
        log.info("deletePost(postNo= {})", postNo);

        return postRepository.deleteByPostNo(postNo).getPostNo();
    }

    public List<Post> readPostSeven() {
        List<Post> allList = postRepository.findByOrderByNoticeYnAscPostNoDesc();
        if (allList.size() <=7) {
            return allList;
        }else {
            List<Post> list = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                list.add(allList.get(i));
            }
            return list;
        }
    }
}
