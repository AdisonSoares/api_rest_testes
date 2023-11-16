package br.com.adison.api_rest_testes.model.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Essa é a classe principal do projeto, por boas práticas está na camada de
 * dominio.
 * <p>
 * A anotação "@Entity" informa ao jpa que essa classe atua como entidade podendo
 * criar uma tabela em banco chamada "users", as demais anotações são do pacote
 * lombok e substituem os construtores e demais métodos de acesso padrão.
 * <p>
 * As anotações dentro da classe indicam o atributo que atua como "id" e chave
 * primária no banco, e como o banco gera automaticamente o "id", atribuindo
 * automaticamente valores incrementais à chave primária à medida que novas
 * linhas são inseridas na tabela.
 * <p>
 * A outra anotação acima de email indica que o mesmo vai ser único,
 * não aceitando valores duplicados.
 */
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
