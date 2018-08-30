package br.com.seniorsolution.estagiario.integration;

import org.ff4j.aop.Flip;

import br.com.seniorsolution.estagiario.helper.FF4JHelper;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;


public interface EstagiarioIntegration {
    
	@Flip(name = FF4JHelper.FEATURE_UID_F1, alterBean = FF4JHelper.EVIL_PATH)
    EstagiarioDTO calcularData(EstagiarioEntity estagiario, boolean save);
	
}
