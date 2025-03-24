package br.com.desafio.eduzz.springboot.service;

import br.com.desafio.eduzz.springboot.dto.TransactionDTO;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.model.TransactionModel;
import br.com.desafio.eduzz.springboot.repository.AccountRepository;
import br.com.desafio.eduzz.springboot.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    private final ObjectMapper mapper;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, ObjectMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    public TransactionDTO createTransaction(TransactionDTO transaction) {
        AccountModel account = accountRepository.findById(transaction.getAccount())
                .orElseThrow(() -> new RuntimeException("Account not found id"+ transaction.getAccount()));
        transaction.setCreated_at(OffsetDateTime.now(ZoneId.systemDefault()));
        TransactionModel transactionModel = mapper.convertValue(transaction, TransactionModel.class);
        transactionModel.setAccount(account);
        TransactionModel savedTransaction = transactionRepository.save(transactionModel);
        TransactionDTO dto = mapper.convertValue(savedTransaction, TransactionDTO.class);

        dto.setAccount(account.getId());
        return dto;
    }

    public TransactionDTO getTransactionId(Long id) {
        Optional<TransactionModel> transaction = transactionRepository.findById(id);
        TransactionDTO dto = mapper.convertValue(transaction, TransactionDTO.class);

        if (transaction.get().getAccount() != null) {
            dto.setAccount(transaction.get().getAccount().getId());
        }
        System.out.printf("DATA: " + dto);
        return dto;

    }

    public List<TransactionDTO> getExtractById(Long id) {
        List<TransactionDTO> extract = transactionRepository.findAllByAccountId(id);

        if (extract.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transactions not found for account ID " + id);
        }

        return extract.stream()
                .map(transaction -> mapper.convertValue(transaction, TransactionDTO.class))
                .toList();
    }

}

