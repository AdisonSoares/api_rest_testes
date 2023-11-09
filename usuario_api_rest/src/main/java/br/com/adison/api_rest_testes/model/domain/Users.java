package br.com.adison.api_rest_testes.model.domain;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}
