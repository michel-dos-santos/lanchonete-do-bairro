package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.LogCode;

public interface LogRepository {

    void error(Class<?> logClass, LogCode.LogCodeError logCode);
    void warn(Class<?> logClass, LogCode.LogCodeWarn logCode);
    void info(Class<?> logClass, LogCode.LogCodeInfo logCode);
    void debug(Class<?> logClass, LogCode.LogCodeDebug logCode);

}
