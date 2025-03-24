package br.com.desafio.eduzz.springboot.model;

import br.com.desafio.eduzz.springboot.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "transaction")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;
    private BigDecimal btc_amount;
    private BigDecimal btc_price;
    private OffsetDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false,  unique = false)
    @JsonBackReference
    @JsonIgnore
    private AccountModel account;


}
