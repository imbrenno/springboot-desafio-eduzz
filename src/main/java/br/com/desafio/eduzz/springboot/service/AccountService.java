package br.com.desafio.eduzz.springboot.service;


import br.com.desafio.eduzz.springboot.dto.AccountDTO;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public AccountDTO createAccount(AccountDTO accountDTO){
        AccountModel accountModel = mapper.convertValue(accountDTO, AccountModel.class);
        AccountModel savedAccount = accountRepository.save(accountModel);
        return mapper.convertValue(savedAccount, AccountDTO.class);
    }

    public AccountDTO getById(Long id){
        return accountRepository.findById(id)
                .map(account -> mapper.convertValue(account, AccountDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id " + id));  // Lança uma exceção
    }

}
