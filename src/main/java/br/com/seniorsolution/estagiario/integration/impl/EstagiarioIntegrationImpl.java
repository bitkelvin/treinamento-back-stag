package br.com.seniorsolution.estagiario.integration.impl;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.seniorsolution.estagiario.clients.CalculoClient;
import br.com.seniorsolution.estagiario.helper.FF4JHelper;
import br.com.seniorsolution.estagiario.integration.EstagiarioIntegration;
import br.com.seniorsolution.estagiario.model.adapters.EstagiarioDTOAdapter;
import br.com.seniorsolution.estagiario.model.dto.v1.CalculoDTO;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(FF4JHelper.GOOD_PATH)
public class EstagiarioIntegrationImpl implements EstagiarioIntegration {

	@Autowired
	private CalculoClient client;

	@Override
	public EstagiarioDTO calcularData(EstagiarioEntity estagiario, boolean save) {
		log.warn("Cliente sendo acessado...");
		GregorianCalendar calendar = new GregorianCalendar();

		if (estagiario != null) {
			calendar.setTime(estagiario.getDataNascimento());

			double dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
			double ano = calendar.get(GregorianCalendar.YEAR);

			CalculoDTO calculoDTO = client.fazerCalculo(dia, ano, estagiario.getId(), save);

			EstagiarioDTO estagiarioDTO = EstagiarioDTOAdapter.converterDocumentToDTO(estagiario);

			estagiarioDTO.setCalculo(calculoDTO);

			return estagiarioDTO;
		} else {
			return null;
		}
	}
}
