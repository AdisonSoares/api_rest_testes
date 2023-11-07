package br.com.adison.api_rest_testes.model.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String email;

    @JsonIgnore
    private String password;
}
