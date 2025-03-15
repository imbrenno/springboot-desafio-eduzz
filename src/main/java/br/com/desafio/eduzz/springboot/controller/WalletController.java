package br.com.desafio.eduzz.springboot.controller;

import br.com.desafio.eduzz.springboot.model.AccountModel;
import br.com.desafio.eduzz.springboot.model.WalletModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletController walletController;



}
