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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * @Resumo: Classe criada para testar os metodos de UserResource, usando os recursos da Ide
 * para criar os moldes de cada teste.<p>
 *
 * @Instancias:
 * * user (objeto da classe domain)<p>
 * * userDTO (objeto da classe de transferencia de dados)<p>
 * * optionalUser (objeto da classe optional que lida com dados do tipo user com possíbilidade de serem nulos)<p>
 * * service (objeto da classe testada para implementar seus metodos)<p>
 * * repository (objeto da classe de comunicacao com o banco)<p>
 * * mapper (objeto da classe de conversao do banco com a classe)<p>
 * * INDEX, ID, NOME, EMAIL, PASSWORD, EMAIL (constantes iniciadas para preencher construtores dos objetos user).<p>
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
class UserResourceTest {
    public static final Integer INDEX_ZERO = 0;
    public static final Integer ID = 1;
    public static final String NOME = "nomeTeste";
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
     * @Finalidade: Metodo criado para inicializar os mocks desta/this classe,
     * e iniciar o metodo que foi feito para criar construtores aos objetos user
     * para nao acontecer um lancamento de nullpointerexception.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    /**
     * @Funcionalidade_original_testada: Esse metodo eh para testar o "findById" que retorna um objeto
     * "ResponseEntity" do tipo "<UserDTO>" de acordo com um id especificado nos parametros ou lanca
     * uma exception caso nao tenha no banco. (findById)<p>
     *
     * @Nomeacao: Quando executar "FindById" retorna um objeto "ResponseEntity" com sucesso, mapeando um
     * objeto "users" para "userDTO". (whenFindByIdThenReturnSucess)<p>
     *
     * @Mockito:
     * * PRIMEIRA: Eh mockado o chamado do metodo "findById" da classe "UserServiceImplement"
     * passando qualquer/any nemero de Id e retornando um objeto "users" com sucesso.<p>
     * * SEGUNDA: Eh mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o metodo "findById" da classe "UserResource" passando o ID criado eh possível
     * armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "UserDTO" para
     * testar as afirmativas.<p>
     *
     * @Assertions:
     * * PRIMEIRA: verifica se o response esta nulo.<p>
     * * SEGUNDA: verifica se o corpo/body do response esta nulo.<p>
     * * TERCEIRA: verifica se a classe do response corresponde a uma classe "ResponseEntity.class".<p>
     * * QUARTA: verifica se a classe do corpo do response corresponde a uma classe "UserDTO.class".<p>
     * * QUINTA: verifica se o id do corpo do response corresponde ao ID estático passado.<p>
     * * SEXTA: verifica se o nome do corpo do response corresponde ao NOME_TESTE estatico passado.<p>
     * * SETIMA: verifica se o email do corpo do response corresponde ao EMAIL estático passado.<p>
     * * OITAVA: verifica se a senha do corpo do response corresponde ao PASSWORD estatico passado.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     *
     * @Recomendacao: Para que o teste seja completo eh preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os codigos serao.
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
        Assertions.assertEquals(NOME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Esse metodo eh para testar o "findAll" que retorna uma lista
     * de objetos "ResponseEntity" do tipo "<List<UserDTO>>" sem parametros. (findAll)<p>
     *
     * @Nomeacao: Quando executar "FindAll" retorna um objeto "ResponseEntity" com sucesso, mapeando a lista
     * de objetos "users" para "userDTO" e retornando essa lista no corpo. (whenFindAllThenReturnAListOfUserDTO)<p>
     *
     * @Mockito:
     * * PRIMEIRA: Eh mockado o chamado do metodo "findAll" da classe "UserServiceImplement" e retornando
     * uma lista de "users" com sucesso.<p>
     * * SEGUNDA: Eh mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o metodo "findAll" da classe "UserResource" eh possivel armazenar seu
     * retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "<List<UserDTO>>" para
     * testar as afirmativas.<p>
     *
     * @Assertions:
     * * PRIMEIRA: verifica se o response esta nulo.<p>
     * * SEGUNDA: verifica se o corpo/body do response esta nulo.<p>
     * * TERCEIRA: verifica se a mensagem do response corresponde a "HttpStatus.OK".<p>
     * * QUARTA: verifica se a classe do responde corresponde a "ResponseEntity.class".<p>
     * * QUINTA: verifica se o corpo do response esta retornando uma classe "ArrayList.class".<p>
     * * SEXTA: verifica se no primeiro indice do corpo do response esta retornado uma classe do tipo "UserDTO.class".<p>
     * * SETIMA: verifica se o id do primeiro indice do corpo do response esta sendo retornado o ID estatico.<p>
     * * OITAVA: verifica se o nome do primeiro indice do corpo do response esta sendo retornado o NOME estatico.<p>
     * * NONA: verifica se o email do primeiro indice do corpo do response esta sendo retornado o EMAIL estatico.<p>
     * * DECIMA: verifica se a senha do primeiro indice do corpo do response esta sendo retornado o PASSWORD estatico.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     *
     * @Recomendacao: Para que o teste seja completo eh preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os codigos serao.
     */
    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        Mockito
                .when(service
                        .findAll())
                .thenReturn(List.of(users));
        Mockito
                .when(mapper
                        .map(any(), any()))
                .thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());

        Assertions.assertEquals(UserDTO.class, response.getBody().get(INDEX_ZERO).getClass());
        Assertions.assertEquals(ID, response.getBody().get(INDEX_ZERO).getId());
        Assertions.assertEquals(NOME, response.getBody().get(INDEX_ZERO).getName());
        Assertions.assertEquals(EMAIL, response.getBody().get(INDEX_ZERO).getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().get(INDEX_ZERO).getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Esse metodo eh para testar o "create" que retorna uma lista
     * de objetos "ResponseEntity" do tipo "<List<UserDTO>>" tendo como parametro um objeto "UserDTO"
     * para ser incluso no banco. (create)<p>
     *
     * @Nomeacao: Quando executar "create" lendo o parametro com objeto "UserDTO" retorna um objeto
     * "ResponseEntity" com sucesso, mapeando a lista de objetos "users" para "userDTO" e
     * retornando essa lista no corpo. (whenCreateThenReturnCreated).<p>
     *
     * @Mockito:
     * * PRIMEIRA: Eh mockado o chamado do metodo "create" da classe "UserServiceImplement" passando qualquer/any
     * parametro e retornando uma lista de "users" com sucesso.<p>
     *
     * @Response: Ao chamar o metodo "create" da classe "UserResource" passando um objeto "userDTO" no parametro
     * eh possivel armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "<UserDTO>"
     * para testar as afirmativas.<p>
     *
     * @Assertions:
     * * PRIMEIRA: verifica se a classe do response corresponde a uma classe do tipo "ResponseEntity.class".<p>
     * * SEGUNDA: verifica se o status do response corresponde a um status do tipo "HttpStatus.CREATED".<p>
     * * TERCEIRA: verifica se o "location" do "headers" do response nao esta vindo nulo, ele precisa de valor
     * pois eh a uri para localizar o novo objeto.<p>
     * * QUARTA: verifica se o body/corpo do response esta vindo nulo, pois nao precisa ter retorno no body.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
     * @Assertions_null: Verifica se o objeto da classe passada esta nulo.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.
     */
    @Test
    void whenCreateThenReturnCreated() {
        Mockito
                .when(service
                        .create(any()))
                .thenReturn(users);
        ResponseEntity<UserDTO> response = resource.create(userDTO);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location"));
        Assertions.assertNull(response.getBody());
    }

    /**
     * @Funcionalidade_original_testada: Esse metodo eh para testar o "update" que retorna
     *
     * @Nomeacao: Quando executar "update" retorna um objeto "ResponseEntity" com sucesso no corpo da solicitacao,
     * mapeando um objeto "users" para "userDTO". (whenUpdateThenReturnSucess)<p>
     *
     * @Mockito:
     * - PRIMEIRA: Eh mockado o chamado do metodo "update" da classe "UserServiceImplement"
     * passando o objeto estatico "userDTO" e retornando um objeto "users" com sucesso.<p>
     * - SEGUNDA: Eh mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o metodo "update" da classe "UserResource" passando o ID e userDTO estatico criados
     * eh possivel armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "UserDTO"
     * para testar as afirmativas.<p>
     *
     * @Assertions:
     * * PRIMEIRA: verifica se o response esta nulo.<p>
     * * SEGUNDA: verifica se o corpo/body do response esta nulo.<p>
     * * TERCEIRA: verifica se a mensagem do response corresponde a "HttpStatus.OK".<p>
     * * QUARTA: verifica se a classe do responde corresponde a "ResponseEntity.class".<p>
     * * QUINTA: verifica se o corpo do response esta retornando uma classe "ArrayList.class".<p>
     * * SEXTA: verifica se no primeiro indice do corpo do response esta retornado uma classe do tipo "UserDTO.class".<p>
     * * SETIMA: verifica se o id do primeiro indice do corpo do response está sendo retornado o ID estatico.<p>
     * * OITAVA: verifica se o nome do primeiro indice do corpo do response esta sendo retornado o NOME estatico.<p>
     * * NONA: verifica se o email do primeiro indice do corpo do response esta sendo retornado o EMAIL estatico.<p>
     * * DECIMA: verifica se a senha do primeiro indice do corpo do response esta sendo retornado o PASSWORD estatico.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.
     *
     * @Recomendacao: Para que o teste seja completo eh preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os codigos serao.
     */
    @Test
    void whenUpdateThenReturnSucess() {
        Mockito
                .when(service
                        .update(userDTO))
                .thenReturn(users);
        Mockito
                .when(mapper
                        .map(any(), any()))
                .thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Esse metodo eh para testar o "delete" que retorna um objeto
     * "ResponseEntity" do tipo "<UserDTO>" de acordo com um id e um objeto do tipo "UserDTO" especificado
     * nos parametros atualizando no banco o objeto especificado. (update)<p>
     *
     * @Nomeacao: Quando executar "delete" retorna um objeto "ResponseEntity" vazio no corpo
     * da solicitacao deletando o objeto indicado no id dos parametros. (whenDeleteThenReturnSucess)<p>
     *
     * @Mockito:
     * * PRIMEIRA: Eh mockado o chamado do método "delete" da classe "UserServiceImplement" passando
     * qualquer valor inteiro de id, porem como nao tem retorno eh indicado para nao fazer nada/doNothing.<p>
     *
     * @Response: Ao chamar o metodo "delete" da classe "UserResource" passando o ID estatico criado
     * eh possivel armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "UserDTO"
     * para testar as afirmativas.<p>
     *
     * @Assertions:
     * * PRIMEIRA: verifica se o response esta nulo.<p>
     * * SEGUNDA: verifica se o corpo/body do response esta nulo.<p>
     * * TERCEIRA: verifica se a classe do responde corresponde a "ResponseEntity.class".<p>
     * * QUARTA: verifica se a operacao foi bem sucedida mas sem conteudo de retorno.<p>
     *
     * @Verify:
     * * PRIMEIRA: verifica se o metodo delete está sendo chamado apenas uma vez para deletar.<p>
     *
     * @Assertions_notNull: Verifica se o objeto da classe passada nao esta nulo.<p>
     *
     * @Assertions_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que esta retornando.<p>
     *
     * @Mockito.verify: Essa funcao eh usada para verificar se um determinado metodo em um
     * mock foi chamado um número especifico de vezes.<p>
     *
     */
    @Test
    void whenDeleteThenReturnSucess() {
        Mockito
                .doNothing()
                .when(service)
                        .delete(anyInt());

        ResponseEntity<UserDTO> response = resource.delete(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).delete(anyInt());
    }

    /**
     * @Finalidade: Metodo de inicializacao dos objetos users, caso nao sejam iniciados
     * ao serem usados acontece uma exception do tipo nullpointerexception.<p>
     */
    private void startUser(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
    }
}