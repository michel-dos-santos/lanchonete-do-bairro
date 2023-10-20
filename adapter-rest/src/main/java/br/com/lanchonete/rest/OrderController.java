package br.com.lanchonete.rest;

import br.com.lanchonete.model.*;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.input.OrderInputDTO;
import br.com.lanchonete.rest.output.OrderOutputDTO;
import br.com.lanchonete.usecase.order.CheckoutOrderUsecase;
import br.com.lanchonete.usecase.order.FindAllOrdersByStatusUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static br.com.lanchonete.rest.OrderController.BASE_PATH;

@Tag(name = "Endpoint Orders")
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    public static final String BASE_PATH = "/v1/orders";
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private CheckoutOrderUsecase checkoutOrderUsecase;
    @Autowired
    private FindAllOrdersByStatusUsecase findAllOrdersByStatusUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o checkout do pedido foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do pedido")
    @Counted(value = "execution.count.checkoutOrder")
    @Timed(value = "execution.time.checkoutOrder", longTask = true)
    @PostMapping
    public OrderOutputDTO checkoutOrder(@RequestBody @Valid OrderInputDTO orderInputDTO) throws APIException {
        try {
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

            return modelMapper.map(checkoutOrderUsecase.checkout(order), OrderOutputDTO.class);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o busca dos pedidos foi executada com sucesso") })
    @Operation(summary = "Lista os dados dos pedidos")
    @Counted(value = "execution.count.findAllOrdersByStatus")
    @Timed(value = "execution.time.findAllOrdersByStatus", longTask = true)
    @GetMapping(value = "/status-type/{statusType}")
    public List<OrderOutputDTO> findAllOrdersByStatus(@PathVariable StatusType statusType) throws APIException {
        try {
            List<Order> orders = findAllOrdersByStatusUsecase.findAll(statusType);
            Type type = new TypeToken<List<OrderOutputDTO>>() {}.getType();
            return modelMapper.map(orders, type);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

}
