package br.com.adison.api_rest_testes.controller.resources;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Essa classe faz parte da camada resource/controller que faz a comunicação com o
 * cliente/front, que de inicio vai ser o "postman", e o banco do sistema.
 * <p>
 * A anotação "@RestController" indica ao spring que ela será do tipo "rest controller",
 * uma classe controladora de uma aplicação web para lidar com requisições HTTP dos clientes,
 * seus métodos devem ser tratados como pontos de extremidade da API da web.
 * <p>
 * Isso significa que os métodos dentro dessa classe serão automaticamente mapeados para
 * as solicitações HTTP (como GET, POST, PUT, DELETE, etc.) e o Spring irá lidar com a
 * conversão dos resultados desses métodos para formatos como JSON ou XML, que são retornados
 * como resposta para as requisições dos clientes, ou seja, manipulando o crud da aplicação
 * recebendo solicitações do client, seja inserindo ou requisitando dados, e guardando ou
 * requisitando do banco esses dados.
 * <p>
 * A anotação "@RequestMapping("/user")" é usada para mapear solicitações HTTP para métodos
 * de manipulação em um controlador, essa anotação é usada para associar um método específico
 * em um controlador com uma URL específica, ou seja, as requisições do cliente/postman devem
 * ser feitas na url de domínio da aplicação acrescida do final "/user", como exemplo:
 * "http://localhost:8080/user".
 * <p>
 * As anotações de método HTTP (como @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, etc.)
 * em conjunto com @RequestMapping indicam o tipo de solicitação HTTP que o método manipulará.
 */
