package com.example.demon.Registraiton.Token;

import com.example.demon.AppUser.AppUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class ConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1 // Artış miktarı
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAtTime;
    @Column(nullable = false)
    private LocalDateTime expiredAtTime;
    @Column(nullable = true)
    private LocalDateTime confirmedAtTime;
    @ManyToOne
    @JoinColumn(name = "app_user_id",
    nullable = false)
    private AppUser appUser;

    public ConfirmationToken( String token, LocalDateTime createdAtTime,
                              LocalDateTime expiredAtTime,
                              AppUser appUser) {

        this.token = token;
        this.createdAtTime = createdAtTime;
        this.expiredAtTime = expiredAtTime;
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    public ConfirmationToken() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiredAtTime() {
        return expiredAtTime;
    }

    public void setExpiredAtTime(LocalDateTime expiredAtTime) {
        this.expiredAtTime = expiredAtTime;
    }

    public LocalDateTime getCreatedAtTime() {
        return createdAtTime;
    }

    public void setCreatedAtTime(LocalDateTime createdAtTime) {
        this.createdAtTime = createdAtTime;
    }

    public LocalDateTime getConfirmedAtTime() {
        return confirmedAtTime;
    }

    public void setConfirmedAtTime(LocalDateTime confirmedAtTime) {
        this.confirmedAtTime = confirmedAtTime;
    }
}
