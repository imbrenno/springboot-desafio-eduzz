package br.com.desafio.eduzz.springboot.repository;

import br.com.desafio.eduzz.springboot.dto.AccountWalletDTO;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
    @Query("SELECT new br.com.desafio.eduzz.springboot.dto.AccountWalletDTO(a.id, a.name, a.email, a.status, w.balance) " +
            "FROM AccountModel a JOIN a.wallet w WHERE a.id = :id")
    Optional<AccountWalletDTO> findAccountWithWalletById(@Param("id") Long id);


}
