package br.com.adison.api_rest_testes.configure;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * Este é um exemplo de uma classe de configuração em um aplicativo Spring Boot,
 * ela é projetada para ser ativada quando o perfil "local" estiver ativo.
 * <p>
 * Quando o aplicativo é iniciado, o método startDB é chamado automaticamente, criando
 * e salvando alguns usuários fictícios no banco de dados. Isso é útil, por exemplo,
 * durante o desenvolvimento local quando você deseja ter dados iniciais no banco
 * de dados para testar a aplicação.
 * <p>
 * A anotação @Configuration indica que a classe é uma classe de configuração do Spring,
 * que é usada para configurar beans no contexto da aplicação.
 * <p>
 * A anotação @Profile("local") indica que esta configuração deve ser ativada apenas quando
 * o perfil "local" estiver ativo. Os perfis no Spring são uma maneira de personalizar a
 * aplicação para diferentes ambientes ou situações.
 */
@Configuration
@Profile("local")
public class LocalConfigure {
    /**
     * A linha de código, @Autowired private UserRepository repository, injeta (autowires) uma
     * instância de UserRepository na classe. O UserRepository é uma interface que estende
     * JpaRepository ou uma classe que implementa métodos para interagir com a camada de
     * persistência (como um repositório JPA).
     * <p>
     * A anotação,@Bean, indica que o método startDB é um método de configuração de bean.
     * Neste contexto, o bean é uma instância gerenciada pelo Spring.
     * <p>
     * O método, public void startDB(), indica que será executado para iniciar o banco de dados.
     * É configurado como um bean para ser chamado automaticamente quando o aplicativo é inicializado.
     * <p>
     * Dentro do método, são criados dois objetos Users (uma entidade de modelo que representa usuários)
     * com dados fictícios.
     * <p>
     * O trecho de código, repository.saveAll(List.of(user1, user2)), usa o UserRepository para salvar
     * os objetos Users no banco de dados, saveAll é um método fornecido pelo Spring Data JPA que salva
     * uma lista de entidades no banco de dados.
     */
    @Autowired
    private UserRepository repository;
    @Bean
    public void startDB(){
        Users user1 = new Users(null, "Adison", "adison@gmail.com", "123");
        Users user2 = new Users(null, "Lorival", "lorival@gmail.com", "123");

        repository.saveAll(List.of(user1, user2));
    }
}
