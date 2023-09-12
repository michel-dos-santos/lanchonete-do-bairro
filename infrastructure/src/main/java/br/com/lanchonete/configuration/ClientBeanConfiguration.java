package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.SaveClient;
import br.com.lanchonete.usecase.SaveClientUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBeanConfiguration {

    @Bean
    SaveClientUsecase saveClient(ClientRepository clientRepository, LogRepository logRepository) {
        return new SaveClientUsecase(clientRepository, logRepository);
    }

}
