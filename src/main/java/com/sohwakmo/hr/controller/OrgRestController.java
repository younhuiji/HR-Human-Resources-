package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.dto.OrgReadDto;
import com.sohwakmo.hr.service.OrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/org")
public class OrgRestController {
    private final OrgService orgService;

    @GetMapping("/all/{team}")
    public ResponseEntity<List<OrgReadDto>> readAllOrgMember(@PathVariable String team){
        log.info("readAllOrgMember(team={})",team);

        List<OrgReadDto> list = orgService.readOrgMember(team);
        log.info("# of list={}", list.size());
        return ResponseEntity.ok(list);
    }

}
