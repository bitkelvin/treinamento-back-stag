package br.com.seniorsolution.estagiario.model.adapters;

import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;

public class EstagiarioDTOAdapter {
	
	private EstagiarioDTOAdapter() {}
	
	public static EstagiarioDTO converterDocumentToDTO(EstagiarioEntity estagiario) {
		if(estagiario == null) {
			return null;
		}
		return EstagiarioDTO
				.builder()
				.cpf(estagiario.getCpf())
				.curiosidade(estagiario.getCuriosidade())
				.curso(estagiario.getCurso())
				.email(estagiario.getEmail())
				.endereco(estagiario.getEndereco())
				.faculdade(estagiario.getFaculdade())
				.id(estagiario.getId())
				.nome(estagiario.getNome())
				.periodo(estagiario.getPeriodo())
				.semestre(estagiario.getSemestre())
				.dataNascimento(estagiario.getDataNascimento())
				.foto(estagiario.getFoto())
				.build();
	}
	
	public static EstagiarioEntity converterDTOToDocument(EstagiarioDTO estagiarioDTO) {
		if(estagiarioDTO == null) {
			return null;
		}
		return EstagiarioEntity
				.builder()
				.cpf(estagiarioDTO.getCpf())
				.curiosidade(estagiarioDTO.getCuriosidade())
				.curso(estagiarioDTO.getCurso())
				.email(estagiarioDTO.getEmail())
				.endereco(estagiarioDTO.getEndereco())
				.faculdade(estagiarioDTO.getFaculdade())
				.id(estagiarioDTO.getId())
				.nome(estagiarioDTO.getNome())
				.periodo(estagiarioDTO.getPeriodo())
				.semestre(estagiarioDTO.getSemestre())
				.dataNascimento(estagiarioDTO.getDataNascimento())
				.foto(estagiarioDTO.getFoto())
				.build();
	}
	
}