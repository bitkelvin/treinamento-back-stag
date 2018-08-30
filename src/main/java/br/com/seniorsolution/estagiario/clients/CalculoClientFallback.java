package br.com.seniorsolution.estagiario.clients;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.seniorsolution.estagiario.model.dto.v1.CalculoDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CalculoClientFallback implements CalculoClient {

	@Override
	public CalculoDTO fazerCalculo(double valor1, double valor2, String idEstagiario, boolean save) {
		log.warn("Cliente não foi acessado, gerando resposta de contingência...");
		
		CalculoDTO calculoDTO = new CalculoDTO();
		
		calculoDTO.setDivisao(new BigDecimal("0"));
		calculoDTO.setMultiplicacao(new BigDecimal("0"));
		calculoDTO.setSoma(new BigDecimal("0"));
		calculoDTO.setSubtracao(new BigDecimal("0"));
		
		return calculoDTO;
	}

}
