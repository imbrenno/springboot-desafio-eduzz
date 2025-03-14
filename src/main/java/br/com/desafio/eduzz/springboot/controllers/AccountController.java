package br.com.desafio.eduzz.springboot.controllers;


import br.com.desafio.eduzz.springboot.models.AccountModel;
import br.com.desafio.eduzz.springboot.services.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Validated
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public List<AccountModel> listUsers() {
        return accountService.listAll();
    }

    @PostMapping("/create")
    public AccountModel createAccount(@RequestBody AccountModel account) {
        System.out.println("DATA: "+ account.getName());
        return accountService.createAccount(account);
    }


}
