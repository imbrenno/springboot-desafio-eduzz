package br.com.desafio.eduzz.springboot.model;


import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "account")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private OffsetDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private OffsetDateTime canceledAt;
    private OffsetDateTime updateAt;

}
