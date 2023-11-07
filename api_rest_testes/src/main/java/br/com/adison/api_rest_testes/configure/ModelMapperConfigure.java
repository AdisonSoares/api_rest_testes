package br.com.adison.api_rest_testes.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigure {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
