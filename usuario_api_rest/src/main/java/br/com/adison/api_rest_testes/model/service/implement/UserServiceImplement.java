package br.com.adison.api_rest_testes.model.service.implement;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.UserService;
import br.com.adison.api_rest_testes.model.service.exceptions.DataIntegratyViolationException;
import br.com.adison.api_rest_testes.model.service.exceptions.ObjectNotFoundException;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta classe implementa a lógica para operações relacionadas a usuários, interagindo com o
 * banco de dados por meio do UserRepository e usando o ModelMapper para mapeamento de objetos.
 * Ela lida com exceções personalizadas para situações específicas, como objetos não encontrados
 * ou violações de integridade de dados. Essa classe é marcada como um serviço (@Service) e
 * pode ser injetada em outras partes do aplicativo que precisam interagir com usuários.
 * <p>
 * A a anotação @Service, marca a classe como um serviço gerenciado pelo Spring, essa classe
 * implementa a interface UserService, por isso é obrigada a implementar seus métodos.
 */
@Service
public class UserServiceImplement implements UserService {
    /**
     * A anotação, @Autowired, da declaração, private UserRepository repository, é uma injeção de
     * dependência do UserRepository para interagir com o banco de dados.
     *<p>
     * A anotação, @Autowired, da declaração, ModelMapper mapper, é uma injeção de dependência do
     * ModelMapper para realizar mapeamentos entre objetos.
     */
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;

    /**
     * Implementação do método findById da interface UserService. Este método busca um usuário pelo
     * ID no banco de dados usando o UserRepository e lança uma exceção ObjectNotFoundException
     * se o usuário não for encontrado.
     * <p>
     * A anotação @Override é usada em métodos de uma classe para indicar que esse método está substituindo
     * um método da superclasse. Essa anotação ajuda a garantir que o método na subclasse realmente está
     * substituindo um método da superclasse, fornecendo assim uma verificação de segurança durante a compilação.
     */
    @Override
    public Users findById(Integer id) {
        Optional<Users> object = repository.findById(id);
        return object.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    /**
     * Implementação do método findAll que retorna todos os usuários do banco de dados.
     */
    public List<Users> findAll(){
        return repository.findAll();
    }

    /**
     * Implementação do método create que cria um novo usuário no banco de dados,
     * lançando uma exceção DataIntegratyViolationException se o e-mail já estiver
     * cadastrado.
     * <p>
     * A anotação @Override é usada em métodos de uma classe para indicar que esse método está substituindo
     * um método da superclasse. Essa anotação ajuda a garantir que o método na subclasse realmente está
     * substituindo um método da superclasse, fornecendo assim uma verificação de segurança durante a compilação.
     * <p>
     * Ao utilizar @Override, o compilador verifica se o método na subclasse realmente está sobrescrevendo
     * um método correspondente na superclasse. Se não estiver, o compilador gerará um erro, alertando sobre
     * o problema.
     */
    @Override
    public Users create(UserDTO object) {
        findByEmail(object);
        return repository.save(mapper.map(object, Users.class));
    }

    /**
     * Implementação do método update que atualiza um usuário no banco de dados,
     * lançando uma exceção DataIntegratyViolationException se o novo e-mail já
     * estiver cadastrado.
     * <p>
     * A anotação @Override é usada em métodos de uma classe para indicar que esse método está substituindo
     * um método da superclasse. Essa anotação ajuda a garantir que o método na subclasse realmente está
     * substituindo um método da superclasse, fornecendo assim uma verificação de segurança durante a compilação.
     * <p>
     * Ao utilizar @Override, o compilador verifica se o método na subclasse realmente está sobrescrevendo
     * um método correspondente na superclasse. Se não estiver, o compilador gerará um erro, alertando sobre
     * o problema.
     */
    @Override
    public Users update(UserDTO object) {
        findByEmail(object);
        return repository.save(mapper.map(object, Users.class));
    }

    /**
     * Implementação do método delete que exclui um usuário do banco de dados,
     * lançando uma exceção ObjectNotFoundException se o usuário não for encontrado.
     * <p>
     * A anotação @Override é usada em métodos de uma classe para indicar que esse método está substituindo
     * um método da superclasse. Essa anotação ajuda a garantir que o método na subclasse realmente está
     * substituindo um método da superclasse, fornecendo assim uma verificação de segurança durante a compilação.
     * <p>
     * Ao utilizar @Override, o compilador verifica se o método na subclasse realmente está sobrescrevendo
     * um método correspondente na superclasse. Se não estiver, o compilador gerará um erro, alertando sobre
     * o problema.
     */
    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    /**
     * Método auxiliar privado para verificar se um e-mail já está cadastrado no banco
     * de dados, lançando uma exceção DataIntegratyViolationException se o e-mail já
     * estiver associado a outro usuário.
     */
    private void findByEmail(UserDTO object){
        Optional<Users> users = repository.findByEmail(object.getEmail());
        if (users.isPresent() && !users.get().getId().equals(object.getId()))
            throw new DataIntegratyViolationException("E-mail já cadastrado!");
    }
}
