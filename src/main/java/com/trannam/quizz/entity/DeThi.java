package com.trannam.quizz.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_dethi")
public class DeThi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deThiId")
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String tenDeThi;
    private int thoiGianLamBai;
    private boolean trangThai;
    private LocalDateTime lichThi;

    @ManyToOne
    @JoinColumn(name = "monThiId", referencedColumnName = "monThiId")
    private MonThi monThi;

    @ManyToOne
    @JoinColumn(name = "kyThiId", referencedColumnName = "kyThiId")
    private KyThi kyThi;

    @OneToMany(mappedBy = "deThi", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<KetQua> results = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tbl_cauhoi_dethi",
            joinColumns = @JoinColumn(name = "deThiId"),
            inverseJoinColumns = @JoinColumn(name = "cauHoiId")
    )
    private Set<CauHoi> dsCauHoi;

}
