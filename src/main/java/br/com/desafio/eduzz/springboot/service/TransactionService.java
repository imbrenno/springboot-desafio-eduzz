package br.com.desafio.eduzz.springboot.service;

import br.com.desafio.eduzz.springboot.dto.TransactionDTO;
import br.com.desafio.eduzz.springboot.exception.EntityNotFoundException;
import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.model.TransactionModel;
import br.com.desafio.eduzz.springboot.repository.AccountRepository;
import br.com.desafio.eduzz.springboot.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final ObjectMapper mapper;

    public TransactionDTO createTransaction(TransactionDTO transaction) {
        logger.info("Identification of Account ID: {}", transaction.getAccount());
        AccountModel account = accountRepository.findById(transaction.getAccount())
                .orElseThrow(() -> new EntityNotFoundException("Account not found for ID: " + transaction.getAccount()));

        logger.info("Account Identified ID: {} Customer {}", account.getId(), account.getName());
        transaction.setCreated_at(OffsetDateTime.now(ZoneId.systemDefault()));

        TransactionModel transactionModel = mapper.convertValue(transaction, TransactionModel.class);
        transactionModel.setAccount(account);

        logger.info("Creating transaction of the type: {}", transaction.getType().toString());
        TransactionModel savedTransaction = transactionRepository.save(transactionModel);
        TransactionDTO dto = mapper.convertValue(savedTransaction, TransactionDTO.class);

        logger.info("Transaction created: {}", dto.getId());
        dto.setAccount(account.getId());
        return dto;
    }


    public TransactionDTO getTransactionId(Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    TransactionDTO dto = mapper.convertValue(transaction, TransactionDTO.class);
                    dto.setAccount(transaction.getAccount() != null ? transaction.getAccount().getId() : null);
                    return dto;
                })
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found for ID: " + id));
    }


    public List<TransactionDTO> getExtractById(Long id) {
        List<TransactionDTO> extract = transactionRepository.findAllByAccountId(id);

        if (extract.isEmpty()) {
            throw new EntityNotFoundException("No transaction found for the account ID:: " + id);
        }

        return extract;
    }

}
