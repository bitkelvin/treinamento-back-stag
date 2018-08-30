package br.com.seniorsolution.estagiario.validations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.seniorsolution.estagiario.Application;
import br.com.seniorsolution.estagiario.services.impl.MessageSourceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
public class ValidacoesTest {

	private static final String ESTAGIARIOS_ERROS_DESC = "Não existem dados com este ID";
	private static final String ESTAGIARIOS_ERROS_LIST = "Erros encontrados:";
	private static final String VALIDACAO_WARN1 = "Nenhum estagiário encontrado";
	private static final String VALIDACAO_EMAIL_CPF = "Email e CPF duplicados";
	private static final String VALIDACAO_WARN2 = "Estagiário com ID {} não foi encontrado";
	private static final String VALIDACAO_EMAIL = "Email duplicado";
	private static final String VALIDACAO_CPF = "CPF duplicado";

    @Mock
	private MessageSourceServiceImpl message;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		doReturn(ESTAGIARIOS_ERROS_DESC).when(this.message).getMessage(ESTAGIARIOS_ERROS_DESC);
		doReturn(ESTAGIARIOS_ERROS_LIST).when(this.message).getMessage(ESTAGIARIOS_ERROS_LIST);
		doReturn(VALIDACAO_WARN1).when(this.message).getMessage(VALIDACAO_WARN1);
		doReturn(VALIDACAO_WARN2).when(this.message).getMessage(VALIDACAO_WARN2);
		doReturn(VALIDACAO_EMAIL_CPF).when(this.message).getMessage(VALIDACAO_EMAIL_CPF);
		doReturn(VALIDACAO_EMAIL).when(this.message).getMessage(VALIDACAO_EMAIL);
		doReturn(VALIDACAO_CPF).when(this.message).getMessage(VALIDACAO_CPF);
	}
	
	@Test
	public void excecaoIdNaoExiste() {
		String msg = this.message.getMessage("Não existem dados com este ID");
		assertThat(msg.equals(ESTAGIARIOS_ERROS_DESC), is(true));
	}
	
	@Test
	public void excecaoListErros() {
		String msg = this.message.getMessage("Erros encontrados:");
		assertThat(msg.contains(ESTAGIARIOS_ERROS_LIST), is(true));
	}
	
	@Test
	public void excecaoNotFoundEstagiario() {
		String msg = this.message.getMessage("Nenhum estagiário encontrado");
		assertThat(msg.equals(VALIDACAO_WARN1), is(true));
	}
	
	@Test
	public void excecaoNotFoundEstagiarioId() {
		String msg = this.message.getMessage("Estagiário com ID {} não foi encontrado");
		assertThat(msg.equals(VALIDACAO_WARN2), is(true));
	}
	
	@Test
	public void excecaoEmailCpfDuplicao() {
		String msg = this.message.getMessage("Email e CPF duplicados");
		assertThat(msg.equals(VALIDACAO_EMAIL_CPF), is(true));
	}
	
	@Test
	public void excecaoEmailDuplicao() {
		String msg = this.message.getMessage("Email duplicado");
		assertThat(msg.equals(VALIDACAO_EMAIL), is(true));
	}
	
	@Test
	public void excecaoCpfDuplicao() {
		String msg = this.message.getMessage("CPF duplicado");
		assertThat(msg.equals(VALIDACAO_CPF), is(true));
	}
	
}
