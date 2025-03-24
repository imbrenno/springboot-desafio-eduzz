package br.com.desafio.eduzz.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "wallet")
public class WalletModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;
    private OffsetDateTime created_at;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    @JsonBackReference
    @JsonIgnore
    private AccountModel account;

}
