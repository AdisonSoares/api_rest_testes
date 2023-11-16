package br.com.adison.api_rest_testes.model.service.exceptions;

/**
 * Essa classe DataIntegratyViolationException é uma exceção específica do aplicativo.
 * É usada para representar situações em que ocorre uma violação de integridade de dados
 * (por exemplo, tentativa de inserção de dados duplicados em uma tabela do banco de dados).
 * O uso de uma exceção personalizada permite que o código cliente identifique e trate de
 * forma específica esse tipo de erro durante a execução do aplicativo.
 * <p>
 * A classe estende RuntimeException, é uma exceção não verificada (unchecked exception)
 * do Java.
 * <p>
 * Este construtor, DataIntegratyViolationException(String message) { super(message), recebe
 * uma mensagem como parâmetro e chama o construtor da classe pai (RuntimeException) passando
 * essa mensagem. O construtor é usado para criar instâncias da exceção com uma mensagem
 * específica que pode ser exibida ao lidar com a exceção.
 */
public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException(String message) {
        super(message);
    }
}
