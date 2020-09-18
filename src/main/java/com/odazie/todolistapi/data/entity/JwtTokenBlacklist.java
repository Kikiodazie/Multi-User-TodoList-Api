package com.odazie.todolistapi.data.entity;


import javax.persistence.*;

@Entity
@Table(name = "token_blacklist")
public class JwtTokenBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;


    @Column(name = "token")
    private String token;


    @Column(name = "expiry_at")
    private long expiryAt;


    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(long expiryAt) {
        this.expiryAt = expiryAt;
    }
}
