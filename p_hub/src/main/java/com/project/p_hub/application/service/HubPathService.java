package com.project.p_hub.application.service;

import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.application.dtos.HubPathDto;
import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.model.HubPath;
import com.project.p_hub.domain.repository.hubpath.HubPathRepository;
import com.project.p_hub.domain.service.HubPathDomainService;
import com.project.p_hub.presentation.response.HubPathResponse;
import com.project.p_hub.presentation.response.HubResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubPathService {

    private final HubPathRepository hubPathRepository;
    private final HubPathDomainService hubPathDomainService;

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
//    @Transactional
//    public HubResponse getHubPath(UUID hubId) {
//
//        Hub hub = hubRepository.findById(hubId)
//                .orElseThrow(() -> new RuntimeException("존재하는 허브가 없습니다."));
//
//        return HubResponse.of(hub);
//    }
//
//    // 허브경로 전체 조회
//    @Transactional
//    public List<HubResponse> getAllHubPaths() {
//
//        List<Hub> hubs = hubRepository.findAll();
//
//        return HubResponse.of(hubs);
//    }
//
//    // 허브경로 수정
//    @Transactional
//    public HubResponse updateHubPath(UUID hubId, HubDto request) {
//        return hubRepository.findById(hubId).map(hub -> {
//            hub.update(request.getName(), request.getCity(), request.getLatitude(), request.getLongitude(), request.getAddress());
//            return HubResponse.of(hub);
//        }).orElseThrow();
//    }
//
//    // 허브경로 삭제
//    @Transactional
//    public HubResponse deleteHubPath(UUID hubId) {
//        return hubRepository.findById(hubId).map(hub -> {
//            hub.delete(true);
//            return HubResponse.of(hub);
//        }).orElseThrow();
//
//    }
}
