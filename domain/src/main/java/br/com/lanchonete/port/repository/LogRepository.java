package br.com.lanchonete.port.repository;

public interface LogRepository {

    void error(Class<?> logClass, String message);
    void warn(Class<?> logClass, String message);
    void info(Class<?> logClass, String message);
    void debug(Class<?> logClass, String message);

}
