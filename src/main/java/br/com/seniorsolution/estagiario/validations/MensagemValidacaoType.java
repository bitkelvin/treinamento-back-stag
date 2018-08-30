package br.com.seniorsolution.estagiario.validations;

public enum MensagemValidacaoType {
	EMAIL ("email"),
	CPF ("cpf"),
	EMAIL_E_CPF ("cpfemail");
	
    private String nome;

    MensagemValidacaoType(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
    	return this.nome;
    }
    
}
