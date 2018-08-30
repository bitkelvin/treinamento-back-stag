package br.com.seniorsolution.estagiario.template;

import java.text.SimpleDateFormat;

import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EnderecoEntity;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EstagiarioTemplateLoader implements TemplateLoader {

	public static final String ESTAG_VALIDO = "estagiario-valido";
	public static final String END_VALIDO = "endereco-valido";
	
	public static final String ESTAG_ID_INVALIDO = "endereco-id-invalido";
	public static final String ESTAG_EMAIL_INVALIDO = "estagiario-email-invalido";
	public static final String ESTAG_EMAIL_CPF_INVALIDO = "estagiario-cpf-email-invalido";
	public static final String ESTAG_CPF_INVALIDO = "estagiario-cpf-invalido";

	public static final String ESTAG_NOME_VAZIO = "estagiario-nome-vazio";
	public static final String ESTAG_EMAIL_VAZIO = "estagiario-email-vazio";
	public static final String ESTAG_CPF_VAZIO = "estagiario-cpf-vazio";
	
	public static final String ESTAG_CURIOSIDADE_VAZIO = "estagiario-curiosidade-vazio";
	public static final String ESTAG_FACULDADE_VAZIO = "estagiario-faculdade-vazio";
	public static final String ESTAG_SEMESTRE_VAZIO = "estagiario-semestre-vazio";
	public static final String ESTAG_CURSO_VAZIO = "estagiario-curso-vazio";
	public static final String ESTAG_PERIODO_VAZIO = "estagiario-periodo-vazio";
	
	public static final String ESTAG_DATA_NULL = "estagiario-data-null";
	public static final String ESTAG_ENDERECO_NULL = "estagiario-endereco-null";

	public static final String ESTAG_VALIDO_DTO = "estagiario-valido-DTO";
	public static final String ESTAG_VALIDO_FOTO = "estagiario-valido-foto";
	
	@Override
	public void load() {
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_VALIDO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EnderecoEntity.class).addTemplate(END_VALIDO, new Rule() {{
			add("logradouro", random("Rua Teste", "Edifício Teste"));
			add("cidade", random("Cidade Teste 1", "Cidade Teste 2"));
			add("estado", random("São Paulo","Rio de Janeiro"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_CPF_INVALIDO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Bruno", "Dináh", "Bárbara"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_EMAIL_INVALIDO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_EMAIL_CPF_INVALIDO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Lucas", "Caio"));
			add("email", random("massa@test.com"));
			add("cpf", random("48192604802"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_ID_INVALIDO, new Rule() {{
			add("id", random("1", "1"));
			add("nome", random("Ana", "Junior"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioDTO.class).addTemplate(ESTAG_VALIDO_DTO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_NOME_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", "");
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_EMAIL_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("PH", "Adailton", "Bruno"));
			add("email", "");
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_CPF_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("PH", "Adailton", "Bruno"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", "");
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
	
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_DATA_NULL, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", null);
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
	
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_ENDERECO_NULL, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", null);
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
	
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_CURIOSIDADE_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", "");
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_FACULDADE_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", "");
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
	
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_SEMESTRE_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", "");
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_CURSO_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", "");
			add("periodo", random("Manhã", "Noite"));
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_PERIODO_VAZIO, new Rule() {{
			add("id", uniqueRandom("12345", "54321", "Test"));
			add("nome", random("Pedro", "Carlos", "Lisa"));
			add("email", uniqueRandom("massa@test.com", "massa222@test.com", "333@test.com"));
			add("cpf", uniqueRandom("48192604802", "51559227397", "21484834208"));
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", "");
		}});
		
		Fixture.of(EstagiarioEntity.class).addTemplate(ESTAG_VALIDO_FOTO, new Rule() {{
			add("id", "TesteTeste");
			add("nome", "imgtest");
			add("email", "sad@dasds.com");
			add("cpf", "54181111407");
			add("dataNascimento", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
			
			add("endereco", one(EnderecoEntity.class, END_VALIDO));
			
			add("curiosidade", random("Gosto de chocolate", "Não gosto de chocolate"));
			add("faculdade", random("Fatec", "Anhembi Morumbi"));
			add("semestre", random("6", "4", "2"));
			add("curso", random("Análise e Desenvolvimento de Sistemas", "Ciência da Computação"));
			add("periodo", random("Manhã", "Noite"));

			add("foto","");
		}});
	}

}
