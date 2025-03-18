package br.com.desafio.eduzz.springboot.controller;

import br.com.desafio.eduzz.springboot.dto.AccountDTO;
import br.com.desafio.eduzz.springboot.dto.AccountWalletDTO;
import br.com.desafio.eduzz.springboot.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountDTO>> listAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccountById(@PathVariable("id") Long id, @RequestBody AccountDTO accountDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccountById(id, accountDTO));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<AccountWalletDTO> getAccountWithWalletById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountWithWalletById(id));
    }




}
