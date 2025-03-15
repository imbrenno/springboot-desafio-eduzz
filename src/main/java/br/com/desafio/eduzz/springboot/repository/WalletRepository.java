package br.com.desafio.eduzz.springboot.repository;

import br.com.desafio.eduzz.springboot.model.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletModel, Long> {
}
