package br.com.lanchonete.rest;

import br.com.lanchonete.model.*;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.input.OrderInputDTO;
import br.com.lanchonete.rest.output.MyOrderOutputDTO;
import br.com.lanchonete.rest.output.OrderOutputDTO;
import br.com.lanchonete.rest.output.StatusPaymentMyOrder;
import br.com.lanchonete.usecase.order.*;
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
import java.util.*;

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
    @Autowired
    private FindMyOrderUsecase findMyOrderUsecase;
    @Autowired
    private FindStatusPaymentMyOrderUsecase findStatusPaymentMyOrderUsecase;
    @Autowired
    private UpdateStatusOrderUsecase updateStatusOrderUsecase;

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

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a consulta com os dados do pedido foi executada com sucesso") })
    @Operation(summary = "Consulta os dados do pedido")
    @Counted(value = "execution.count.findMyOrder")
    @Timed(value = "execution.time.findMyOrder", longTask = true)
    @GetMapping(value = "/{id}")
    public MyOrderOutputDTO findMyOrder(@PathVariable UUID id) throws APIException {
        try {
            Order order = findMyOrderUsecase.findMyOrder(id);
            MyOrderOutputDTO myOrderOutputDTO = modelMapper.map(order, MyOrderOutputDTO.class);
            myOrderOutputDTO.getBilling().setBillingFormType(order.getBilling().getBillingForm().getBillingFormType());
            return myOrderOutputDTO;
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a consulta com o status do pagamento do pedido foi executada com sucesso") })
    @Operation(summary = "Consulta o status do pagamento do pedido")
    @Counted(value = "execution.count.findStatusPaymentMyOrder")
    @Timed(value = "execution.time.findStatusPaymentMyOrder", longTask = true)
    @GetMapping(value = "/{id}/status-payment")
    public StatusPaymentMyOrder findStatusPaymentMyOrder(@PathVariable UUID id) throws APIException {
        try {
            return new StatusPaymentMyOrder(findStatusPaymentMyOrderUsecase.findStatusPaymentMyOrder(id));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a atualização do status do pedido foi executada com sucesso") })
    @Operation(summary = "Atualiza o status do pedido")
    @Counted(value = "execution.count.updateStatusOrder")
    @Timed(value = "execution.time.updateStatusOrder", longTask = true)
    @PatchMapping(value = "/{id}/status/{status}")
    public void updateStatusOrder(@PathVariable UUID id, @PathVariable StatusType status) throws APIException {
        try {
            updateStatusOrderUsecase.updateStatusOrder(id, status);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }
}
