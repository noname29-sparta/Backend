package com.project.p_auth.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum UserRoleEnum implements Serializable {
    COMPANY(Authority.COMPANY),  // 업체
    DELIVERY(Authority.DELIVERY),  // 배송기사
    HUB(Authority.HUB), // 허브
    MASTER(Authority.MASTER); // 마스터

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }
    public static class Authority {
        public static final String COMPANY = "ROLE_COMPANY";
        public static final String DELIVERY = "ROLE_DELIVERY";
        public static final String HUB = "ROLE_HUB";
        public static final String MASTER = "ROLE_MASTER";
    }
}