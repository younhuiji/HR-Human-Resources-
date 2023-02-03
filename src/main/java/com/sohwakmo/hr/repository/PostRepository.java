package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByOrderByPostNoDesc();

    Post findByPostNo(Integer postNo);

    Post deleteByPostNo(Integer postNo);

}
