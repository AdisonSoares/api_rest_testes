package br.com.adison.api_rest_testes.repository;

import br.com.adison.api_rest_testes.model.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
