package br.com.adison.api_rest_testes.model.service;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO object);
    Users update(UserDTO object);
}
