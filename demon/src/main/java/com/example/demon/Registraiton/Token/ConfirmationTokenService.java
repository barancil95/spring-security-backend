package com.example.demon.Registraiton.Token;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;



    public ConfirmationTokenService(ConfirmationTokenRepo confirmationTokenRepo) {
        this.confirmationTokenRepo = confirmationTokenRepo;
    }

    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepo.save(confirmationToken);
    }

    public Optional<ConfirmationToken>  getToken(String token) {
      return  confirmationTokenRepo.findByToken(token);

    }
    public int setConfirmedAt(String token) {
          return  confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());

    }
}
