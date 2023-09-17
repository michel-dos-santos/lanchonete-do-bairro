package br.com.lanchonete.port.usecase.request;

import br.com.lanchonete.model.Request;

import java.util.List;

public interface FindRequest {

    List<Request> findAll();
    List<Request> findAllReceived();
    List<Request> findAllInPreparation();
    List<Request> findAllReady();
    List<Request> findAllFinished();

}
