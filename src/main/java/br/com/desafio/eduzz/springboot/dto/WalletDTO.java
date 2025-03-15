package br.com.desafio.eduzz.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class WalletDTO {

    private Long id;
    private Long account_id;
    private double balance;
    private OffsetDateTime create_at;
}
