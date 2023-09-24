package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPostgresOrderRepository extends JpaRepository<OrderEntity, UUID> {


}
