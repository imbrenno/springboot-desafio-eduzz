package br.com.desafio.eduzz.springboot.service;

import br.com.desafio.eduzz.springboot.dto.AccountDTO;
import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

private final AccountRepository accountRepository;
private final ObjectMapper mapper;

    public AccountService(AccountRepository accountRepository, ObjectMapper mapper) {
        this.accountRepository= accountRepository;
        this.mapper = mapper;
    }

    public List<AccountDTO> listAll() {
        return accountRepository.findAll().stream()
                .map(account -> mapper.convertValue(account, AccountDTO.class))
                .toList();
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        accountDTO.setCreatedAt(OffsetDateTime.now(ZoneId.systemDefault()));
        accountDTO.setStatus(AccountStatus.ACTIVE);
        AccountModel accountModel = mapper.convertValue(accountDTO, AccountModel.class);
        AccountModel savedAccount = accountRepository.save(accountModel);
        return mapper.convertValue(savedAccount, AccountDTO.class);
    }

    public AccountDTO getById(Long id) {
        return accountRepository.findById(id)
                .map(account -> mapper.convertValue(account, AccountDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));  // Lança uma exceção
    }

    public AccountDTO updateAccountById(Long id, AccountDTO accountDTO) {
        AccountDTO getAccount = getById(id);
        getAccount.setName(Optional.ofNullable(accountDTO.getName()).orElse(getAccount.getName()));
        getAccount.setEmail(Optional.ofNullable(accountDTO.getEmail()).orElse(getAccount.getEmail()));
        getAccount.setPassword(Optional.ofNullable(accountDTO.getPassword()).orElse(getAccount.getPassword()));
        getAccount.setUpdateAt(OffsetDateTime.now(ZoneId.systemDefault()));
        getAccount.setCanceledAt(OffsetDateTime.now(ZoneId.systemDefault()));
        getAccount.setStatus(Optional.ofNullable(accountDTO.getStatus()).orElse(getAccount.getStatus()));

        AccountModel savedAccount = accountRepository.save(mapper.convertValue(getAccount, AccountModel.class));
        return mapper.convertValue(savedAccount, AccountDTO.class);
    }


}
