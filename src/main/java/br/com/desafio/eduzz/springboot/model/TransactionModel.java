package br.com.desafio.eduzz.springboot.model;

import br.com.desafio.eduzz.springboot.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "transactions")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private AccountModel account;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal btc_amount;
    private BigDecimal btc_price;
    private OffsetDateTime created_at;

}
