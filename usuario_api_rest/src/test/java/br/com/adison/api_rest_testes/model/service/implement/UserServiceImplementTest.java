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

import static org.mockito.ArgumentMatchers.*;

/**
 * @Resumo: Classe criada para testar os metodos de UserServiceImplement, usando os recursos da Ide
 * para criar os moldes de cada teste.<p>
 *
 * @Instancias:
 * * user (objeto da classe domain)<p>
 * * userDTO (objeto da classe de transferencia de dados)<p>
 * * optionalUser (objeto da classe optional que lida com dados do tipo user com possíbilidade de serem nulos)<p>
 * * service (objeto da classe testada para implementar seus metodos)<p>
 * * repository (objeto da classe de comunicacao com o banco)<p>
 * * mapper (objeto da classe de conversao do banco com a classe)<p>
 * * INDEX, ID, NOME, EMAIL, PASSWORD, EMAIL, OBJETO_NAO_ENCONTRADO
 * (constantes iniciadas para preencher construtores dos objetos user).<p>
 *
 * @Anotacoes:
 * * @SpringBootTest (indica ao spring que essa classe eh de testes)<p>
 * * @InjectMocks (cria uma instancia real do objeto instanciado)<p>
 * * @Mock (cria uma instancia falsa do objeto instanciado)<p>
 * * @BeforeEach (metodos que executam antes de tudo)<p>
 * * @Test (metodos testados).<p>
 *
 */
@SpringBootTest
class UserServiceImplementTest {

    private Users users;
    private UserDTO userDTO;
    private Optional<Users> optionalUser;
    @InjectMocks
    private UserServiceImplement service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    public static final int INDEX = 0;
    public static final Integer ID = 1;
    public static final String NOME = "nomeTeste";
    public static final String PASSWORD = "123";
    public static final String EMAIL = "emailTeste@gmail.com";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";

