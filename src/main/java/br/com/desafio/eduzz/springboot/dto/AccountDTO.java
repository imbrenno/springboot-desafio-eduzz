package br.com.desafio.eduzz.springboot.dto;

import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class AccountDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private OffsetDateTime createdAt;
    private AccountStatus status;
    private OffsetDateTime canceledAt;
    private OffsetDateTime updateAt;

}
