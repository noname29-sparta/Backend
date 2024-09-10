package com.project.p_hub.presentation.controller;

import com.project.p_hub.presentation.resonse.HubResponse;
import com.project.p_hub.application.service.HubService;
import com.project.p_hub.presentation.request.HubRequest;
import com.project.p_hub.presentation.request.SearchHubRequest;
import com.project.p_hub.presentation.request.UpdateHubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hubs")
public class HubController {
    
    private final HubService hubService;

    // 허브 생성
    @PostMapping
    public ResponseEntity<?> createHub(@RequestBody HubRequest hubRequest) {
        
        return ResponseEntity.ok().body(hubService.createHub(hubRequest.toDTO()));
    }

    // 허브 단건 조회
    @GetMapping("/{hubId}")
    public ResponseEntity<?> getHub(@PathVariable(name = "hubId") UUID hubId) {

        return ResponseEntity.ok().body(hubService.getHub(hubId));
    }

    // 허브 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllHubs(){

        return ResponseEntity.ok().body(hubService.getAllHubs());
    }


    // 허브 수정
    @PatchMapping("/{hubId}")
    public ResponseEntity<?> updateHub(@PathVariable(name = "hubId") UUID hubId,
                                       @RequestBody UpdateHubRequest updateHubRequest) {

        return ResponseEntity.ok().body(hubService.updateHub(hubId, updateHubRequest.toDTO()));
    }

    // 허브 삭제
    @DeleteMapping("/{hubId}")
    public ResponseEntity<?> deleteHub(@PathVariable(name = "hubId") UUID hubId) {

        return ResponseEntity.ok().body(hubService.deleteHub(hubId));
    }

    // 허브 검색
    @GetMapping("/search")
    public ResponseEntity<Page<HubResponse>> searchHubs(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String city,
                                                        @RequestParam(required = false) Double latitude,
                                                        @RequestParam(required = false) Double longitude,
                                                        @RequestParam(required = false) String address,
                                                        @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "20") int size) {

        // HubSearchDto에 파라미터 설정
        SearchHubRequest searchDto = SearchHubRequest.builder()
                .name(name)
                .city(city)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(hubService.searchHubs(searchDto.toDTO(), pageRequest));
    }

}
