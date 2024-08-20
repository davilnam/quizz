package com.trannam.quizz.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(name = "username", columnDefinition = "nvarchar(50)", unique = true)
    private String username;
    private String password;    

    @Column(columnDefinition = "nvarchar(100)")
    private String fullName;

    private String email;
    private Set<String> roles;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<KetQua> results = new HashSet<>();
}
