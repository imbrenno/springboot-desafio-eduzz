package br.com.desafio.eduzz.springboot.dto;

import br.com.desafio.eduzz.springboot.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private Long account;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal btc_amount;
    private BigDecimal btc_price;
    private OffsetDateTime created_at;

}
