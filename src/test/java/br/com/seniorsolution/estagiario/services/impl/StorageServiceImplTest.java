package br.com.seniorsolution.estagiario.services.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;

import br.com.seniorsolution.estagiario.Application;
import br.com.seniorsolution.estagiario.services.impl.MessageSourceServiceImpl;
import br.com.seniorsolution.estagiario.services.impl.StorageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
public class StorageServiceImplTest {
	
	private static final String STORAGE_SERVICE_ERRO1 = "Falha de armazenamento!";
	private static final String STORAGE_SERVICE_ERRO2 = "Imagem inválida ou inexistente!";
	private static final String STORAGE_SERVICE_ERRO3 = "Erro ao carregar a imagem!";
	private static final String STORAGE_SERVICE_ERRO4 = "Não foi possível inicializar o armazenamento!";
	private static final String STORAGE_SERVICE_ERRO5 = "Imagem é obrigatória!";
	private static final String STORAGE_SERVICE_ERRO6 = "Imagem inválida!";
	
	@Mock
    private MessageSourceServiceImpl message;
	
	@InjectMocks
	private StorageServiceImpl service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		doReturn(STORAGE_SERVICE_ERRO1).when(this.message).getMessage(STORAGE_SERVICE_ERRO1);
		doReturn(STORAGE_SERVICE_ERRO2).when(this.message).getMessage(STORAGE_SERVICE_ERRO2);
		doReturn(STORAGE_SERVICE_ERRO3).when(this.message).getMessage(STORAGE_SERVICE_ERRO3);
		doReturn(STORAGE_SERVICE_ERRO4).when(this.message).getMessage(STORAGE_SERVICE_ERRO4);
		doReturn(STORAGE_SERVICE_ERRO5).when(this.message).getMessage(STORAGE_SERVICE_ERRO5);
		doReturn(STORAGE_SERVICE_ERRO6).when(this.message).getMessage(STORAGE_SERVICE_ERRO6);

	}
	
	@Test
	public void excecaoFalhaArmazenamento() {
		String msg = this.message.getMessage("Falha de armazenamento!");
		assertThat(msg.equals(STORAGE_SERVICE_ERRO1), is(true));
	}
	
	@Test
	public void excecaoImagemInvalid() {
		String msg = this.message.getMessage("Imagem inválida ou inexistente!");
		assertThat(msg.equals(STORAGE_SERVICE_ERRO2), is(true));
	}
	
	@Test
	public void excecaoErroCarregar() {
		String msg = this.message.getMessage("Erro ao carregar a imagem!");
		assertThat(msg.equals(STORAGE_SERVICE_ERRO3), is(true));
	}
	
	@Test
	public void excecaoNaoInit() {
		String msg = this.message.getMessage("Não foi possível inicializar o armazenamento!");
		assertThat(msg.equals(STORAGE_SERVICE_ERRO4), is(true));
	}
	
	@Test
	public void excecaoImagemNull() {
		String msg = this.message.getMessage("Imagem é obrigatória!");
		assertThat(msg.equals(STORAGE_SERVICE_ERRO5), is(true));
	}
	
	@Test
	public void excecaoImagemInv() {
		String msg = this.message.getMessage("Imagem inválida!");
		assertThat(msg.equals(STORAGE_SERVICE_ERRO6), is(true));
	}
	
}
