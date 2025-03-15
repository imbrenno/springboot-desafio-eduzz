package br.com.desafio.eduzz.springboot.repository;

import br.com.desafio.eduzz.springboot.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
