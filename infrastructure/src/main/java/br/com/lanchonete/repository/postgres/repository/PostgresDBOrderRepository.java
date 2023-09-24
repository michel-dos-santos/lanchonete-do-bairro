package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.repository.postgres.entity.OrderEntity;
import br.com.lanchonete.repository.postgres.entity.SequenceNumber;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostgresDBOrderRepository implements OrderRepository {

    private final SpringDataPostgresOrderRepository orderRepository;
    private final SpringDataPostgresSequenceNumberRepository sequenceNumberRepository;
    private final ModelMapper modelMapper;

    public PostgresDBOrderRepository(SpringDataPostgresOrderRepository orderRepository, SpringDataPostgresSequenceNumberRepository sequenceNumberRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.sequenceNumberRepository = sequenceNumberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Order checkout(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        SequenceNumber sequenceNumber = sequenceNumberRepository.save(new SequenceNumber());
        orderEntity.setNumber(sequenceNumber.getNumber());
        orderEntity.getOrderItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
        return modelMapper.map(orderRepository.save(orderEntity), Order.class);
    }
}
