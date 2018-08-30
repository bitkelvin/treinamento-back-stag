package br.com.seniorsolution.estagiario.services;

import br.com.seniorsolution.estagiario.model.dto.v1.CalculoDTO;

public interface CalculoService {
	
	public void save(CalculoDTO calculoDTO, String idEstagiario);
	
}
