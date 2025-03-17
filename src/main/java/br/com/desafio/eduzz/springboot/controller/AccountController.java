package br.com.desafio.eduzz.springboot.controller;


import br.com.desafio.eduzz.springboot.dto.AccountDTO;
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
        AccountDTO createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
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


}
