package com.example.demon.Registraiton;

import com.example.demon.AppUser.AppUser;
import com.example.demon.AppUser.AppUserRepository;
import com.example.demon.Registraiton.Token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")

public class RegistraitonController {

    private final RegistrationService registrationService;

    public RegistraitonController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest user) {
        registrationService.register(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }
}
