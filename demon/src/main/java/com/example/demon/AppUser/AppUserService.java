package com.example.demon.AppUser;

import com.example.demon.Registraiton.Token.ConfirmationToken;
import com.example.demon.Registraiton.Token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public AppUserService(AppUserRepository appUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder
    , ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return appUserRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public String signUpUser(AppUser appUser) {
     boolean exists = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();
     if (exists) {
         throw new IllegalStateException("email already exists");
     }
       String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
         appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
     //TODO: send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser
        );
        confirmationTokenService.save(confirmationToken);

        return token;
        //TODO: send email

    }


    public int updateEnabledStatus(String email) {
     return  appUserRepository.updateEnabledStatus(email);
    }
}
