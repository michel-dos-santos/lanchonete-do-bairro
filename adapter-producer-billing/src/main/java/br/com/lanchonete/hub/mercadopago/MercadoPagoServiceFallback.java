package br.com.lanchonete.hub.mercadopago;

import br.com.lanchonete.port.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MercadoPagoServiceFallback implements FallbackFactory<MercadoPagoServiceFeignClient> {

    @Autowired
    private LogRepository logRepository;

    @Override
    public MercadoPagoServiceFeignClient create(Throwable cause) {
        //logRepository.info(cause.getMessage());

        //try {
        //    throw new ServiceException(new ErrorResponse(cause.getMessage()));
        //} catch (IllegalArgumentException e) {
        //    throw new TokenServiceUnavailableException();
        //}
        return null;
    }
}
