package br.com.lanchonete.rest.controllers;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.gateways.OrderGateway;
import br.com.lanchonete.rest.gateways.input.OrderInputDTO;
import br.com.lanchonete.rest.presenters.OrderPresenter;
import br.com.lanchonete.rest.presenters.output.MyOrderOutputDTO;
import br.com.lanchonete.rest.presenters.output.OrderOutputDTO;
import br.com.lanchonete.rest.presenters.output.StatusPaymentMyOrder;
import br.com.lanchonete.usecase.order.*;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.lanchonete.rest.controllers.OrderController.BASE_PATH;

@Tag(name = "Endpoint Orders")
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    public static final String BASE_PATH = "/v1/orders";
    @Autowired
    private OrderGateway orderGateway;
    @Autowired
    private OrderPresenter orderPresenter;
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
    @Autowired
    private ListOrdersMonitorUsecase listOrdersMonitorUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o checkout do pedido foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do pedido")
    @Counted(value = "execution.count.checkoutOrder")
    @Timed(value = "execution.time.checkoutOrder", longTask = true)
    @PostMapping
    public OrderOutputDTO checkoutOrder(@RequestBody @Valid OrderInputDTO orderInputDTO) throws APIException {
        try {
            Order order = orderGateway.mapOrderFromOrderInputDTO(orderInputDTO);
            return orderPresenter.mapOrderOutputDTOFromOrder(checkoutOrderUsecase.checkout(order));
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
            return orderPresenter.mapListOrderOutputDTOFromListOrder(findAllOrdersByStatusUsecase.findAll(statusType));
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
            return orderPresenter.mapMyOrderOutputDTOFromOrder(findMyOrderUsecase.findMyOrder(id));
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
            return orderPresenter.mapStatusPaymentMyOrderFromStatusPaymentType(findStatusPaymentMyOrderUsecase.findStatusPaymentMyOrder(id));
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

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a listagem dos pedidos (Pronto > Em Preparação > Recebido) foi executada com sucesso") })
    @Operation(summary = "Consulta a listagem dos pedidos (Pronto > Em Preparação > Recebido)")
    @Counted(value = "execution.count.listOrders")
    @Timed(value = "execution.time.listOrders", longTask = true)
    @GetMapping(value = "/monitor")
    public List<MyOrderOutputDTO> listOrders() throws APIException {
        try {
            return orderPresenter.mapListMyOrderOutputDTOFromListOrder(listOrdersMonitorUsecase.listOrdersMonitor());
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }
}
