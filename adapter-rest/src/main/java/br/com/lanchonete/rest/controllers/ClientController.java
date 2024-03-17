package br.com.lanchonete.rest.controllers;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.port.repository.AuthClientProviderRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.rest.mappers.inputs.ClientInputMapper;
import br.com.lanchonete.rest.mappers.inputs.dtos.ClientInputDTO;
import br.com.lanchonete.rest.mappers.inputs.dtos.IdentifierClientInputDTO;
import br.com.lanchonete.rest.mappers.outputs.ClientOutputMapper;
import br.com.lanchonete.rest.mappers.outputs.dtos.ClientOutputDTO;
import br.com.lanchonete.usecase.client.IdentifierClientUsecase;
import br.com.lanchonete.usecase.client.SaveClientUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static br.com.lanchonete.rest.controllers.ClientController.BASE_PATH;

@Tag(name = "Endpoint Clients")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    public static final String BASE_PATH = "/v1/clients";
    @Autowired
    private ClientInputMapper clientInputMapper;
    @Autowired
    private ClientOutputMapper clientOutputMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SaveClientUsecase saveClientUsecase;
    @Autowired
    private IdentifierClientUsecase identifierClientUsecase;
    @Autowired
    private AuthClientProviderRepository authClientProviderRepository;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do cliente foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do cliente")
    @Counted(value = "execution.count.saveClient")
    @Timed(value = "execution.time.saveClient", longTask = true)
    @PostMapping(value = "/sign-in")
    public ClientOutputDTO saveClient(@RequestBody @Valid ClientInputDTO clientInputDTO) throws APIException {
        try {
            Client client = clientInputMapper.mapClientFromClientInputDTO(clientInputDTO);
            client = saveClientUsecase.save(client);
            authClientProviderRepository.signIn(clientInputDTO.getCpf(), clientInputDTO.getPassword(), clientInputDTO.getEmail());
            return clientOutputMapper.mapClientOutputDTOFromClient(client);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o cliente foi identificado com sucesso") })
    @Operation(summary = "Identifica o cliente")
    @Counted(value = "execution.count.identifierClient")
    @Timed(value = "execution.time.identifierClient", longTask = true)
    @PostMapping(value = "/sign-up")
    public ClientOutputDTO identifierClient(@RequestBody @Valid IdentifierClientInputDTO identifierClientInputDTO) throws APIException {
        try {
            String token = authClientProviderRepository.signUp(identifierClientInputDTO.getUsername(), identifierClientInputDTO.getPassword());
            Client client = identifierClientUsecase.identifierByCPF(identifierClientInputDTO.getUsername());
            return clientOutputMapper.mapClientOutputDTOFromClient(client, token);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }
}
