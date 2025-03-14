package br.com.desafio.eduzz.springboot.services;


import br.com.desafio.eduzz.springboot.models.AccountModel;
import br.com.desafio.eduzz.springboot.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository= accountRepository;
    }

    public List<AccountModel> listAll() {
        return accountRepository.findAll();
    }


    public AccountModel createAccount(AccountModel account){
        return accountRepository.save(account);
    }


}
