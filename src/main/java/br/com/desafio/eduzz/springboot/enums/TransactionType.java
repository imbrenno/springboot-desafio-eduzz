package br.com.desafio.eduzz.springboot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    DEPOSIT("deposit"),
    BUY("buy"),
    SELL("sell");

    private final String description;
}