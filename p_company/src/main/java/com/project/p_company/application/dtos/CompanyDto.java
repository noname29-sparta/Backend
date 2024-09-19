package com.project.p_company.application.dtos;

import com.project.p_company.domain.model.CompanyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType type; // 생산업체(Producer), 수령업체(Receiver)

    private UUID managedHub; // 업체 관리 허브 ID

    private String address;

    public static CompanyDto create(String name, CompanyType type, UUID managedHub, String address) {
        return CompanyDto.builder()
                .name(name)
                .type(type)
                .managedHub(managedHub)
                .address(address)
                .build();
    }

}
