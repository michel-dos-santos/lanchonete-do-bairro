package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.client.ValidateClient;
import br.com.lanchonete.usecase.IdentifierClientUsecase;
import br.com.lanchonete.usecase.SaveClientUsecase;
import br.com.lanchonete.usecase.ValidateClientUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBeanConfiguration {

    @Bean
    SaveClientUsecase saveClient(ClientRepository clientRepository, LogRepository logRepository, ValidateClient validateClient) {
        return new SaveClientUsecase(clientRepository, logRepository, validateClient);
    }
    @Bean
    ValidateClientUsecase validateClient(LogRepository logRepository) {
        return new ValidateClientUsecase(logRepository);
    }
    @Bean
    IdentifierClientUsecase identifierClient(ClientRepository clientRepository, LogRepository logRepository) {
        return new IdentifierClientUsecase(clientRepository, logRepository);
    }

}
