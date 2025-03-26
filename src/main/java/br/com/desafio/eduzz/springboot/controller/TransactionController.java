package br.com.desafio.eduzz.springboot.controller;

import br.com.desafio.eduzz.springboot.dto.TransactionDTO;
import br.com.desafio.eduzz.springboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    //    Endpoints
    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> createDeposit(@RequestBody TransactionDTO deposit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(deposit));
    }

    //  Compra

    //  Venda

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionId(id));
    }

    @GetMapping("/extract/{id}")
    public ResponseEntity<List<TransactionDTO>> getExtractByAccountId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getExtractById(id));
    }




}
