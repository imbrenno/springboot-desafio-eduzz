package br.com.desafio.eduzz.springboot.repositories;

import br.com.desafio.eduzz.springboot.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
