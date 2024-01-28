package br.com.lanchonete.rest.mappers.inputs;

import br.com.lanchonete.model.*;
import br.com.lanchonete.rest.mappers.inputs.dtos.OrderInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class OrderInputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Order mapOrderFromOrderInputDTO(OrderInputDTO orderInputDTO) {
        Order order = modelMapper.map(orderInputDTO, Order.class);

        if (Objects.nonNull(orderInputDTO.getClientID())) {
            Client client = new Client();
            client.setId(orderInputDTO.getClientID());
            order.setClient(client);
        }

        BillingForm billingForm = new BillingForm();
        billingForm.setBillingFormType(orderInputDTO.getBilling().getBillingFormType());
        order.getBilling().setBillingForm(billingForm);
        order.setOrderItems(new ArrayList<>());
        orderInputDTO.getOrderItems().forEach(OrderItemInputDTO -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setObservation(OrderItemInputDTO.getObservation());
            orderItem.setQuantity(OrderItemInputDTO.getQuantity());
            Product product = new Product();
            product.setId(OrderItemInputDTO.getProductID());
            orderItem.setProduct(product);
            order.getOrderItems().add(orderItem);
        });
        return order;
    }

}
