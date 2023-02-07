package com.sohwakmo.hr.service;

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

//    public List<Post> search(String type, String keyword) {
//        log.info("search(type={}, keyword={})", type, keyword);
//
//        List<Post> list = new ArrayList<>();
//        switch (type) {
//            case "t": // 제목만 검색
//                list = postRepository.findByTitleIgnoreCaseContainingOrderByIdDesc(keyword);
//                break;
//            case "c": // 내용만 검색
//                list = postRepository.findByContentIgnoreCaseContainingOrderByIdDesc(keyword);
//                break;
//            case "tc": // 제목 또는 내용 검색
//                list = postRepository.searchByKeyword(keyword);
//                break;
//            case "a": // 작성자만 검색
//                list = postRepository.findByAuthorIgnoreCaseContainingOrderByIdDesc(keyword);
//                break;
//        }
//
//        return list;
//    }
}
