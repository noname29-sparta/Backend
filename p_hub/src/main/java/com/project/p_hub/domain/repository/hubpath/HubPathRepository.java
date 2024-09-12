package com.project.p_hub.domain.repository.hubpath;

import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.model.HubPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubPathRepository {
    HubPath save(HubPath hubPath);
    Optional<HubPath> findById(UUID id);
    List<HubPath> findAll();
    Page<HubPath> searchHubs(HubDto request, Pageable pageable);
}
