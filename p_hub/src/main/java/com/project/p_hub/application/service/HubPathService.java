package com.project.p_hub.application.service;

import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.application.dtos.HubPathDto;
import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.model.HubPath;
import com.project.p_hub.domain.repository.hub.HubRepository;
import com.project.p_hub.domain.repository.hubpath.HubPathRepository;
import com.project.p_hub.domain.service.HubPathDomainService;
import com.project.p_hub.presentation.response.HubPathResponse;
import com.project.p_hub.presentation.response.HubResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HubPathService {

    private final HubPathRepository hubPathRepository;
    private final HubPathDomainService hubPathDomainService;
    private final HubRepository hubRepository;

    // 허브경로 생성
    @Transactional
    public HubPathResponse createHubPath(HubPathDto request) {

        HubPath hubPath = HubPath.create(
                request.getStartHubId(),
                request.getEndHubId(),
                request.getTravelTime(),
                request.getDisplayName()
        );

        // 허브 validation
        hubPathDomainService.validateHub(request.getStartHubId(), request.getEndHubId());

        hubPathRepository.save(hubPath);

        return HubPathResponse.of(hubPath);
    }

    // 허브경로 단건 조회
    @Transactional
    public HubPathResponse getHubPath(UUID hubPathId) {

        HubPath hubPath = hubPathRepository.findById(hubPathId)
                .orElseThrow(() -> new RuntimeException("존재하는 허브경로가 없습니다."));

        return HubPathResponse.of(hubPath);
    }

    // 허브경로 전체 조회
    @Transactional
    public List<HubPathResponse> getAllHubPaths() {

        List<HubPath> hubPaths = hubPathRepository.findAll();

        return HubPathResponse.of(hubPaths);
    }

    // 허브경로 수정
    @Transactional
    public HubPathResponse updateHubPath(UUID hubPathId, HubPathDto request) {
        return hubPathRepository.findById(hubPathId).map(hubPath -> {
            hubPath.update(request.getStartHubId(), request.getEndHubId(), request.getTravelTime(), request.getDisplayName());
            return HubPathResponse.of(hubPath);
        }).orElseThrow();
    }

    // 허브경로 삭제
    @Transactional
    public HubPathResponse deleteHubPath(UUID hubPathId) {
        return hubPathRepository.findById(hubPathId).map(hubPath -> {
            hubPath.delete(true);
            return HubPathResponse.of(hubPath);
        }).orElseThrow();

    }




}
