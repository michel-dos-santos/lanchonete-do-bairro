package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.postgres.entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPostgresBillingRepository extends JpaRepository<BillingEntity, UUID> {

}
