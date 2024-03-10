package br.com.lanchonete.rest.controller;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.port.repository.AuthClientProviderRepository;
import br.com.lanchonete.rest.controllers.ClientController;
import br.com.lanchonete.rest.mappers.inputs.ClientInputMapper;
import br.com.lanchonete.rest.mappers.inputs.dtos.ClientInputDTO;
import br.com.lanchonete.rest.mappers.inputs.dtos.IdentifierClientInputDTO;
import br.com.lanchonete.rest.mappers.outputs.ClientOutputMapper;
import br.com.lanchonete.rest.mappers.outputs.dtos.ClientOutputDTO;
import br.com.lanchonete.usecase.client.IdentifierClientUsecase;
import br.com.lanchonete.usecase.client.SaveClientUsecase;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest extends ControllerTestBase {

    @MockBean
    private ClientInputMapper clientInputMapper;
    @MockBean
    private ClientOutputMapper clientOutputMapper;
    @MockBean
    private SaveClientUsecase saveClientUsecase;
    @MockBean
    private IdentifierClientUsecase identifierClientUsecase;
    @MockBean
    private AuthClientProviderRepository authClientProviderRepository;

    @Test
    public void saveClientSuccess() throws Exception {
        String url = ClientController.BASE_PATH.concat("/sign-in");
        ClientInputDTO clientInputDTO = easyRandom.nextObject(ClientInputDTO.class);
        clientInputDTO.setCpf("10902661035");
        clientInputDTO.setEmail("email@email.com");
        clientInputDTO.setPassword("password");
        Client client = modelMapperAPI.map(clientInputDTO, Client.class);
        client.setId(easyRandom.nextObject(UUID.class));
        ClientOutputDTO clientOutputDTO = modelMapperAPI.map(client, ClientOutputDTO.class);

        when(clientInputMapper.mapClientFromClientInputDTO(clientInputDTO)).thenReturn(client);
        when(saveClientUsecase.save(any())).thenReturn(client);
        when(clientOutputMapper.mapClientOutputDTOFromClient(client)).thenReturn(clientOutputDTO);

        clientOutputDTO =
                doPost(url, clientInputDTO, HttpStatus.OK, new TypeReference<ClientOutputDTO>() {});

        assertThat(!Objects.isNull(clientOutputDTO.getId()));
        assertThat(clientOutputDTO.getName().equals(clientOutputDTO.getName()));
        assertThat(clientOutputDTO.getCpf().equals(clientOutputDTO.getCpf()));
        assertThat(clientOutputDTO.getEmail().equals(clientOutputDTO.getEmail()));
    }

    @Test
    public void saveClientException() throws Exception {
        String url = ClientController.BASE_PATH.concat("/sign-in");
        ClientInputDTO clientInputDTO = easyRandom.nextObject(ClientInputDTO.class);
        clientInputDTO.setCpf("10902661035");
        clientInputDTO.setEmail("email@email.com");
        clientInputDTO.setPassword("password");
        Client client = modelMapperAPI.map(clientInputDTO, Client.class);
        client.setId(easyRandom.nextObject(UUID.class));

        when(clientInputMapper.mapClientFromClientInputDTO(clientInputDTO)).thenReturn(client);
        when(saveClientUsecase.save(any())).thenThrow(new MockitoException("Cliente não informado"));

        ResultActions resultActions = doPost(url, clientInputDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    public void identifierClientSuccess() throws Exception {
        String url = ClientController.BASE_PATH.concat("/sign-up");
        IdentifierClientInputDTO identifierClientInputDTO = new IdentifierClientInputDTO();
        identifierClientInputDTO.setUsername("10902661035");
        identifierClientInputDTO.setPassword("password");
        String token = easyRandom.nextObject(String.class);
        Client client = easyRandom.nextObject(Client.class);
        client.setId(easyRandom.nextObject(UUID.class));
        client.setCpf(identifierClientInputDTO.getUsername());
        ClientOutputDTO clientOutputDTO = modelMapperAPI.map(client, ClientOutputDTO.class);
        clientOutputDTO.setToken(token);

        when(authClientProviderRepository.signUp(identifierClientInputDTO.getUsername(), identifierClientInputDTO.getPassword())).thenReturn(token);
        when(identifierClientUsecase.identifierByCPF(identifierClientInputDTO.getUsername())).thenReturn(client);
        when(clientOutputMapper.mapClientOutputDTOFromClient(client, token)).thenReturn(clientOutputDTO);

        clientOutputDTO =
                doPost(url, identifierClientInputDTO, HttpStatus.OK, new TypeReference<ClientOutputDTO>() {});

        assertThat(!Objects.isNull(clientOutputDTO.getId()));
        assertThat(clientOutputDTO.getName().equals(clientOutputDTO.getName()));
        assertThat(clientOutputDTO.getCpf().equals(clientOutputDTO.getCpf()));
        assertThat(clientOutputDTO.getEmail().equals(clientOutputDTO.getEmail()));
        assertThat(clientOutputDTO.getToken().equals(clientOutputDTO.getToken()));
    }

    @Test
    public void identifierClientException() throws Exception {
        String url = ClientController.BASE_PATH.concat("/sign-up");
        IdentifierClientInputDTO identifierClientInputDTO = new IdentifierClientInputDTO();
        identifierClientInputDTO.setUsername("109026610");
        identifierClientInputDTO.setPassword("password");
        String token = easyRandom.nextObject(String.class);
        Client client = modelMapperAPI.map(identifierClientInputDTO, Client.class);
        client.setId(easyRandom.nextObject(UUID.class));

        when(authClientProviderRepository.signUp(identifierClientInputDTO.getUsername(), identifierClientInputDTO.getPassword())).thenReturn(token);
        when(identifierClientUsecase.identifierByCPF(identifierClientInputDTO.getUsername())).thenThrow(new MockitoException("Cliente não localizado"));

        ResultActions resultActions = doPost(url, identifierClientInputDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        resultActions.andExpect(status().isInternalServerError());
    }

}
