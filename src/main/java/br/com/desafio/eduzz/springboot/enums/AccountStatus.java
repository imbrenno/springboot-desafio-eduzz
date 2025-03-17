package br.com.desafio.eduzz.springboot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {
    ACTIVE("active"),
    INACTIVE("inactive"),
    SUSPENDED("suspended"),
    CANCELED("canceled"),
    PENDING("pending"),
    BLOCKED("blocked");

    private final String description;
}




