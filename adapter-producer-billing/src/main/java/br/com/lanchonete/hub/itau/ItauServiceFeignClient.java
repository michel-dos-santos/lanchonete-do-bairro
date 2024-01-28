package br.com.lanchonete.hub.itau;

import br.com.lanchonete.hub.itau.dto.ItauRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "itauServiceFeignClient", url = "${api.itau.url}", fallbackFactory = ItauServiceFallback.class)
public interface ItauServiceFeignClient {

    @PostMapping(value = "")
    void billing(@RequestBody ItauRequestDTO itauRequestDTO);

}
