package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.postgres.entity.OrderEntity;
import br.com.lanchonete.postgres.entity.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPostgresOrderRepository extends JpaRepository<OrderEntity, UUID> {


    List<OrderEntity> findAllByStatus(StatusType statusType);


}