    /**
     * @Finalidade: Metodo criado para inicializar os mocks desta/this classe,
     * e iniciar o metodo que foi feito para criar construtores aos objetos user
     * para nao acontecer um lançamento de nullpointerexception.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    /**
     * @Funcionalidade_original_testada: Retorna um objeto Users com um id especificado nos parametros
     * ou lanca uma exception caso nao tenha no banco. (findById)<p>
     *
     * @Nomeacao: Quando executar FindById retorna uma instancia de usuario,
     * (whenRunnigFindByIdThenReturnAnUserInstance).<p>
     *
     * @Mockito:
     * * PRIMEIRA: eh mockado o retorno  do metodo findById de repository passando
     * qualquer numero inteiro como id, entao retornando o objeto optionalUser.<p>
     *
     * @Response: Eh chamado o metodo "findById" da classe testada passando o ID estatico, guardando seu
     * objeto em "response" para testar.<p>
     *
     * @Assertions:
     * * PRIMEIRA: afirma que o response esta nulo.<p>
     * * SEGUNDA: afirma que a classe do response coresponse a "Users.class".<p>
     * * TERCEIRA: afirma que o id do response corresponde ao ID estatico passado.<p>
     * * QUARTA: afirma que o nome do response corresponde ao NOME_TESTE estatico passado.<p>
     * * QUINTA: afirma que o email do response corresponde ao EMAIL estatico passado.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
     * @Recomendacao: Para que o teste seja completo e preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os codigos serao.
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
        Assertions.assertEquals(NOME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    /**
     * @Funcionalidade_original_testada: Retorna uma exception caso nao encontre o objeto buscado,
     * e exibe uma mensagem de erro. (findById)<p>
     *
     * @Descricao: Quando executar FindById retorna uma exception de objeto nao encontrado,
     * (whenFindByIdThenReturnAnObjectNotFoundException).<p>
     *
     * @Mockito:
     * * PRIMEIRA: eh mockado o retorno do findById de repository passando qualquer numero inteiro como
     * id entao lance um "ObjectNotFoundException" com a mensagem "Objeto não encontrado!" que vai ser
     * capturado em um try/catch.<p>
     *
     * @Try: eh feito o chamado do metodo findById da classe "UserServiceImplement", passando o ID estatico,
     * para capturar sua exception para fazer as afirmativas.
     *
     * @Assertions:
     * * PRIMEIRA: afirma que o objeto retornado eh igual a classe ObjectNotFoundException.class.<p>
     * * SEGUNDA: afirma que a mensagem retornada eh igual a mensagem "Objeto não encontrado!".<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
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
     * @Nomeacao: Quando executar "findAll" entao retorne uma lista de objetos do tipo "Users".
     * (whenRunnigFindAllThenReturnAnListOfUsers)<p>
     *
     * @Mockito:
     * * PRIMEIRA: eh mockado os dados passados quando chamar o findAll de repository entao retornando uma lista de
     * objetos Users.<p>
     *
     * @Response: Eh chamado o metodo "findAll" da classe testada passando, guardando seu objeto em "response"
     * para testar.<p>
     *
     * @Assertions:
     * * PRIMEIRA: afirma que o response nao esta nulo.<p>
     * * SEGUNDA: afirma que o tamanho da lista do response corresponde a 1, pelo valor mockado ter sido apenas 1.<p>
     * * TERCEIRA: afirma que a classe guardada na lista do response no primeiro indice corresponde a Users.class.<p>
     * * QUARTA: afirma que o id guardado na lista do response no primeiro indice corresponde a ID.<p>
     * * QUINTA: afirma que o nome guardado na lista do response no primeiro indice corresponde a NOME_TESTE.<p>
     * * SEXTA: afirma que o email guardado na lista do response no primeiro indice corresponde a EMAIL.<p>
     * * SETIMA: afirma que a senha guardada na lista do response no primeiro indice corresponde a PASSWORD.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
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
        Assertions.assertEquals(NOME, response.get(INDEX).getName());
        Assertions.assertEquals(EMAIL, response.get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Cria um objeto um novo usuario no banco, ou seja, um
     * objeto do tipo users, caso nao tenha sucesso lanca uma exception por testar email duplicado.
     * (create).<p>
     *
     * @Nomeacao: Quando executar create retorna sucesso, guardando um objeto tipo users,
     * (whenRunnigCreateThenReturnSucess).<p>
     *
     * @Mockito:
     * * PRIMEIRA: eh mockado o retorno  do metodo findById de repository passando
     * qualquer numero inteiro como id entao retornando o objeto optionalUser.<p>
     *
     * @Response: Eh chamado o metodo "create" da classe  UserServiceImplement testada passando o userDTO
     * estatico, guardando seu objeto em "response" para testar.<p>
     *
     * @Assertions:
     * * PRIMEIRA: afirma que o response nao esta nulo.<p>
     * * SEGUNDA: afirma que a classe do response corresponde a Users.class.<p>
     * * TERCEIRA: afirma que o id do response corresponde a ID.<p>
     * * QUARTA: afirma que o nome do response corresponde a NOME_TESTE.<p>
     * * QUINTA: afirma que o email do response corresponde a EMAIL.<p>
     * * SEXTA: afirma que a senha do response corresponde a PASSWORD.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
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
        Assertions.assertEquals(NOME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Cria um objeto, um novo usuario no banco, ou seja, um
     * objeto do tipo users, caso nao tenha sucesso lanca uma exception por testar email duplicado.
     * (create e findByEmail).<p>
     *
     * @Nomeacao: Quando executar create retorna uma exception, nao guardando o objeto no banco,
     * (whenRunnigCreateThenReturnAnDataIntegratyViolationException).<p>
     *
     * @Mockito:
     * * PRIMEIRA: eh mockado o retorno do metodo "findByEmail" de repository passando
     * qualquer mensagem String, entao retornando o objeto optionalUser.<p>
     *
     * @Try:
     * * PRIMEIRA: acrescenta um ID diferente do mockado para verificar id diferente e gerar a exception de email duplicado.<p>
     * * SEGUNDA: executa o metodo create passando o objeto userDTO gerando a exception mockada.<p>
     *
     * @Assertions:
     * * PRIMEIRA: afirma que a exception capturada eh igual a DataIntegratyViolationException.class.<p>
     * * SEGUNDA: afirma que a mensagem do email nao cadastrado correponde ao que esta vindo na mensagem
     * da exception lancada.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
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
     * @Nomeacao: Quando executar update atualiza o users com sucesso no banco,
     * (whenRunnigUpdateThenReturnSucess).<p>
     *
     * @Mockito:
     * * PRIMEIRA: eh mockado o retorno do metodo update de repository passando
     * qualquer numero inteiro como id entao retorne o objeto sers.<p>
     *
     * @Response: Eh chamado o metodo "update" da classe UserServiceImplement testada ,passando o userDTO estatico,
     * guardando seu objeto em "response" para testar.<p>
     *
     * @Assertions:
     * * PRIMEIRA: afirma que o response nao está nulo.<p>
     * * SEGUNDA: afirma que a classe guardada no mocks corresponde a Users.class.<p>
     * * TERCEIRA: afirma que o id mockado corresponde a ID.<p>
     * * QUARTA: afirma que o nome mockado corresponde a NOME_TESTE.<p>
     * * QUINTA: afirma que o email mockado corresponde a EMAIL.<p>
     * * SEXTA: afirma que a senha mockada corresponde a PASSWORD.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
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
        Assertions.assertEquals(NOME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Atualiza um objeto, usuario existente no banco, ou seja, um
     * objeto do tipo users, caso nao tenha sucesso lança uma exception por testar email duplicado.
     * (update e findByEmail).<p>
     *
     * @Nomeacao: Quando executar update retorna uma exception, nao atualizando o objeto no banco,
     * (whenRunnigCreateThenReturnAnDataIntegratyViolationException).<p>
     *
     * @Try:
     * * PRIMEIRA: acrescenta um ID diferente do mockado para verificar o id diferente e gerar a exception
     * de email duplicado.<p>
     * * SEGUNDA: executa o metodo update passando o objeto userDTO gerando a exception mockada.<p>
     *
     * @Assertions:
     * * PRIMEIRA: afirma que a exception capturada eh igual a DataIntegratyViolationException.class.<p>
     * * SEGUNDA: afirma que a mensagem do email nao cadastrado correponde ao que esta vindo na mensagem
     * da exception lançada.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     */
    @Test
    void whenRunnigUpdateThenReturnAnDataIntegratyViolationException() {
        Mockito
                .when(repository
                        .findByEmail(anyString()))
                .thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.update(userDTO);
        }catch (Exception ex){
            Assertions.assertEquals(DataIntegratyViolationException.class, ex.getClass());
            Assertions.assertEquals("E-mail já cadastrado!", ex.getMessage());
        }
    }

