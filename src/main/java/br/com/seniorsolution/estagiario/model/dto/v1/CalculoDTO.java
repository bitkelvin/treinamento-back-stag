package br.com.seniorsolution.estagiario.model.dto.v1;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CalculoDTO {
	
	private BigDecimal soma;
	private BigDecimal divisao;
	private BigDecimal multiplicacao;
	private BigDecimal subtracao;
	
}
