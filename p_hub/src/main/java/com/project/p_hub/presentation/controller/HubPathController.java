package com.project.p_hub.presentation.controller;


import com.project.p_hub.application.service.HubPathService;
import com.project.p_hub.application.service.HubService;
import com.project.p_hub.presentation.request.hub.HubRequest;
import com.project.p_hub.presentation.request.hubpath.HubPathRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hubPaths")
public class HubPathController {

    private final HubPathService hubPathService;

    // 허브 생성
    @PostMapping
    public ResponseEntity<?> createHub(@RequestBody HubPathRequest hubPathRequest) {

        return ResponseEntity.ok().body(hubPathService.createHubPath(hubPathRequest.toDTO()));
    }



}
