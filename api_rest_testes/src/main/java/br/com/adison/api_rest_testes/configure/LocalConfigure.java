package br.com.adison.api_rest_testes.configure;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfigure {
    @Autowired
    private UserRepository repository;
    @Bean
    public void startDB(){
        Users user1 = new Users(null, "Adison", "adison@gmail.com", "123");
        Users user2 = new Users(null, "Lorival", "lorival@gmail.com", "123");

        repository.saveAll(List.of(user1, user2));
    }
}
