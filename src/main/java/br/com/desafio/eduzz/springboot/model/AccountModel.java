package br.com.desafio.eduzz.springboot.model;


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
    private OffsetDateTime created_at;

    public AccountModel(Long id, String name, String email, String password, OffsetDateTime created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

}
