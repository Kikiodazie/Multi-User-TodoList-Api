package com.odazie.todolistapi.data.repository;

import com.odazie.todolistapi.data.entity.JwtTokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenBlacklistRepository extends JpaRepository<JwtTokenBlacklist, Long> {

    JwtTokenBlacklist findByToken(String token);

    JwtTokenBlacklist deleteAllByExpiryAtLessThan(long time);


}
