package com.trannam.quizz.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_kythi")
public class KyThi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kyThiId")
    private Long id;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String tenKyThi;

    @Column(columnDefinition = "nvarchar(500)")
    private String moTa;

    @OneToMany(mappedBy = "kyThi")
    private Set<DeThi> dsDeThi = new HashSet<>(); 
}

