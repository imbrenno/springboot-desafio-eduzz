package br.com.desafio.eduzz.springboot.controller;

import br.com.desafio.eduzz.springboot.dto.AccountWalletDTO;
import br.com.desafio.eduzz.springboot.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final AccountService accountService;

    public WalletController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<AccountWalletDTO> getAccountWithWalletById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountWithWalletById(id));
    }


}
