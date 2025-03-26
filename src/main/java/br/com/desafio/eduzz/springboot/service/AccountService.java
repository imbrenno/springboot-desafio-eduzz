package br.com.desafio.eduzz.springboot.service;

import br.com.desafio.eduzz.springboot.dto.AccountDTO;
import br.com.desafio.eduzz.springboot.dto.AccountWalletDTO;
import br.com.desafio.eduzz.springboot.enums.AccountStatus;
import br.com.desafio.eduzz.springboot.exception.EntityNotFoundException;
import br.com.desafio.eduzz.springboot.exception.InvalidInputException;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.model.WalletModel;
import br.com.desafio.eduzz.springboot.repository.AccountRepository;
import br.com.desafio.eduzz.springboot.repository.WalletRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;
    private final ObjectMapper mapper;

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
                .orElseThrow(() -> new EntityNotFoundException("Account not found id: " + id));  // Lança uma exceção
    }

    public AccountDTO updateAccountById(Long id, AccountDTO accountDTO) {
        AccountModel account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found id: " + id));

        if (Objects.equals(account.getStatus().getDescription(), "canceled")){
            throw new InvalidInputException("Account cannot be updated");
        }

        Optional.ofNullable(accountDTO.getName()).ifPresent(account::setName);
        Optional.ofNullable(accountDTO.getEmail()).ifPresent(account::setEmail);
        Optional.ofNullable(accountDTO.getPassword()).ifPresent(account::setPassword);
        Optional.ofNullable(accountDTO.getStatus()).ifPresent(newStatus -> {
            account.setStatus(newStatus);
            if ("canceled".equalsIgnoreCase(newStatus.name())) {
                account.setCanceled_at(OffsetDateTime.now(ZoneId.systemDefault()));
            }
        });
        account.setUpdate_at(OffsetDateTime.now(ZoneId.systemDefault()));
        return mapper.convertValue(accountRepository.save(account), AccountDTO.class);
    }

    public AccountWalletDTO getAccountWithWalletById(Long id){
        return accountRepository.findAccountWithWalletById(id)
                .map(account -> mapper.convertValue(account, AccountWalletDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Account not found id: " + id));
    }
}
