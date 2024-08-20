package com.trannam.quizz.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "tbl_cauhoi")
public class CauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cauHoiId")
    private Long id;

    @Column(columnDefinition = "nvarchar(500)")
    private String deBai;

    private String a;
    private String b;
    private String c;
    private String d;
    private String dapAn;

    @ManyToMany(mappedBy = "dsCauHoi")
    private Set<DeThi> dsDeThi;
}
