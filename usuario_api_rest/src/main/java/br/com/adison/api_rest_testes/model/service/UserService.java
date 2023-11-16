package br.com.adison.api_rest_testes.model.service;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;

import java.util.List;

/**
 * Esta interface UserService define um conjunto de operações relacionadas à entidade Users.
 * Cada método representa uma operação específica, como encontrar um usuário por ID, listar
 * todos os usuários, criar um novo usuário, atualizar um usuário existente e excluir um usuário.
 * Implementações concretas dessa interface fornecerão a lógica real para realizar essas operações,
 * geralmente interagindo com um banco de dados ou outro mecanismo de armazenamento de dados.
 * A utilização de uma interface fornece um contrato claro para o serviço, permitindo a fácil
 * substituição ou expansão da implementação sem afetar o código que consome o serviço.
 */
public interface UserService {
    /**
     * O método, Users findById(Integer id), retorna um objeto Users com base no ID fornecido como parâmetro.
     *<p>
     * O método, List<Users> findAll(), retorna uma lista de todos os objetos Users.
     *<p>
     * O método, Users create(UserDTO object), cria um novo objeto Users com base nos dados fornecidos
     * por um objeto UserDTO e retorna o objeto recém-criado.
     *<p>
     * O método, Users update(UserDTO object), atualiza um objeto Users com base nos dados fornecidos
     * por um objeto UserDTO e retorna o objeto atualizado.
     *<p>
     * O método, void delete(Integer id), exclui um objeto Users com base no ID fornecido como parâmetro.
     */
    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO object);
    Users update(UserDTO object);
    void delete(Integer id);
}
