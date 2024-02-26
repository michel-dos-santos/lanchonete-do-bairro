package br.com.lanchonete.port.repository;

public interface AuthClientProviderRepository {

    void signIn(String username, String password, String email);

    String signUp(String username, String password);

}
