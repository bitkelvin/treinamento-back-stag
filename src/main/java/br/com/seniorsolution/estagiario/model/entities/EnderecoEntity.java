package br.com.seniorsolution.estagiario.model.entities;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {
    
    @NotEmpty(message = "{logradouro}")
    private String logradouro;
    
    @NotEmpty(message = "{cidade}")
    private String cidade;
    
    @NotEmpty(message = "{estado}")
    private String estado;
    
}
