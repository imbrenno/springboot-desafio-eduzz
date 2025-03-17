package br.com.desafio.eduzz.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "wallet")

public class WalletModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private double balance;
    private OffsetDateTime create_at;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private AccountModel account;

    public WalletModel(Long id, double balance, OffsetDateTime create_at) {
        this.id = id;
        this.balance = balance;
        this.create_at = create_at;
    }


    public WalletModel() {

    }
}
