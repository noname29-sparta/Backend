package com.project.p_hub.domain.service;

import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.repository.hub.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubPathDomainService {

    private final HubRepository hubRepository;

    public void validateHub(UUID startHubId, UUID endHubId) {

        // 출발 허브가 존재하는 허브인지 확인
        Hub startHub = hubRepository.findById(startHubId)
                .orElseThrow(() -> new RuntimeException("존재하는 허브가 없습니다."));

        // 도착 허브가 존재하는 허브인지 확인
        Hub endHub = hubRepository.findById(endHubId)
                .orElseThrow(() -> new RuntimeException("존재하는 허브가 없습니다."));

        // 경로를 설정할 때 출발 허브와 도착 허브는 같을 수 없음
        if (startHub == endHub) {
            throw new RuntimeException("출발지 허브와 도착지 허브는 같을 수 없습니다.");
        }

    }
}
