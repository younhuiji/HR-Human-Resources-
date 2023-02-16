package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.Post;
import com.sohwakmo.hr.dto.post.PostCreateDto;
import com.sohwakmo.hr.dto.post.PostUpdateDto;
import com.sohwakmo.hr.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<Post> readPost(Pageable pageable) {
        log.info("readPost()");

        Integer page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable= PageRequest.of(page,10);

        return postRepository.findByOrderByNoticeLvAscPostNoDesc(pageable);
    }

    public Page<Post> searchPost(Pageable pageable, String type, String keyword) {
        log.info("search(type={}, keyword={})", type, keyword);

        Integer page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable= PageRequest.of(page,10);

        Page<Post> list= null;
        switch (type) {
            case "tcw": // 전체 검색
                list = postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrWriterContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(pageable, keyword, keyword, keyword);
                break;
            case "t": // 제목만 검색
                list = postRepository.findByTitleContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(pageable, keyword);
                break;
            case "c": // 내용만 검색
                list = postRepository.findByContentContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(pageable, keyword);
                break;
            case "tc": // 제목 또는 내용 검색
                list = postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(pageable, keyword, keyword);
                break;
            case "w": // 작성자만 검색
                list = postRepository.findByWriterContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(pageable, keyword);
                break;
        }

        return list;
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

        Post entity= postRepository.findByPostNo(dto.getPostNo());
        entity.updatePost(dto.getTitle(), dto.getContent());

        return entity.getPostNo();
    }

    @Transactional
    public Integer deletePost(Integer postNo) {
        log.info("deletePost(postNo= {})", postNo);
        postRepository.deleteById(postNo);
        return postNo;
    }

    public List<Post> readPostSeven() {
        List<Post> allList = postRepository.findByOrderByNoticeLvAscPostNoDesc();
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
