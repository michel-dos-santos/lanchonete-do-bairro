package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.postgres.entity.OrderEntity;
import br.com.lanchonete.postgres.entity.SequenceNumber;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

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

    @Override
    public List<Order> findAllOrdersByStatus(StatusType statusType) {
        List<OrderEntity> orderEntityList = orderRepository.findAllByStatus(br.com.lanchonete.postgres.entity.StatusType.get(statusType.toString()));
        Type type = new TypeToken<List<Order>>() {}.getType();
        return modelMapper.map(orderEntityList, type);
    }
}
