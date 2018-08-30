package br.com.seniorsolution.estagiario.services.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.seniorsolution.estagiario.clients.CalculoClient;
import br.com.seniorsolution.estagiario.integration.EstagiarioIntegration;
import br.com.seniorsolution.estagiario.model.adapters.EstagiarioDTOAdapter;
import br.com.seniorsolution.estagiario.model.dto.ListResultDTO;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.repositories.EstagiarioRepository;
import br.com.seniorsolution.estagiario.services.MessageService;
import br.com.seniorsolution.estagiario.services.StorageService;
import br.com.seniorsolution.estagiario.template.EstagiarioTemplateLoader;
import br.com.seniorsolution.estagiario.validations.Validador;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class EstagiarioServiceImplTest {

	private EstagiarioEntity massa1;
	private EstagiarioEntity massa2;
	private EstagiarioEntity massa3;
	private EstagiarioEntity massaEmailInvalido;
	private EstagiarioEntity massaEmailCpfInvalido;
	private EstagiarioEntity massaCpfInvalido;

	private EstagiarioDTO massaDTO;
	
	private EstagiarioEntity entity;
	private EstagiarioEntity entityConvert;
	private EstagiarioDTO dto;
	private EstagiarioDTO dtoConvert;
	
	private List<EstagiarioEntity> estagiarios = new ArrayList<>();
	
	@Mock
	private EstagiarioRepository dao;

	@Mock
    private MessageService message;
    
	@Mock
    private Validador validador;

	@Mock 
    private StorageService storageService; 
	
	@Mock
    private Path caminhoPastaImagem;
	
	@Mock
    private CalculoClient calculoClient;
	
	@Mock
    private EstagiarioDTOAdapter dtoAdapter;
	
	@Mock
	private EstagiarioIntegration integration;
	
	@InjectMocks
	private EstagiarioServiceImpl service;

	@BeforeClass
	public static void loadTemplates() {
	    FixtureFactoryLoader.loadTemplates("br.com.seniorsolution.estagiario.template");
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		entity = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO);
		dtoConvert = EstagiarioDTOAdapter.converterDocumentToDTO(entity);
		
		dto = Fixture.from(EstagiarioDTO.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO_DTO);
		entityConvert = EstagiarioDTOAdapter.converterDTOToDocument(dto);
		
		massa1 = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO);
		massa2 = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO);
		massa3 = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO);
		
		massaDTO = Fixture.from(EstagiarioDTO.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO_DTO);
		
		estagiarios = Arrays.asList(massa1, massa2);
		
		doReturn(estagiarios).when(dao).findAll();
		doNothing().when(validador).executarValidacoes(estagiarios);
	}

	@Test()
	public void listarTodos() {
		List<EstagiarioEntity> listarTodos = this.dao.findAll();
		assertThat(listarTodos, IsCollectionContaining.hasItems(massa1, massa2));
	}
	
	@Test()
	public void listaVazia() {
		doReturn(null).when(dao).findAll();
		
		List<EstagiarioEntity> estagiarios = this.dao.findAll();
		
		assertNull(estagiarios);
	}
	
	@Test()
	public void listarTodosWithFilter(){
		ListResultDTO<EstagiarioDTO> listDto = null;
		
		doReturn(listDto).when(dao).findAll(Matchers.any(BooleanExpression.class));
		ListResultDTO<EstagiarioDTO> listarTodos = this.service.listarTodos("", "", "", "", "", 0, 0);
		assertThat(listarTodos, nullValue());
	}
	
	@Test()
	public void listarPorId() {
		doReturn(massa1).when(dao).findOne(massa1.getId());
		doReturn(EstagiarioDTOAdapter.converterDocumentToDTO(massa1)).when(integration).calcularData(massa1, false);
		
		EstagiarioDTO findOne = this.service.listarPorId(massa1.getId());
		
		assertThat(findOne.equals(EstagiarioDTOAdapter.converterDocumentToDTO(massa1)), is(true));
	}
	
	@Test()
	public void estagNotFoundId() {
		doReturn(null).when(dao).findOne("12345");
		doReturn(null).when(integration).calcularData(null, false);
		
		EstagiarioDTO listarPorId = this.service.listarPorId("12345");
		
		assertNull(listarPorId);
	}
	
	@Test()
	public void cadastrar() {
		doReturn(massa3).when(dao).save(massa3);
		
		EstagiarioEntity save = this.dao.save(massa3);
		
		assertThat(save.equals(massa3), is(true));
	}
	
	@Test()
	public void cadastrarEmailInvalido() {
		doReturn(null).when(dao).save(massaEmailInvalido);
		
		EstagiarioEntity save = this.dao.save(massaEmailCpfInvalido);
		
		assertNull(save);
	}
	
	@Test()
	public void cadastrarCpfInvalido() {
		doReturn(null).when(dao).save(massaCpfInvalido);
		
		EstagiarioEntity save = this.dao.save(massaCpfInvalido);
		
		assertNull(save);
	}
	
	@Test()
	public void cadastrarEmailCpfInvalido() {
		doReturn(null).when(dao).save(massaEmailCpfInvalido);
		
		EstagiarioEntity save = this.dao.save(massaEmailCpfInvalido);
		
		assertNull(save);
	}
	
	@Test()
	public void atualizar() {
		doReturn(massa3).when(dao).save(massa3);
		doReturn(massa3).when(dao).findOne(massa3.getId());
		doReturn(EstagiarioDTOAdapter.converterDocumentToDTO(massa3)).when(integration).calcularData(massa3, true);
		
		EstagiarioDTO atualizar = this.service.atualizar(massaDTO, massa3.getId(), null);
		
		assertThat(atualizar, any(EstagiarioDTO.class));
	}
	
	@Test
	public void dtoToDocument() {
		assertThat(entity.getId(), equalTo(dtoConvert.getId()));
		assertThat(entity.getNome(), equalTo(dtoConvert.getNome()));
		assertThat(entity.getEmail(), equalTo(dtoConvert.getEmail()));
		assertThat(entity.getCpf(), equalTo(dtoConvert.getCpf()));
		assertThat(entity.getDataNascimento(), equalTo(dtoConvert.getDataNascimento()));
		assertThat(entity.getEndereco().getLogradouro(), equalTo(dtoConvert.getEndereco().getLogradouro()));
		assertThat(entity.getEndereco().getCidade(), equalTo(dtoConvert.getEndereco().getCidade()));
		assertThat(entity.getEndereco().getEstado(), equalTo(dtoConvert.getEndereco().getEstado()));
		assertThat(entity.getCuriosidade(), equalTo(dtoConvert.getCuriosidade()));
		assertThat(entity.getFaculdade(), equalTo(dtoConvert.getFaculdade()));
		assertThat(entity.getSemestre(), equalTo(dtoConvert.getSemestre()));
		assertThat(entity.getCurso(), equalTo(dtoConvert.getCurso()));
		assertThat(entity.getPeriodo(), equalTo(dtoConvert.getPeriodo()));
	}
	
	@Test
	public void documentToDto() {
		assertThat(dto.getId(), equalTo(entityConvert.getId()));
		assertThat(dto.getNome(), equalTo(entityConvert.getNome()));
		assertThat(dto.getEmail(), equalTo(entityConvert.getEmail()));
		assertThat(dto.getCpf(), equalTo(entityConvert.getCpf()));
		assertThat(dto.getDataNascimento(), equalTo(entityConvert.getDataNascimento()));
		assertThat(dto.getEndereco().getLogradouro(), equalTo(entityConvert.getEndereco().getLogradouro()));
		assertThat(dto.getEndereco().getCidade(), equalTo(entityConvert.getEndereco().getCidade()));
		assertThat(dto.getEndereco().getEstado(), equalTo(entityConvert.getEndereco().getEstado()));
		assertThat(dto.getCuriosidade(), equalTo(entityConvert.getCuriosidade()));
		assertThat(dto.getFaculdade(), equalTo(entityConvert.getFaculdade()));
		assertThat(dto.getSemestre(), equalTo(entityConvert.getSemestre()));
		assertThat(dto.getCurso(), equalTo(entityConvert.getCurso()));
		assertThat(dto.getPeriodo(), equalTo(entityConvert.getPeriodo()));
	}
	
	@Test
	public void documentNull() {
		EstagiarioEntity estagEntity = null;
		
		EstagiarioDTO estagDTO = EstagiarioDTOAdapter.converterDocumentToDTO(estagEntity);
		
		assertNull(estagDTO);
	}
	
	@Test
	public void dtoNull() {
		EstagiarioDTO estagDTO = null;
		
		EstagiarioEntity estagEntity = EstagiarioDTOAdapter.converterDTOToDocument(estagDTO);
		
		assertNull(estagEntity);
	}
	
}