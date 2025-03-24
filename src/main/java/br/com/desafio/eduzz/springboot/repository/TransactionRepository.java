package br.com.desafio.eduzz.springboot.repository;

import br.com.desafio.eduzz.springboot.dto.TransactionDTO;
import br.com.desafio.eduzz.springboot.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    @Query("SELECT new br.com.desafio.eduzz.springboot.dto.TransactionDTO(t.id, t.account.id, t.type, t.amount, t.btc_amount, t.btc_price, t.created_at) " +
            "FROM TransactionModel t WHERE t.account.id = :id")
    List<TransactionDTO> findAllByAccountId(@Param("id") Long id);



}
