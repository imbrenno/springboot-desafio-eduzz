package br.com.desafio.eduzz.springboot.dto;

import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountWalletDTO {
    private Long account_id;
    private String name;
    private String email;
    private AccountStatus status;
    private Double balance;

}

