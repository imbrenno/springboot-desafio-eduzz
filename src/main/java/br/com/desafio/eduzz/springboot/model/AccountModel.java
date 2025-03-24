package br.com.desafio.eduzz.springboot.model;

import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

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
    @JsonBackReference
    @JsonIgnore
    private WalletModel wallet;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonIgnore
    private List<TransactionModel> transactions;

}
