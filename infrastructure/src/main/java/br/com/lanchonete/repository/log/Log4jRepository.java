package br.com.lanchonete.repository.log;

import br.com.lanchonete.port.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Log4jRepository implements LogRepository {

    @Override
    public void debug(Class<?> logClass, String message) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.debug(message);
    }

    @Override
    public void info(Class<?> logClass, String message) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.info(message);
    }

    @Override
    public void warn(Class<?> logClass, String message) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.warn(message);
    }

    @Override
    public void error(Class<?> logClass, String message) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.error(message);
    }
}