package br.com.desafio.eduzz.springboot.service;

import br.com.desafio.eduzz.springboot.dto.WalletDTO;
import br.com.desafio.eduzz.springboot.model.WalletModel;
import br.com.desafio.eduzz.springboot.repository.WalletRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private WalletRepository walletRepository;
    private final ObjectMapper mapper;

    public WalletService(WalletRepository walletRepository, ObjectMapper mapper) {
        this.walletRepository = walletRepository;
        this.mapper = mapper;
    }


    List<WalletDTO> findAll() {
        return walletRepository.findAll().stream()
                .map(wallet -> mapper.convertValue(wallet, WalletDTO.class))
                .toList();

    }

    public WalletDTO createWallet(WalletDTO walletDTO) {
        WalletModel walletModel = mapper.convertValue(walletDTO, WalletModel.class);
        WalletModel savedWallet = walletRepository.save(walletModel);
        return mapper.convertValue(savedWallet, WalletDTO.class);
    }

    public WalletDTO getById(Long id) {
        return walletRepository.findById(id)
                .map(wallet -> mapper.convertValue(wallet, WalletDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with id " + id));
    }


}
