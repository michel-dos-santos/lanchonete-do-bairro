package br.com.lanchonete;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Log4jRepository implements LogRepository {

    @Override
    public void debug(Class<?> logClass, LogCode.LogCodeDebug logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.debug(String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription()));
    }

    @Override
    public void info(Class<?> logClass, LogCode.LogCodeInfo logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.info(String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription()));
    }

    @Override
    public void warn(Class<?> logClass, LogCode.LogCodeWarn logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.warn(String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription()));
    }

    @Override
    public void error(Class<?> logClass, LogCode.LogCodeError logCode) {
        Logger logger = LoggerFactory.getLogger(logClass);
        logger.error(String.format("TAG: %s - MESSAGE: %s", logCode.name(), logCode.getDescription()));
    }
}