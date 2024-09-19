package com.project.p_company.presentation.response;

import com.project.p_company.domain.model.Company;
import com.project.p_company.domain.model.CompanyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType type; // 생산업체(Producer), 수령업체(Receiver)

    private UUID managedHub; // 업체 관리 허브 ID

    private String address;

    public static CompanyResponse of(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .type(company.getType())
                .address(company.getAddress())
                .build();
    }

    public static List<CompanyResponse> of(List<Company> companies) {
        return companies.stream()
                .map(CompanyResponse::of)
                .collect(Collectors.toList());
    }
}
