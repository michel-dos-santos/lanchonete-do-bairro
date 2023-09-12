package br.com.lanchonete.rest;

import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.exception.APIException;
import br.com.lanchonete.usecase.SaveClientUsecase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Endpoint Clients")
@RestController
@RequestMapping(path = "/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SaveClientUsecase saveClientUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do cliente foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do cliente")
    @Counted(value = "execution.count.saveClient")
    @Timed(value = "execution.time.saveClient", longTask = true)
    @PostMapping
    public void saveClient() throws APIException {
        try {
            saveClientUsecase.save();
        } catch (Exception e) {
            throw APIException.internalError("Erro interno inesperado ao criar o cliente", e.getMessage());
        }
    }
}
