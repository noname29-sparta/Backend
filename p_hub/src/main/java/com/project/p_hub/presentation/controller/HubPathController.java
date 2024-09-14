package com.project.p_hub.presentation.controller;


import com.project.p_hub.application.service.HubPathService;
import com.project.p_hub.application.service.HubService;
import com.project.p_hub.domain.model.HubPath;
import com.project.p_hub.presentation.request.hub.HubRequest;
import com.project.p_hub.presentation.request.hub.UpdateHubRequest;
import com.project.p_hub.presentation.request.hubpath.HubPathRequest;
import com.project.p_hub.presentation.request.hubpath.UpdateHubPathRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hubPaths")
public class HubPathController {

    private final HubPathService hubPathService;

    // 허브경로 생성
    @PostMapping
    public ResponseEntity<?> createHub(@RequestBody HubPathRequest hubPathRequest) {

        return ResponseEntity.ok().body(hubPathService.createHubPath(hubPathRequest.toDTO()));
    }

    // 허브경로 단건 조회
    @GetMapping("/{hubPathId}")
    public ResponseEntity<?> getHubPath(@PathVariable(name = "hubPathId") UUID hubPathId) {

        return ResponseEntity.ok().body(hubPathService.getHubPath(hubPathId));
    }

    // 허브경로 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllHubPaths(){

        return ResponseEntity.ok().body(hubPathService.getAllHubPaths());
    }


    // 허브경로 수정
    @PatchMapping("/{hubPathId}")
    public ResponseEntity<?> updateHubPath(@PathVariable(name = "hubPathId") UUID hubPathId,
                                       @RequestBody UpdateHubPathRequest updateHubPathRequest) {

        return ResponseEntity.ok().body(hubPathService.updateHubPath(hubPathId, updateHubPathRequest.toDTO()));
    }

    // 허브경로 삭제
    @DeleteMapping("/{hubPathId}")
    public ResponseEntity<?> deleteHubPath(@PathVariable(name = "hubPathId") UUID hubPathId) {

        return ResponseEntity.ok().body(hubPathService.deleteHubPath(hubPathId));
    }

    @GetMapping("/hubPaths")
    public ResponseEntity<List<HubPath>> findPath(@RequestParam(name = "startHubId") UUID startHubId, @RequestParam(name = "endHubId") UUID endHubId) {
        try {
            List<HubPath> path = hubPathService.findPath(startHubId, endHubId);
            return ResponseEntity.ok(path);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



}
