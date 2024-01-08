package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.postgres.entity.ClientEntity;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {

    private final SpringDataPostgresClientRepository springDataPostgresClientRepository;
    private final SpringDataPostgresCategoryRepository springDataPostgresCategoryRepository;
    private final SpringDataPostgresProductRepository springDataPostgresProductRepository;

    public DataLoader(SpringDataPostgresClientRepository springDataPostgresClientRepository, SpringDataPostgresCategoryRepository springDataPostgresCategoryRepository, SpringDataPostgresProductRepository springDataPostgresProductRepository) {
        this.springDataPostgresClientRepository = springDataPostgresClientRepository;
        this.springDataPostgresCategoryRepository = springDataPostgresCategoryRepository;
        this.springDataPostgresProductRepository = springDataPostgresProductRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadClient();
    }

    private void loadClient() {
        if (springDataPostgresClientRepository.count() != 0) {
            return;
        }
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(UUID.fromString("846f7ede-dd90-497e-83a4-4878718ebd03"));
        clientEntity.setCpf("64115683082");
        clientEntity.setCreatedAt(Date.valueOf("2023-12-24 16:18:55.198"));
        clientEntity.setUpdatedAt(Date.valueOf("2023-12-24 16:18:55.198"));
        clientEntity.setEmail("joao.de.souza@gmail.com");
        clientEntity.setName("João de Souza");
    }
}
