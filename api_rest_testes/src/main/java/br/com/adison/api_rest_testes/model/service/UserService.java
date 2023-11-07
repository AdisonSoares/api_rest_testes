package br.com.adison.api_rest_testes.model.service;

import br.com.adison.api_rest_testes.model.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();
}
