package br.com.lanchonete.rest.controllers;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.gateways.ClientGateway;
import br.com.lanchonete.rest.gateways.input.ClientInputDTO;
import br.com.lanchonete.rest.presenters.ClientPresenter;
import br.com.lanchonete.rest.presenters.output.ClientOutputDTO;
import br.com.lanchonete.usecase.client.IdentifierClientUsecase;
import br.com.lanchonete.usecase.client.SaveClientUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static br.com.lanchonete.rest.controllers.ClientController.BASE_PATH;

@Tag(name = "Endpoint Clients")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    public static final String BASE_PATH = "/v1/clients";
    @Autowired
    private ClientGateway clientGateway;
    @Autowired
    private ClientPresenter clientPresenter;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SaveClientUsecase saveClientUsecase;
    @Autowired
    private IdentifierClientUsecase identifierClientUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do cliente foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do cliente")
    @Counted(value = "execution.count.saveClient")
    @Timed(value = "execution.time.saveClient", longTask = true)
    @PostMapping
    public ClientOutputDTO saveClient(@RequestBody @Valid ClientInputDTO clientInputDTO) throws APIException {
        try {
            Client client = clientGateway.mapClientFromClientInputDTO(clientInputDTO);
            return clientPresenter.mapClientOutputDTOFromClient(saveClientUsecase.save(client));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o cliente foi identificado com sucesso") })
    @Operation(summary = "Identifica o cliente")
    @Counted(value = "execution.count.identifierClient")
    @Timed(value = "execution.time.identifierClient", longTask = true)
    @PostMapping(value = "/{cpf}")
    public ClientOutputDTO identifierClient(@PathVariable @NotBlank(message = "CPF não pode ser vazio ou nulo") String cpf) throws APIException {
        try {
            Client client = identifierClientUsecase.identifierByCPF(cpf);
            return clientPresenter.mapClientOutputDTOFromClient(client);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }
}
