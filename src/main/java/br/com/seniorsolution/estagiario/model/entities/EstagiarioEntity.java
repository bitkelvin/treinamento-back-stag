package br.com.seniorsolution.estagiario.model.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstagiarioEntity {

    private String id;
    
    private String nome;
    
    private String email;
    
    private String cpf;
    
    private Date dataNascimento;
    
    private EnderecoEntity endereco;
    
    private String curiosidade;
    
    private String faculdade;
    
    private String semestre;
    
    private String curso;
    
    private String periodo;
    
    private String foto;
    
    @CreatedDate
    private Date createdDate;
    
    @LastModifiedDate
    private Date lastModifiedDate;
    
}
