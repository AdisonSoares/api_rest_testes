package br.com.adison.api_rest_testes.controller.resources.exceptions;

import br.com.adison.api_rest_testes.model.service.exceptions.DataIntegratyViolationException;
import br.com.adison.api_rest_testes.model.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Essa classe ResourceExceptionHandler atua como um controlador de aconselhamento global e
 * fornece manipulação personalizada para exceções específicas. Quando uma exceção do tipo
 * ObjectNotFoundException é lançada, o método objectNotFound é chamado para criar uma
 * resposta HTTP 404 contendo informações sobre o erro. Da mesma forma, quando uma
 * exceção do tipo DataIntegratyViolationException é lançada, o método
 * dataIntegratyViolationException é chamado para criar uma resposta HTTP 400.
 * Essa abordagem centralizada ajuda a padronizar o tratamento de erros no aplicativo inteiro.
 * <p>
 * A anotação, @ControllerAdvice, é uma anotação do Spring que indica que a classe é um controlador
 * de aconselhamento global. Isso significa que a classe é responsável por fornecer a manipulação
 * centralizada de exceções para todos os controladores dentro do aplicativo.
 */
@ControllerAdvice
public class ResourceExceptionHandler {
    /**
     * Esta anotação, @ExceptionHandler(ObjectNotFoundException.class),indica que o método
     * a seguir (objectNotFound) será chamado quando uma exceção do tipo ObjectNotFoundException
     * (ou uma de suas subclasses) for lançada durante a execução de um controlador.
     * <p>
     * A declaração, public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException
     * exception, HttpServletRequest request), do método objectNotFound, lida com exceções
     * do tipo ObjectNotFoundException. Ele recebe dois parâmetros: a exceção lançada
     * (ObjectNotFoundException exception) e a requisição HTTP (HttpServletRequest request).
     * <p>
     * A linha de código, StandardError error = new StandardError(LocalDateTime.now(),
     * HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI()),
     * cria uma instância da classe StandardError para encapsular informações sobre o erro.
     * Neste caso, inclui um timestamp, o código de status HTTP 404 (NOT_FOUND), a mensagem
     * da exceção e o URI da requisição.
     * <p>
     * A linha final, return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error), retorna uma
     * resposta HTTP 404 NOT_FOUND contendo o objeto StandardError criado no passo anterior.
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * O segundo método (dataIntegratyViolationException) é semelhante, mas lida com exceções do tipo
     * DataIntegratyViolationException (ou suas subclasses) e retorna uma resposta HTTP 400 BAD_REQUEST.
     */
    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException exception, HttpServletRequest request){
        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
