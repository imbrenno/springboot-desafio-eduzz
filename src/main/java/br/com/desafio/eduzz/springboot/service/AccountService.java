package br.com.desafio.eduzz.springboot.service;

import br.com.desafio.eduzz.springboot.dto.AccountDTO;
import br.com.desafio.eduzz.springboot.dto.AccountWalletDTO;
import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.model.WalletModel;
import br.com.desafio.eduzz.springboot.repository.AccountRepository;
import br.com.desafio.eduzz.springboot.repository.WalletRepository;
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
    private final WalletRepository walletRepository;
    private final ObjectMapper mapper;

    public AccountService(AccountRepository accountRepository, WalletRepository walletRepository, ObjectMapper mapper) {
        this.accountRepository = accountRepository;
        this.walletRepository = walletRepository;
        this.mapper = mapper;
    }

    public List<AccountDTO> listAll() {
        return accountRepository.findAll().stream()
                .map(account -> mapper.convertValue(account, AccountDTO.class))
                .toList();
    }
  
    public AccountDTO createAccount(AccountDTO accountDTO) {
        accountDTO.setCreated_at(OffsetDateTime.now(ZoneId.systemDefault()));
        accountDTO.setStatus(AccountStatus.ACTIVE);
        AccountModel accountModel = mapper.convertValue(accountDTO, AccountModel.class);
        AccountModel createAccount = accountRepository.save(accountModel);

        WalletModel wallet = new WalletModel();
        wallet.setAccount(createAccount);
        wallet.setBalance(Double.valueOf(0));
        wallet.setCreated_at(OffsetDateTime.now(ZoneId.systemDefault()));
        walletRepository.save(wallet);

        return mapper.convertValue(createAccount, AccountDTO.class);
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
        getAccount.setUpdate_at(OffsetDateTime.now(ZoneId.systemDefault()));
        getAccount.setCanceled_at(  OffsetDateTime.now(ZoneId.systemDefault()));
        getAccount.setStatus(Optional.ofNullable(accountDTO.getStatus()).orElse(getAccount.getStatus()));

        AccountModel savedAccount = accountRepository.save(mapper.convertValue(getAccount, AccountModel.class));
        return mapper.convertValue(savedAccount, AccountDTO.class);
    }

    public AccountWalletDTO getAccountWithWalletById(Long id){
        return accountRepository.findAccountWithWalletById(id)
                .map(account -> mapper.convertValue(account, AccountWalletDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }
}
