package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.postgres.entity.OrderEntity;
import br.com.lanchonete.postgres.entity.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresOrderRepository extends JpaRepository<OrderEntity, UUID> {


    List<OrderEntity> findAllByStatus(StatusType statusType);

    @Query(value = "select o from OrderEntity o inner join o.billing b where b.id = :id")
    Optional<OrderEntity> findByBillingId(UUID id);

    @Query(value = "select o from OrderEntity o where o.status in (:status) " +
            "order by " +
            "case when status = 'READY' then 0 " +
            "when status = 'IN_PREPARATION' then 1 " +
            "when status = 'RECEIVED' then 2 else 3 end asc, " +
            "o.createdAt asc ")
    List<OrderEntity> findAllOrdersMonitor(List<StatusType> status);

}
