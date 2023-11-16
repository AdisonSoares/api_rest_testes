package br.com.adison.api_rest_testes.controller.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Esta classe StandardError é uma classe de representação de erros (ou respostas de erro)
 * que pode ser utilizada para estruturar e fornecer informações detalhadas sobre um erro
 * ocorrido em uma API. A anotação @Data do Lombok simplifica a criação dessa classe,
 * gerando automaticamente os métodos necessários para facilitar a manipulação dos dados.
 * <p>
 * A anotação @Data do Lombok é composta e inclui automaticamente as anotações @ToString,
 * @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor, elas geram automaticamente
 * métodos como toString(), equals(), hashCode(), getters e setters.
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class StandardError {
    /**
     * O atributo, private LocalDateTime timestamp, é uma declaração de uma variável de instância chamada timestamp
     * do tipo LocalDateTime, representando o momento em que ocorreu o erro.
     * <p>
     * O atributo, private Integer status, é uma declaração de uma variável de instância chamada status do tipo Integer,
     * representando o código de status HTTP associado ao erro.
     * <p>
     * O atributo, private String error, é uma declaração de uma variável de instância chamada error do tipo String,
     * representando uma descrição do erro.
     * <p>
     * O atributo, private String path, é uma declaração de uma variável de instância chamada path do tipo String,
     * representando o caminho do recurso que causou o erro.
     */
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
