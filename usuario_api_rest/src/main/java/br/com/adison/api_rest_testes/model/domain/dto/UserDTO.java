package br.com.adison.api_rest_testes.model.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta classe UserDTO é uma classe de transferência de dados (DTO) que representa
 * dados de usuário/users. É anotada com o Lombok para gerar automaticamente métodos úteis
 * e possui a anotação @JsonProperty para controlar a serialização da propriedade password.
 * <p>
 * A anotação @Data do Lombok, é uma anotação composta que inclui automaticamente as
 * anotações @ToString, @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor.
 * Essas anotações são usadas para gerar automaticamente métodos como toString(), equals(),
 * hashCode(), getters e setters.
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String email;

    /**
     * Declaração de uma variável de instância chamada password do tipo String e anotada com @JsonProperty.
     * A anotação @JsonProperty com access = JsonProperty.Access.WRITE_ONLY indica que essa propriedade
     * só deve ser serializada para JSON e não deve ser desserializada (ignorada durante a conversão de
     * JSON para objeto Java). Isso é comumente usado para campos sensíveis, como senhas, quando você
     * deseja evitar que a senha seja exposta no JSON de saída.
     * <p>
     * Serializar é escrever o JSON na resposta e desserializar é obter o objeto.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
