package br.com.lanchonete.hub.mercadopago;

import br.com.lanchonete.hub.mercadopago.dto.MercadoPagoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mercadoPagoServiceFeignClient", url = "${api.mercadopago.url}", fallbackFactory = MercadoPagoServiceFallback.class)
public interface MercadoPagoServiceFeignClient {

    @PostMapping(value = "")
    void billing(@RequestBody MercadoPagoRequestDTO mercadoPagoRequestDTO);

}
