package br.com.desafio.eduzz.springboot.controller;


import br.com.desafio.eduzz.springboot.dto.AccountDTO;
import br.com.desafio.eduzz.springboot.service.AccountService;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<AccountDTO> createc(@RequestBody AccountDTO account) {
        AccountDTO createdAccount = accountService.createAccount(account);
        System.out.printf(createdAccount.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountDTO>> listAccounts() {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getId(@PathVariable("id") Long id) {
        System.out.printf("ID: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getById(id));
    }


}
