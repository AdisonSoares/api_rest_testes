package br.com.adison.api_rest_testes.model.service.exceptions;

/**
 * Essa classe ObjectNotFoundException é uma exceção específica do aplicativo.
 * Ela é usada para representar situações em que um objeto específico não é
 * encontrado. Por exemplo, pode ser lançada quando uma consulta a um banco
 * de dados não retorna resultados esperados ou quando um objeto com uma
 * identificação específica não pode ser encontrado. O uso de uma exceção
 * personalizada permite que o código cliente identifique e trate de forma
 * específica esse tipo de erro durante a execução do aplicativo.
 * <p>
 * A classe estende RuntimeException, que é uma exceção não
 * verificada (unchecked exception) do Java.
 * <p>
 * Este construtor,public ObjectNotFoundException(String message) { super(message),
 * recebe uma mensagem como parâmetro e chama o construtor da classe pai (RuntimeException)
 * passando essa mensagem. O construtor é usado para criar instâncias da exceção com
 * uma mensagem específica que pode ser exibida ao lidar com a exceção.
 */
public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
