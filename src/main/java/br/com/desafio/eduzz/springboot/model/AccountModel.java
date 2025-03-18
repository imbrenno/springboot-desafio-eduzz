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
    private OffsetDateTime created_at;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private OffsetDateTime canceled_at;
    private OffsetDateTime update_at;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private WalletModel wallet;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private TransactionModel trasaction;

}
