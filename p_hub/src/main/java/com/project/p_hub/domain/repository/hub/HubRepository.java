package com.project.p_hub.domain.repository.hub;

import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.domain.model.Hub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubRepository{
    Hub save(Hub hub);
    Optional<Hub> findById(UUID id);
    List<Hub> findAll();
    Page<Hub> searchHubs(HubDto request, Pageable pageable);

}
