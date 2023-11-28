package br.com.adison.api_rest_testes.model.service.implement;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.exceptions.DataIntegratyViolationException;
import br.com.adison.api_rest_testes.model.service.exceptions.ObjectNotFoundException;
import br.com.adison.api_rest_testes.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImplement service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private Users users;
    private UserDTO userDTO;
    private Optional<Users> optionalUser;
    public static final Integer  ID = 1;
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

    /**
     * @Funcionalidade_original_testada: Retorna um objeto Users com um id especificado nos parâmetros
     * ou lança uma exception caso nao tenha no banco. (findById)<p>
     *
     * @Nomeação: Esse método é para testar o findById, porém seu nome deve ser uma descrição
     * do teste que vai ser feito.<p>
     *
     * @Descrição: Quando executar FindById retorna uma instância de usuário,
     * (whenRunnigFindByIdThenReturnAnUserInstance).<p>
     *
     * @Explicação: Na primeira linha do teste é mockado os dados passados no parâmetro,
     * quando chamar o findById de repository passando qualquer número inteiro como id
     * então retorne o objeto optionalUser.<p>
     * Na segunda linha é chamado o método da classe testada, seguindo as regras do mock acima,
     * e guardado seu objeto em "response".<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: é feita uma comparação para verificar se essa classe guardada, response.getClass(),
     * está retornando um objeto do tipo "Users.class".<p>
     * - TERCEIRA: é feita uma comparação para verificar se o id gerado, response.getId(), corresponde
     * ao ID passado como parâmetro e criado nessa classe.<p>
     * - QUARTA: é feita uma comparação para verificar se o id gerado, response.getName(), corresponde
     * ao NOME_TESTE passado como parâmetro e criado nessa classe.<p>
     * - QUINTA: é feita uma comparação para verificar se o nome gerado, response.getEmail(), corresponde
     * ao EMAIL passado como parâmetro e criado nessa classe.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Recomendação: Para que o teste seja completo é preciso verificar todos os atributos, quanto mais
     * atributos testados mais segurança seguro o sistema e os códigos serão.
     */
    @Test
    void whenRunnigFindByIdThenReturnAnUserInstance() {
        Mockito
                .when(repository
                        .findById(Mockito.anyInt()))
                .thenReturn(optionalUser);
        Users response = service.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME_TESTE, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    /**
     * @Funcionalidade_original_testada: Retorna uma exception caso não encontre o objeto buscado,
     * e exibe uma mensagem de erro. (findById)<p>
     *
     * @Nomeação: Esse método é para testar o lançamento de exception do findById, porém seu nome deve
     * ser uma descrição do teste que vai ser feito.<p>
     *
     * @Descrição: Quando executar FindById retorna uma exception de objeto não encontrado,
     * (whenFindByIdThenReturnAnObjectNotFoundException).<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o objeto retornado é igual a classe ObjectNotFoundException.class.<p>
     * - SEGUNDA: verifica se a mensagem retornada é igual a mensagem capturada na exception.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     */
    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito
                .when(repository
                        .findById(Mockito.anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findById(ID);
        } catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    /**
     * @Funcionalidade_original_testada: Retorna uma lista com todos os objetos users guardados
     * no banco. (findAll)<p>
     *
     * @Nomeação: Esse método é para testar o retorno da funcionalidade findAll, porém seu nome deve
     * ser uma descrição do teste que vai ser feito.<p>
     *
     * @Descrição: Quando executar findAll retorna uma lista de users,
     * (whenRunnigFindAllThenReturnAnListOfUsers).<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se o tamanho da lista corresponde a 1, pelo valor mockado ter sido apenas 1.<p>
     * - TERCEIRA: verifica se a classe guardada na lista mockada no primeiro indice corresponde a Users.class.<p>
     * - QUARTA: verifica se o id guardado na lista mockada no primeiro indice corresponde a ID.<p>
     * - QUINTA: verifica se o nome guardado na lista mockada no primeiro indice corresponde a NOME_TESTE.<p>
     * - SEXTA: verifica se o email guardado na lista mockada no primeiro indice corresponde a EMAIL.<p>
     * - SETIMA: verifica se a senha guardada na lista mockada no primeiro indice corresponde a PASSWORD.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     */
    @Test
    void whenRunnigFindAllThenReturnAnListOfUsers() {
        Mockito
                .when(repository
                        .findAll())
                .thenReturn(List.of(users));
        List<Users> response = service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(Users.class, response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(NOME_TESTE, response.get(INDEX).getName());
        Assertions.assertEquals(EMAIL, response.get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Cria um objeto um novo usuário no banco, ou seja, um
     * objeto do tipo users, caso não tenha sucesso lança uma exception por testar email duplicado.
     * (create).<p>
     *
     * @Nomeação: Esse método é para testar a criação de um objeto do tipo users no banco.<p>
     *
     * @Descrição: Quando executar create retorna sucesso, guardando um objeto tipo users,
     * (whenRunnigCreateThenReturnSucess).<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se a classe guardada no mocks corresponde a Users.class.<p>
     * - TERCEIRA: verifica se o id mockado corresponde a ID.<p>
     * - QUARTA: verifica se o nome mockado corresponde a NOME_TESTE.<p>
     * - QUINTA: verifica se o email mockado corresponde a EMAIL.<p>
     * - SEXTA: verifica se a senha mockada corresponde a PASSWORD.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     */
    @Test
    void whenRunnigCreateThenReturnSucess() {
        Mockito
                .when(repository
                        .save(any()))
                .thenReturn(users);
        Users response = service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME_TESTE, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Cria um objeto, um novo usuário no banco, ou seja, um
     * objeto do tipo users, caso não tenha sucesso lança uma exception por testar email duplicado.
     * (create e findByEmail).<p>
     *
     * @Nomeação: Esse método é para testar a exception jogada quando não cria um objeto do tipo
     * users no banco.<p>
     *
     * @Descrição: Quando executar create retorna uma exception, não guardando o objeto no banco,
     * (whenRunnigCreateThenReturnAnDataIntegratyViolationException).<p>
     *
     * @Try:
     * - PRIMEIRA: acrescenta um ID diferente do mockado para verificar id diferente e gerar a exception de email duplicado.<p>
     * - SEGUNDA: executa o método create passando o objeto userDTO gerando a exception mockada.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se a exception capturada é igual a DataIntegratyViolationException.class.<p>
     * - SEGUNDA: verifica se a mensagem do email não cadastrado correponde ao que está vindo na mensagem
     * da exception lançada.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     */
    @Test
    void whenRunnigCreateThenReturnAnDataIntegratyViolationException() {
        Mockito
                .when(repository
                        .findByEmail(anyString()))
                .thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex){
            Assertions.assertEquals(DataIntegratyViolationException.class, ex.getClass());
            Assertions.assertEquals("E-mail já cadastrado!", ex.getMessage());
        }
    }

    /**
     * @Funcionalidade_original_testada: Atualiza um objeto, um usuário existente no banco, ou seja,
     * um objeto do tipo users, caso tenha email duplicado lança uma exception pelo teste feito antes.
     * (update).<p>
     *
     * @Nomeação: Esse método é para a atualização de um objeto users no banco.<p>
     *
     * @Descrição: Quando executar update atualiza o users com sucesso no banco,
     * (whenRunnigUpdateThenReturnSucess).<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se a classe guardada no mocks corresponde a Users.class.<p>
     * - TERCEIRA: verifica se o id mockado corresponde a ID.<p>
     * - QUARTA: verifica se o nome mockado corresponde a NOME_TESTE.<p>
     * - QUINTA: verifica se o email mockado corresponde a EMAIL.<p>
     * - SEXTA: verifica se a senha mockada corresponde a PASSWORD.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     */
    @Test
    void whenRunnigUpdateThenReturnSucess() {
        Mockito
                .when(repository
                        .save(any()))
                .thenReturn(users);
        Users response = service.update(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME_TESTE, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
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