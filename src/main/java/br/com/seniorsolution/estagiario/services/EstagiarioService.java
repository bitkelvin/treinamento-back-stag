package br.com.seniorsolution.estagiario.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.seniorsolution.estagiario.model.dto.ListResultDTO;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;

public interface EstagiarioService {
    
    ListResultDTO<EstagiarioDTO> listarTodos(String nome, String faculdade, String periodo, String semstre, String curso, int limit, int offset);
    
    EstagiarioDTO listarPorId(String id);
    
    EstagiarioDTO cadastrar(EstagiarioDTO estagiario, MultipartFile file);
    
    EstagiarioDTO atualizar(EstagiarioDTO estagiario, String id, MultipartFile file);
            
    void remover (String id) throws IOException;

	void removerFoto(String id) throws IOException;
    
}
