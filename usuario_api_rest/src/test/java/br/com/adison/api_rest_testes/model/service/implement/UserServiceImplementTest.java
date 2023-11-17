package br.com.adison.api_rest_testes.model.service.implement;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @Resumo: Classe criada para testar os métodos da classe UserServiceImplement, usando os recursos da Ide
 * para criar o molde de cada teste.<p>
 *
 * @Instâncias:
 * -service (objeto da classe testada para implementar seus métodos)<p>
 * -repository(objeto da classe de comunicação com o banco)<p>
 * -mapper(objeto da classe de conversão do banco com a classe)<p>
 * -userDTO(objeto da classe de transferência de dados)<p>
 * -user(objeto da classe domain)<p>
 * -optionalUser(objeto da classe optional que lida com dados do tipo user com possíbilidade de serem nulos)<p>
 * -ID, NOME_TESTE, EMAIL, PASSWORD (constantes iniciadas para preencher construtores dos objetos user).<p>
 *
 * @Anotações:
 * -@SpringBootTest(indica ao spring que essa classe é de testes)<p>
 * -@InjectMocks(cria uma instância real do objeto instanciado)<p>
 * -@Mock(cria uma instância falsa do objeto instanciado)<p>
 * -@Before (métodos que executam antes de tudo)<p>
 * -@Test (métodos testados).<p>
 *
 */
@SpringBootTest
class UserServiceImplementTest {
    @InjectMocks
    private UserServiceImplement service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private Users users;
    private UserDTO userDTO;
    private Optional<Users> optionalUser;
    public static final int ID = 1;
    public static final String NOME_TESTE = "nomeTeste";
    public static final String EMAIL = "emailTeste@gmail.com";
    public static final String PASSWORD = "123";

    /**
     * @Finalidade: Método criado para inicializar os mocks desta/this classe,
     * e iniciar o método que feito para criar construtores aos objetos user
     * para não acontecer um lançamento de nullpointerexception.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    /**
     * @Finalidade: Método de inicialização dos objetos users, caso não sejam iniciados
     * ao serem usados acontece uma exception do tipo nullpointerexception.<p>
     * No caso do objeto optional seu construtor vai ser de/of um objeto users.
     */
    private void startUser(){
        users = new Users(ID, NOME_TESTE, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME_TESTE, EMAIL, PASSWORD);
        optionalUser = Optional.of(new Users(ID, NOME_TESTE, EMAIL, PASSWORD));
    }
}