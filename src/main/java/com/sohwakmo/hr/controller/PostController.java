package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Post;
import com.sohwakmo.hr.dto.post.PostCreateDto;
import com.sohwakmo.hr.dto.post.PostUpdateDto;
import com.sohwakmo.hr.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model){
        log.info("/post/list");

        Page<Post> list= postService.readPost(pageable);
        long row= list.getTotalElements();

        model.addAttribute("row", row);
        model.addAttribute("list", list);
        model.addAttribute("type", "");
        model.addAttribute("keyword", "");

        return "/post/list";
    }

    @GetMapping("/search")
    public String search(@PageableDefault Pageable pageable, String type, String keyword, Model model) {
        log.info("search(type={}, keyword={})", type, keyword);

        Page<Post> list= postService.searchPost(pageable, type, keyword);
        long row= list.getTotalElements();

        model.addAttribute("row", row);
        model.addAttribute("list", list);
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        return "/post/list";
    }

    @GetMapping("/create")
    public void create() {
        log.info("/post/create");
    }

    @PostMapping("/create")
    public String create(PostCreateDto dto, RedirectAttributes attrs) {
        log.info("create(dto= {})", dto);

        Post entity= postService.createPost(dto);
        attrs.addFlashAttribute("postNo", entity.getPostNo());

        return "redirect:/post/list";
    }

    @GetMapping({ "/detail", "/modify" })
    public void detail(Integer postNo, Model model) {
        log.info("detail(postNo= {})", postNo);

        Post post = postService.readPost(postNo);
        log.info(post.toString());

        model.addAttribute("post", post);
    }

    @PostMapping("/delete")
    public String delete(Integer postNo, RedirectAttributes attrs) {
        log.info("delete(postNo={})", postNo);

        Integer postId = postService.deletePost(postNo);
        attrs.addFlashAttribute("deletedPostNo", postNo);

        return "redirect:/post/list";
    }

    @PostMapping("/update")
    public String update(PostUpdateDto dto, RedirectAttributes attrs) {
        log.info("update(dto= {})", dto);

        Integer postNo= postService.updatePost(dto);

        return "redirect:/post/detail?postNo="+ postNo;
    }

}
