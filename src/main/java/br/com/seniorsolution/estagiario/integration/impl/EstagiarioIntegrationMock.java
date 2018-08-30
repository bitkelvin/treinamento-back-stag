package br.com.seniorsolution.estagiario.integration.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.seniorsolution.estagiario.helper.FF4JHelper;
import br.com.seniorsolution.estagiario.integration.EstagiarioIntegration;
import br.com.seniorsolution.estagiario.model.adapters.EstagiarioDTOAdapter;
import br.com.seniorsolution.estagiario.model.dto.v1.CalculoDTO;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(FF4JHelper.EVIL_PATH)
public class EstagiarioIntegrationMock implements EstagiarioIntegration {

	@Override
	public EstagiarioDTO calcularData(EstagiarioEntity estagiario, boolean save) {
		log.warn("Client mockado !");
		if (estagiario != null) {

			CalculoDTO calculoDTO = CalculoDTO.builder().divisao(new BigDecimal("2"))
					.multiplicacao(new BigDecimal("20000")).soma(new BigDecimal("300")).subtracao(new BigDecimal("100"))
					.build();

			EstagiarioDTO estagiarioDTO = EstagiarioDTOAdapter.converterDocumentToDTO(estagiario);

			estagiarioDTO.setCalculo(calculoDTO);

			return estagiarioDTO;
		} else {
			return null;
		}
	}
}
