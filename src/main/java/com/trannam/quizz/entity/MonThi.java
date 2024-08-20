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

@Entity
@Table(name = "tbl_monthi")
public class MonThi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monThiId")
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String tenMonThi;

    @OneToMany(mappedBy = "monThi")
    private Set<DeThi> deThi = new HashSet<>();
}
