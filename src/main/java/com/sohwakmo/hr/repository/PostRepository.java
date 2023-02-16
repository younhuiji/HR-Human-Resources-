package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findByOrderByNoticeLvAscPostNoDesc(Pageable pageable);

    Post findByPostNo(Integer postNo);

    Page<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrWriterContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(Pageable pageable, String title, String content,String writer);
    Page<Post> findByTitleContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(Pageable pageable, String title);
    Page<Post> findByContentContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(Pageable pageable, String content);
    Page<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(Pageable pageable, String title, String content);
    Page<Post> findByWriterContainingIgnoreCaseOrderByNoticeLvAscPostNoDesc(Pageable pageable, String writer);

    List<Post> findByOrderByNoticeLvAscPostNoDesc();
}
