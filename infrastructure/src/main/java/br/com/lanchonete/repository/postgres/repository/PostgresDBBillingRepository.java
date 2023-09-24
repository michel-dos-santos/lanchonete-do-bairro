package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.port.repository.BillingRepository;
import br.com.lanchonete.repository.postgres.entity.BillingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostgresDBBillingRepository implements BillingRepository {

    private final SpringDataPostgresBillingRepository billingRepository;
    private final ModelMapper modelMapper;

    public PostgresDBBillingRepository(SpringDataPostgresBillingRepository billingRepository, ModelMapper modelMapper) {
        this.billingRepository = billingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Billing generate(Billing billing) {
        BillingEntity billingEntity = modelMapper.map(billing, BillingEntity.class);
        return modelMapper.map(billingRepository.save(billingEntity), Billing.class);
    }

}
