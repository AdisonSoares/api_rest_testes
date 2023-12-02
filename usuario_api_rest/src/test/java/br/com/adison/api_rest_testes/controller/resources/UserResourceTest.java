package br.com.adison.api_rest_testes.controller.resources;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.implement.UserServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserResourceTest {
    public static final Integer  ID = 1;
    public static final String NOME_TESTE = "nomeTeste";
    public static final String EMAIL = "emailTeste@gmail.com";
    public static final String PASSWORD = "123";

    private Users users;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserServiceImplement service;

    /**
     * @Finalidade: Método criado para inicializar os mocks desta/this classe,
     * e iniciar o método que foi feito para criar construtores aos objetos user
     * para não acontecer um lançamento de nullpointerexception.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "findById" que retorna um objeto
     * "ResponseEntity" de acordo com um id especificado nos parâmetros ou lança uma exception caso nao
     * tenha no banco. (findById)<p>
     *
     * @Nomeação: Quando executar "FindById" retorna um objeto "ResponseEntity" com sucesso, mapeando um
     * objeto "users" para "userDTO". (whenFindByIdThenReturnSucess).<p>
     *
     * @Mockito:
     * - PRIMEIRA: É mockado o chamado do método "findById" passando qualquer/any número de Id e retornando
     * um objeto "users" com sucesso.<p>
     * - SEGUNDA: É mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o método "findById" da classe "UserResource" passando o ID criado é possível
     * armazenar seu retorno "ResponseEntity" do tipo "UserDTO" para testar as assertivas.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se o corpo/body do response está nulo.<p>
     * - TERCEIRA: verifica se a classe do response corresponde a uma classe "ResponseEntity.class".<p>
     * - QUARTA: verifica se a classe do corpo do response corresponde a uma classe "UserDTO.class".<p>
     * - QUINTA: verifica se o id do corpo do response corresponde ao ID estático passado.<p>
     * - SEXTA: verifica se o nome do corpo do response corresponde ao NOME_TESTE estático passado.<p>
     * - SETIMA: verifica se o email do corpo do response corresponde ao EMAIL estático passado.<p>
     * - OITAVA: verifica se a senha do corpo do response corresponde ao PASSWORD estático passado.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Recomendação: Para que o teste seja completo é preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os códigos serão.
     */
    @Test
    void whenFindByIdThenReturnSucess() {
        Mockito
                .when(service
                        .findById(Mockito.anyInt()))
                .thenReturn(users);

        Mockito
                .when(mapper
                        .map(any(), any()))
                .thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME_TESTE, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
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
     */
    private void startUser(){
        users = new Users(ID, NOME_TESTE, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME_TESTE, EMAIL, PASSWORD);
    }
}