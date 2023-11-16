package br.com.adison.api_rest_testes.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Esta classe de configuração ModelMapperConfigure é usada para disponibilizar
 * uma instância de ModelMapper no contexto da aplicação. Quando o Spring inicia,
 * ele encontra essa classe de configuração e chama o método mapper(), registrando
 * a instância de ModelMapper como um bean no contexto do aplicativo. Essa instância
 * pode ser então injetada em outras partes do aplicativo que precisem realizar
 * mapeamentos de objetos.
 * <p>
 * A anotação, @Configuration, indica que a classe ModelMapperConfigure é uma
 * classe de configuração do Spring.
 */
@Configuration
public class ModelMapperConfigure {
    /**
     * O ModelMapper é uma biblioteca que facilita a conversão de objetos de um tipo para outro,
     * ela é uma classe do framework ModelMapper que facilita a configuração e execução de
     * mapeamentos entre objetos de diferentes tipos.
     * <p>
     * A anotação, @Bean, indica que o método mapper() será usado para criar um bean gerenciado
     * pelo Spring.
     */
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
