package br.com.adison.api_rest_testes.repository;

import br.com.adison.api_rest_testes.model.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Esta interface UserRepository estende JpaRepository, fornecendo métodos padrão
 * para operações de banco de dados para a entidade Users. Além disso, ela declara
 * um método personalizado findByEmail que permite buscar um usuário pelo seu e-mail.
 * O uso de Optional é uma prática comum para indicar a possibilidade de que nenhum
 * usuário seja encontrado com o e-mail fornecido.
 * <p>
 * A anotação @Repository é uma anotação do Spring Framework utilizada para indicar
 * que uma classe é um componente de repositório, ou seja, ela desempenha o papel
 * de um repositório de dados. Essa anotação é comumente usada em classes que
 * acessam o banco de dados ou qualquer outra fonte de dados.
 * <p>
 * O método, public interface UserRepository extends JpaRepository<Users, Integer>
 * é a Declaração da interface UserRepository, que estende JpaRepository. Isso
 * significa que UserRepository herda todos os métodos fornecidos por JpaRepository
 * para a entidade Users, usando um identificador do tipo Integer.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    /**
     *A linha, Optional<Users> findByEmail(String email), é uma consulta
     * derivada, onde o Spring Data JPA irá automaticamente gerar uma consulta
     * SQL com base no nome do método.
     * <p>
     * Neste caso, o método findByEmail busca um usuário pelo seu campo de e-mail
     * e retorna um objeto Optional<Users>. O uso de Optional sugere que o usuário
     * pode ou não ser encontrado.
     */
    Optional<Users> findByEmail(String email);
}
