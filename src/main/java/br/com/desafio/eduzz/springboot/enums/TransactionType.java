package br.com.desafio.eduzz.springboot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    DEPOSITY("deposity"),
    BUY("buy"),
    SELL("sell");

    private final String description;
}