@RestController
@RequestMapping("/user")
public class UserResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService service;

    /**
     * A anotação, @GetMapping(ID),  fornecida pelo Spring Framework, especificamente
     * pelo módulo Spring Web, indica que o método a seguir deve ser tratado como um
     * manipulador para solicitações HTTP GET.
     * <p>
     * O ID dentro dos parênteses é um valor de
     * variável, e será substituído pelo valor real fornecido na solicitação.
     * <p>
     * O método controlador, findById(), baseado em spring manipula solicitações GET que
     * espera um parâmetro de caminho, (@PathVariable), chamado "id".
     * O tipo Integer indica que o "id" deve ser do tipo inteiro. O método retorna um objeto
     * do tipo ResponseEntity, que é uma classe do Spring usada para representar toda a
     * resposta HTTP, incluindo cabeçalhos, status e corpo.
     * <p>
     * O return cria um objeto ResponseEntity com um status HTTP 200 OK (ok()), indicando
     * que a solicitação foi bem-sucedida. O corpo da resposta (body()) é gerado chamando
     * o método map de um objeto chamado mapper, que é um mapeador utilizado para converter
     * objetos de uma classe para outra. O método findById(id) do serviço (service) é chamado,
     * e o resultado é convertido para um objeto do tipo UserDTO usando o mapeador.
     */
    @GetMapping(ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    /**
     * Este método manipula solicitações GET para recuperar todos os usuários, converte esses
     * usuários para objetos UserDTO e retorna uma resposta HTTP 200 OK com a lista de UserDTO
     * no corpo.
     * <p>
     * A notação, @GetMapping, é uma anotação do Spring Framework que indica que o método
     * a seguir deve ser tratado como um manipulador para solicitações HTTP GET. Neste caso,
     * não há um valor específico entre parênteses, o que significa que este método será
     * acionado para solicitações GET na URL correspondente ao mapeamento do controlador.
     * <p>
     * O método controlador, findAll(), é um manipulador para solicitações GET que retorna uma
     * lista de objetos UserDTO encapsulados em um ResponseEntity.  O ResponseEntity é usado para
     * representar toda a resposta HTTP, incluindo cabeçalhos, status e corpo.
     * <p>
     * O trecho de código, List<Users> list = service.findAll(), chama o método findAll do
     * serviço (service). Esse método busca todos os usuários no sistema e retorna uma lista
     * de objetos Users (a entidade do modelo de usuário).
     * <p>
     * No trecho de código, List<UserDTO> listDTO = list.stream().
     * map(objectsUsersDaLista -> mapper.map(objectsUsersDaLista, UserDTO.class)).
     * collect(Collectors.toList()), a lista de objetos Users é transformada em uma lista de objetos
     * UserDTO usando a API de Stream do Java. Para cada objeto Users na lista, o método map do objeto
     * mapper é chamado para converter o objeto para o tipo UserDTO. Em seguida,
     * collect(Collectors.toList()) é usado para coletar os resultados em uma nova lista.
     * <p>
     * No return, return ResponseEntity.ok().body(listDTO), a resposta é construída. Um ResponseEntity
     * com status HTTP 200 OK é criado, e o corpo da resposta (body()) é preenchido com a lista de UserDTO
     * recém-criada.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<Users> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(objectsUsersDaLista -> mapper
                .map(objectsUsersDaLista, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    /**
     * Este método controlador manipula solicitações POST para criar um novo usuário. Ele recebe um
     * objeto UserDTO, chama o serviço para criar um novo objeto Users, constrói uma URI para o novo
     * objeto e retorna uma resposta HTTP 201 Created com a URI do novo recurso nos cabeçalhos.
     * <p>
     * A anotação, @PostMapping do Spring Framework, indica que o método a seguir deve ser
     * tratado como um manipulador para solicitações HTTP POST. Este método é destinado a criar
     * um novo recurso, como um novo usuário no sistema.
     * <p>
     * O método, public ResponseEntity<UserDTO> create(@RequestBody UserDTO object),  é um manipulador
     * para solicitações POST. Ele recebe um objeto do tipo UserDTO no corpo da solicitação.
     * <p>
     * A anotação @RequestBody indica ao Spring para desserializar o corpo da solicitação HTTP no
     * objeto UserDTO, indica que um recurso não será enviado ou recebido por meio de uma página da
     * Web. Serializar é escrever o JSON na resposta e desserializar é obter o objeto.
     * <p>
     * Se os dados do seu recurso fossem enviados a partir dos campos de uma página Web
     * (thymeleaf, por exemplo), você não poderia utilizar a anotação @RequestBody no parâmetro
     * do seu método na classe Controller.
     * <p>
     * Mas como uma API REST não utiliza conceito de páginas Web, usa-se esta anotação para
     * o recebimento dos recursos, fica mais claro usando o postman. Dessa forma é indicado que o objeto
     * usuario tem que ser buscado no corpo da requisição.
     * <p>
     * No trecho, Users newObject = service.create(object), o método create do serviço (service) é
     * chamado, passando o objeto UserDTO recebido como parâmetro. Esse método realiza a lógica de
     * negócios necessária para criar um novo usuário no sistema e retorna o objeto Users recém-criado.
     * <p>
     * Na linha, URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(newObject.getId()).toUri(),
     * é criada uma URI para o novo objeto criado.
     * <p>
     * ServletUriComponentsBuilder é uma classe do Spring que ajuda a construir URIs a partir da solicitação atual.
     * A URI é construída a partir da solicitação atual, com a adição de um caminho
     * (path(ID)) e a expansão do ID do novo objeto (buildAndExpand(newObject.getId())).
     * <p>
     * No return ResponseEntity.created(uri).build(), uma resposta ResponseEntity é construída. O método created(uri)
     * indica que a solicitação foi bem-sucedida e que um novo recurso foi criado, e a URI do recurso recém-criado é
     * incluída nos cabeçalhos da resposta. O método build() finaliza a construção da resposta.
     */
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO object){
        Users newObject = service.create(object);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID)
                .buildAndExpand(newObject.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Este método controlador manipula solicitações PUT para atualizar um usuário existente no sistema.
     * Ele recebe o ID do usuário a ser atualizado da URL e os dados atualizados no corpo da
     * solicitação, realiza a atualização através do serviço, e retorna uma resposta HTTP 200 OK
     * com o objeto UserDTO atualizado no corpo da resposta.
     * <p>
     * A anotação @PutMapping(ID), do Spring Framework, indica que o método a seguir deve
     * ser tratado como um manipulador para solicitações HTTP PUT. A variável ID dentro dos
     * parênteses representa uma variável de caminho que será substituída pelo valor real
     * fornecido na solicitação.
     * <p>
     * O public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO object), é um
     * método manipulador para solicitações PUT. Ele recebe dois parâmetros: id, que é o identificador
     * do usuário que deve ser atualizado (extrído da URL), e object, que é o objeto UserDTO contendo os
     * dados atualizados, recebido no corpo da solicitação (@RequestBody).
     * <p>
     * No object.setId(id), o ID fornecido na URL é atribuído ao objeto UserDTO. Isso é feito para garantir
     * que o ID passado no corpo da solicitação seja o mesmo que o ID na URL, alinhando as informações.
     * <p>
     * Na linha, Users newObject = service.update(object), o método update do serviço (service)
     * é chamado, passando o objeto UserDTO atualizado. Esse método executa a lógica de negócios
     * necessária para atualizar o usuário no sistema e retorna o objeto Users atualizado.
     * <p>
     * Na última linha, return ResponseEntity.ok().body(mapper.map(newObject, UserDTO.class)),
     * uma resposta ResponseEntity é construída indicando que a solicitação foi bem-sucedida
     * (status HTTP 200 OK). O corpo da resposta (body()) contém o objeto UserDTO recém-atualizado,
     * que é obtido mapeando o objeto Users atualizado para um objeto UserDTO usando um mapeador (mapper).
     */
    @PutMapping(ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO object){
        object.setId(id);
        Users newObject = service.update(object);
        return ResponseEntity.ok().body(mapper.map(newObject, UserDTO.class));
    }

    /**
     * Este método manipula solicitações DELETE para excluir um usuário no sistema. Ele recebe
     * o ID do usuário a ser excluído da URL, realiza a exclusão através do serviço e retorna
     * uma resposta HTTP 204 No Content indicando que a operação foi bem-sucedida e que não há
     * conteúdo a ser retornado no corpo da resposta.
     * <p>
     * O @DeleteMapping(ID) é uma anotação do Spring Framework que indica que o método a seguir
     * deve ser tratado como um manipulador para solicitações HTTP DELETE. A variável ID dentro
     * dos parênteses representa uma variável de caminho que será substituída pelo valor real
     * fornecido na solicitação.
     * <p>
     * O método, public ResponseEntity<UserDTO> delete(@PathVariable Integer id), é um manipulador
     * para solicitações DELETE. Ele recebe um parâmetro id que é o identificador do usuário a ser
     * excluído, e que é extraído da URL com a anotação @PathVariable.
     * <p>
     * Quando a linha, service.delete(id) é acionada o método delete do serviço (service) é chamado,
     * passando o id do usuário a ser excluído. Este método contém a lógica de negócios necessária
     * para excluir o usuário correspondente no sistema.
     * <p>
     * A linha final, return ResponseEntity.noContent().build(), retorna uma resposta ResponseEntity
     * indicando que a solicitação foi bem-sucedida e que não há conteúdo a ser retornado no corpo da
     * resposta (status HTTP 204 No Content). O método noContent() é usado para indicar que a operação
     * foi realizada com sucesso, mas não há conteúdo a ser retornado.
     */
    @DeleteMapping(ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