/**
     * @Funcionalidade_original_testada: Deleta um objeto users do banco, mas antes verifica se o
     * objeto indicado no parametro para ser deletado existe por meio do metodo findById, caso
     * exista o objeto eh deletado nao exista lanca uma exception. (delete).<p>
     *
     * @Descricao: Quando executar delete remove o users com sucesso no banco,
     * (whenRunnigDeleteThenReturnSucess).<p>
     *
     * @Mockito_when: Na primeira parte do teste eh feito um mock do objeto optionalUser no repository
     * para nao apresentar a exception de email duplicado e passar no teste findById, pois esse
     * teste e de sucesso para deletar.<p>
     *
     * @Mockito_doNothing: Esse comando do mockito indica para fazer nada quando chamar o metodo deleteById
     * do repository passando qualquer número de id, "anyInt".<p>
     *
     * @Service: Apos preparar os dados mockados eh chamado o método delete do objeto "service"
     * passando o ID estatico criado.
     *
     * @Verify: Como esse método nao tem retorno eh preciso "verificar" no repository quantas vezes
     * ele foi chamado usando o metodo deleteById, precisa ser apenas uma vez.
     *
     */
    @Test
    void whenRunnigDeleteThenReturnSucess() {
        Mockito
                .when(repository
                        .findById(anyInt()))
                .thenReturn(optionalUser);

        Mockito
                .doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);

        Mockito.verify(repository, Mockito.times(1))
                .deleteById(anyInt());
    }

    /**
     * @Funcionalidade_original_testada: Deleta um objeto users do banco, mas antes verifica se o
     * objeto indicado no parametro para ser deletado existe por meio do metodo findById, caso
     * exista o objeto eh deletado nao exista lanca uma exception. (delete e findById).<p>
     *
     * @Descricao: Quando executar delete nao remove o users do banco e lanca uma exception,
     * (whenRunnigDeleteThenReturnAnObjectNotFoundException).<p>
     *
     * @Mockito: Eh feito um mock do objeto optionalUser no repository porem seu retorno deve ser
     * o lancamento de uma exception, pois esse teste não eh de sucesso para deletar mas para
     * testar a exception lancada.<p>
     *
     * @Try: Eh tentada a execucao do metodo "delete" por meio do service passando
     * o ID estatico que vai gerar a exception mockada, essa exception vai ser capturada
     * no catch para validacoes.
     *
     * @Assertions:
     * * PRIMEIRA: afirma que a classe capturada no catch corresponde a classe ObjectNotFoundException.class.<p>
     * * SEGUNDA: afirma que a mensagem da classe capturada no catch corresponde a mensagem da exception.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     *
     */
    @Test
    void  whenRunnigDeleteThenReturnAnObjectNotFoundException(){
        Mockito
                .when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException("Objeto não encontrado!"));
        try {
            service.delete(ID);
        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals("Objeto não encontrado!", ex.getMessage());
        }
    }

    /**
     * @Finalidade: Metodo de inicializacao dos objetos users, caso nao sejam iniciados
     * ao serem usados acontece uma exception do tipo nullpointerexception.<p>
     * No caso do objeto optional seu construtor vai ser de/of um objeto users.
     */
    private void startUser(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new Users(ID, NOME, EMAIL, PASSWORD));
    }
}