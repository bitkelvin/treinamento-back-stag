package br.com.seniorsolution.estagiario.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.seniorsolution.estagiario.config.FeignConfig;
import br.com.seniorsolution.estagiario.config.RibbonConfig;
import br.com.seniorsolution.estagiario.model.dto.v1.CalculoDTO;

@RibbonClient(name="projeto-calculo", configuration = RibbonConfig.class)
@FeignClient(name="projeto-calculo", fallback = CalculoClientFallback.class, configuration = FeignConfig.class)
public interface CalculoClient {
	
	@RequestMapping(method = RequestMethod.GET, value="/api/v1", consumes="application/json")
	CalculoDTO fazerCalculo(@RequestParam("valor1") double valor1, @RequestParam("valor2") double valor2,
			@RequestParam("id") String idEstagiario, @RequestParam("save") boolean save);
	
}
