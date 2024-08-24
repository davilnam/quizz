package com.trannam.quizz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trannam.quizz.entity.MonThi;

@Repository
public interface MonThiRepository extends JpaRepository<MonThi, Long> {
    boolean existsByTenMonThi(String tenMonThi);
    Optional<MonThi> findByTenMonThi(String tenMonThi);
} 
