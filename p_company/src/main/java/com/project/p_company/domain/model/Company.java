package com.project.p_company.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_companies")
public class Company extends BaseEntity{

    @Id
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType type; // 생산업체(Producer), 수령업체(Receiver)

    private UUID managedHub; // 업체 관리 허브 ID

    private String address;

    public static Company create(String name, CompanyType type, UUID managedHub, String address) {
        return Company.builder()
                .id(UUID.randomUUID())
                .name(name)
                .type(type)
                .managedHub(managedHub)
                .address(address)
                .build();
    }

    public void update(String name, CompanyType type, UUID managedHub, String address) {
        this.name = name;
        this.type = type;
        this.managedHub = managedHub;
        this.address = address;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }

}
