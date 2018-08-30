package br.com.seniorsolution.estagiario.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;

public interface StorageService {
    
    public void armazenar(MultipartFile arquivo, EstagiarioEntity estagiario);
    
    public void alterar(MultipartFile arquivo, EstagiarioEntity estagiario);
    
    public Resource carregarArquivo(String nomeArquivo);

    public Resource carregarArquivo(EstagiarioEntity estagiario);
    
    public void init();
    
}
