package com.project.p_company.presentation.request;

import com.project.p_company.application.dtos.CompanyDto;
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
public class CompanyRequest {

    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType type; // 생산업체(Producer), 수령업체(Receiver)

    private UUID managedHub; // 업체 관리 허브 ID

    private String address;

    public CompanyDto toDTO() {
        return CompanyDto.create(
                this.name,
                this.type,
                this.managedHub,
                this.address);
    }

}
