package com.project.p_hub.application.service;

import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.presentation.response.HubResponse;
import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.repository.hub.HubRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;

    // 허브 생성
    @Transactional
    public HubResponse createHub(HubDto request) {

        Hub hub = Hub.create(
                request.getName(),
                request.getCity(),
                request.getLatitude(),
                request.getLongitude(),
                request.getAddress()
        );

        hubRepository.save(hub);

        return HubResponse.of(hub);
    }

    // 허브 단건 조회
    @Transactional
    public HubResponse getHub(UUID hubId) {

       Hub hub = hubRepository.findById(hubId)
                .orElseThrow(() -> new RuntimeException("존재하는 허브가 없습니다."));

        return HubResponse.of(hub);
    }

    // 허브 전체 조회
    @Transactional
    public List<HubResponse> getAllHubs() {

        List<Hub> hubs = hubRepository.findAll();

        return HubResponse.of(hubs);
    }

    // 허브 수정
    @Transactional
    public HubResponse updateHub(UUID hubId, HubDto request) {
        return hubRepository.findById(hubId).map(hub -> {
            hub.update(request.getName(), request.getCity(), request.getLatitude(), request.getLongitude(), request.getAddress());
            return HubResponse.of(hub);
        }).orElseThrow();
    }

    // 허브 삭제
    @Transactional
    public HubResponse deleteHub(UUID hubId) {
        return hubRepository.findById(hubId).map(hub -> {
            hub.delete(true);
            return HubResponse.of(hub);
        }).orElseThrow();

    }

    // 허브 검색
    @Transactional
    public Page<HubResponse> searchHubs(HubDto request, Pageable pageable) {
        Page<Hub> hubs = hubRepository.searchHubs(request, pageable);
        return hubs.map(HubResponse::of);
    }




}
