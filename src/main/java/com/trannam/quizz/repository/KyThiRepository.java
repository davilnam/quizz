package com.trannam.quizz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trannam.quizz.entity.KyThi;

@Repository
public interface KyThiRepository extends JpaRepository<KyThi, Long> {
    boolean existsByTenKyThi(String tenKyThi);
    Optional<KyThi> findByTenKyThi(String tenKyThi);
}
