package br.com.seniorsolution.estagiario.cucumber;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.doReturn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hamcrest.collection.IsCollectionWithSize;
import org.json.JSONException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import br.com.seniorsolution.estagiario.clients.CalculoClient;
import br.com.seniorsolution.estagiario.model.dto.v1.CalculoDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.repositories.EstagiarioRepository;
import br.com.seniorsolution.estagiario.template.EstagiarioTemplateLoader;
import br.com.seniorsolution.estagiario.util.IntegrationTestCommon;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.config.JsonConfig;
import io.restassured.parsing.Parser;
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType;

@ContextConfiguration //Don't ask
public class TesteIntegradoSteps extends IntegrationTestCommon {
		
	@Value("${local.server.port}")
	private int serverPort;

    @Value("${imagem.caminho}")
    private String src;
	
	private static final String ENDPOINT = "/api/v1/";

	@Mock
	private CalculoClient client;
	
	private List<EstagiarioEntity> massas = new ArrayList<>();

	@Autowired	
	private EstagiarioRepository estagiarioRepository;

	@When("^carrego os templates$")
	public void loadTemplates() throws Throwable  {
		FixtureFactoryLoader.loadTemplates("br.com.seniorsolution.estagiario.template");
		
		RestAssured.port = serverPort;
		RestAssured.defaultParser = Parser.JSON;
		RestAssured.config = RestAssured.config().jsonConfig(JsonConfig.jsonConfig().numberReturnType(NumberReturnType.BIG_DECIMAL));

	}

	@And("^crio a massa$")
	public void criacaoMassa() throws Throwable  {
		MockitoAnnotations.initMocks(this);
		
		// Alimenta a base de dados
		this.massas.add(Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO));
		this.massas.add(Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO));
		this.massas.add(Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO));

        EstagiarioEntity imgtest = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO_FOTO);

        imgtest.setFoto(System.getProperty("user.dir") + "\\" + src  + "\\" + imgtest.getNome() + imgtest.getCpf() + ".png");

		this.massas.add(imgtest);
		
		// Salva na base de dados
		this.estagiarioRepository.save(massas);
		
		doReturn(new CalculoDTO()).when(client).fazerCalculo(1, 1, "1", false);
	}
	
	@Then("^listo por id$")
	public void test_a_listarId() throws Throwable {
		RestAssured.
			get(ENDPOINT + this.massas.get(0)
				.getId()).
			then()
				.statusCode(200).
			assertThat()
				.body("email", equalTo(this.massas.get(0).getEmail()),
					"cpf", equalTo(this.massas.get(0).getCpf()),
					"curso", equalTo(this.massas.get(0).getCurso()),
					"semestre", equalTo(this.massas.get(0).getSemestre()),
					"faculdade", equalTo(this.massas.get(0).getFaculdade()),
					"periodo", equalTo(this.massas.get(0).getPeriodo()),
					"curiosidade", equalTo(this.massas.get(0).getCuriosidade()),
					"endereco.cidade", equalTo(this.massas.get(0).getEndereco().getCidade()),
					"endereco.estado", equalTo(this.massas.get(0).getEndereco().getEstado()),
					"endereco.logradouro", equalTo(this.massas.get(0).getEndereco().getLogradouro()),
					"nome", equalTo(this.massas.get(0).getNome()));
	}

	@And("^listo todos$")
	public void test_b_listarTodos()  throws Throwable {
		RestAssured.expect().statusCode(200).body("content", IsCollectionWithSize.hasSize(this.massas.size())).when()
				.get(ENDPOINT);
	}

	@And("^testo o cadastro$")
	public void test_c_cadastrar() throws IOException, JSONException, Throwable  {
		final byte[] file = IOUtils.toByteArray(getClass().getResourceAsStream("/imgtest.png"));
		
		RestAssured.
			given()
				.multiPart(new MultiPartSpecBuilder(file).fileName("imgtest.png")
				.controlName("file").mimeType("image/png").build())
                .param("id", "ididididdididi")
				.param("email", "happy@dasds.com").param("cpf", "20572923040")
				.param("curso", "TI").param("dataNascimento", "1996/03/30")
				.param("faculdade", "USP").param("semestre", "5").param("curiosidade", "Legal")
				.param("periodo", "Noite").param("endereco.logradouro", "Rua")
				.param("endereco.cidade", "Boa").param("endereco.estado", "Noite")
				.param("nome", "Pogba")
			.expect()
				.statusCode(200)
				.body("email", equalTo("happy@dasds.com"), "cpf", equalTo("20572923040"),
						"curso", equalTo("TI"), "semestre", equalTo("5"),
						"dataNascimento", equalTo("1996-03-30"),
						"faculdade", equalTo("USP"), "periodo", equalTo("Noite"),
						"curiosidade", equalTo("Legal"), "endereco.cidade", equalTo("Boa"),
						"endereco.estado", equalTo("Noite"), "endereco.logradouro", equalTo("Rua"),
						"nome", equalTo("Pogba"), "id", notNullValue())
			.when()
				.post(ENDPOINT);
	}
	
	@And("^testo atualizar$")
	public void test_d_atualizar() throws IOException, JSONException, Throwable  {
		final byte[] file = IOUtils.toByteArray(getClass().getResourceAsStream("/imgtest.png"));
		
		RestAssured.
			given()
            .multiPart(new MultiPartSpecBuilder(file).fileName("imgtest.png")
                .controlName("file").mimeType("image/png").build())
                .param("email", "happy@dasds.com").param("cpf", "20572923040")
                .param("curso", "TI").param("dataNascimento", "1996/03/30")
                .param("faculdade", "USP").param("semestre", "5").param("curiosidade", "Legal")
                .param("periodo", "Noite").param("endereco.logradouro", "Rua")
                .param("endereco.cidade", "Boa").param("endereco.estado", "Noite")
                .param("nome", "MeuNomeAgoraEHOUTROOUTROASHDHSOASHOOHS")
			.expect()
				.statusCode(200)
				.body("email", equalTo("happy@dasds.com"), "cpf", equalTo("20572923040"),
						"curso", equalTo("TI"), "semestre", equalTo("5"),
						"dataNascimento", equalTo("1996-03-30"),
						"faculdade", equalTo("USP"), "periodo", equalTo("Noite"),
						"curiosidade", equalTo("Legal"), "endereco.cidade", equalTo("Boa"),
						"endereco.estado", equalTo("Noite"), "endereco.logradouro", equalTo("Rua"),
						"nome", equalTo("MeuNomeAgoraEHOUTROOUTROASHDHSOASHOOHS"))
			.when()
				.put(ENDPOINT + "ididididdididi");
	}
	
	@And("^testo deletar$")
	public void test_e_deletar()  throws Throwable {
		List<EstagiarioEntity> findAll = this.estagiarioRepository.findAll();
		
		RestAssured.
			given()
				.param("id", findAll.get(3).getId())
			.expect()
				.statusCode(200)
				.body(containsString("1"))
			.when()
				.delete(ENDPOINT + findAll.get(3).getId());
	}
	
	@And("^deleto o diretorio$")
	public void clear_base()  throws Throwable {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\" + src  + "\\"));
	}
}
