package br.com.seniorsolution.estagiario.model.dto.v1;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.seniorsolution.estagiario.model.entities.EnderecoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstagiarioDTO{

	@Id
    private String id;
    
    @NotEmpty(message = "{nome}")
    private String nome;
    
    @NotEmpty(message = "{email}")
    @Email(message = "{email.invalido}")
    private String email;
    
    @NotEmpty(message = "{cpf}")
    @CPF(message = "{cpf.invalido}")
    private String cpf;
    
    @JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd")
    @NotNull(message = "{dataNascimento}")
    private Date dataNascimento;
    
    @NotNull(message = "{endereco}")
    private EnderecoEntity endereco;
    
    @NotEmpty(message = "{curiosidade}")
    private String curiosidade;
    
    @NotEmpty(message = "{faculdade}")
    private String faculdade;
    
    @NotEmpty(message = "{semestre}")
    private String semestre;
    
    @NotEmpty(message = "{curso}")
    private String curso;
    
    @NotEmpty(message = "{periodo}")
    private String periodo;
    
    private String foto;
    
    private CalculoDTO calculo;
    
}