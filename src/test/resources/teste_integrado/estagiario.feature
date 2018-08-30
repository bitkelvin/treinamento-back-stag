Feature: testa o estagiario
  Scenario: cria um estagiario e testa o calculo
    When carrego os templates
	And crio a massa
	Then listo todos
	And listo por id
	And testo o cadastro
	And testo atualizar
	And testo deletar
	
  Scenario: limpa o diretorio de imagens
	And deleto o diretorio