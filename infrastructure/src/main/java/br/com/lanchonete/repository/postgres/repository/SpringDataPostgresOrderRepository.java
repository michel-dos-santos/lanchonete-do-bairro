package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.OrderEntity;
import br.com.lanchonete.repository.postgres.entity.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPostgresOrderRepository extends JpaRepository<OrderEntity, UUID> {


    List<OrderEntity> findAllByStatus(StatusType statusType);


}
