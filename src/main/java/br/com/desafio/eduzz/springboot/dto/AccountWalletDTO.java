package br.com.desafio.eduzz.springboot.dto;

import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountWalletDTO {
    private Long accountId;
    private String name;
    private String email;
    private AccountStatus status;
    private Double balance;

}

