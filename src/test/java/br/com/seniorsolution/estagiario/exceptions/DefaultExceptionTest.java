package br.com.seniorsolution.estagiario.exceptions;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.seniorsolution.estagiario.exceptions.DefaultException;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.repositories.EstagiarioRepository;
import br.com.seniorsolution.estagiario.template.EstagiarioTemplateLoader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class DefaultExceptionTest {
	
	EstagiarioEntity estagCpfInvalid, estagNameEmpty, estagEmailEmpty, estagCpfEmpty, estagDataNull, estagEnderecoNull,
	estagCuriosidadeEmpty, estagFaculdadeEmpty, estagSemestreEmpty, estagCursoEmpty, estagPeriodoEmpty;
	
	EstagiarioEntity massa;
	
	@Mock
	EstagiarioRepository dao;
	
	@BeforeClass
	public static void loadTemplates() {
	    FixtureFactoryLoader.loadTemplates("br.com.seniorsolution.estagiario.template");
	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		estagCpfInvalid = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_CPF_INVALIDO);
		estagNameEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_NOME_VAZIO);
		estagEmailEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_EMAIL_VAZIO);
		estagCpfEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_CPF_VAZIO);
		estagDataNull = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_DATA_NULL);
		estagCuriosidadeEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_EMAIL_VAZIO);
		estagFaculdadeEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_EMAIL_VAZIO);
		estagSemestreEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_EMAIL_VAZIO);
		estagCursoEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_EMAIL_VAZIO);
		estagPeriodoEmpty = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_EMAIL_VAZIO);
	}
	
	@Test(expected = DefaultException.class)
	public void cpfInvalidException() {
		Mockito.doThrow(new DefaultException("CPF inválido!")).when(dao).save(estagCpfInvalid);
		
		dao.save(estagCpfInvalid);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyNameException() {
		Mockito.doThrow(new DefaultException("Nome não pode ser vazio(a)")).when(dao).save(estagNameEmpty);
		
		dao.save(estagNameEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyEmailException() {
		Mockito.doThrow(new DefaultException("Email não pode ser vazio(a)")).when(dao).save(estagEmailEmpty);
		
		dao.save(estagEmailEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyCpfException() {
		Mockito.doThrow(new DefaultException("CPF não pode ser vazio(a)")).when(dao).save(estagCpfEmpty);
		
		dao.save(estagCpfEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void nullCpfException() {
		Mockito.doThrow(new DefaultException("Data de nascimento não pode ser nulo(a)")).when(dao).save(estagDataNull);
		
		dao.save(estagDataNull);
	}
	
	@Test(expected = DefaultException.class)
	public void nullEnderecoException() {
		Mockito.doThrow(new DefaultException("Endereço não pode ser nulo(a)")).when(dao).save(estagEnderecoNull);
		
		dao.save(estagEnderecoNull);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyCuriosidadeException() {
		Mockito.doThrow(new DefaultException("Curiosidade não pode ser vazio(a)")).when(dao).save(estagCuriosidadeEmpty);
		
		dao.save(estagCuriosidadeEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyFaculdadeException() {
		Mockito.doThrow(new DefaultException("Faculdade não pode ser vazio(a)")).when(dao).save(estagFaculdadeEmpty);
		
		dao.save(estagFaculdadeEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void emptySemestreException() {
		Mockito.doThrow(new DefaultException("Semestre não pode ser vazio(a)")).when(dao).save(estagSemestreEmpty);
		
		dao.save(estagSemestreEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyCursoException() {
		Mockito.doThrow(new DefaultException("Curso não pode ser vazio(a)")).when(dao).save(estagCursoEmpty);
		
		dao.save(estagCursoEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void emptyPeriodoException() {
		Mockito.doThrow(new DefaultException("Periodo não pode ser vazio(a)")).when(dao).save(estagPeriodoEmpty);
		
		dao.save(estagPeriodoEmpty);
	}
	
	@Test(expected = DefaultException.class)
	public void existsCpfEmailException() {
		Mockito.doThrow(new DefaultException("Email e CPF duplicados")).when(dao).save(massa);
		
		dao.save(massa);
	}
	
	@Test(expected = DefaultException.class)
	public void existsCpfException() {
		Mockito.doThrow(new DefaultException("Email duplicado")).when(dao).save(massa);
		
		dao.save(massa);
	}
	
	@Test(expected = DefaultException.class)
	public void existsEmailException() {
		Mockito.doThrow(new DefaultException("CPF duplicado")).when(dao).save(massa);
		
		dao.save(massa);
	}
	
	@Test(expected = DefaultException.class)
	public void invalidImageException() {
		Mockito.doThrow(new DefaultException("Imagem inválida!")).when(dao).save(massa);
		
		dao.save(massa);
	}
	
	@Test(expected = DefaultException.class)
	public void nullImageException() {
		Mockito.doThrow(new DefaultException("Imagem é obrigatória!")).when(dao).save(massa);
		
		dao.save(massa);
	}
	
	@Test(expected = DefaultException.class)
	public void invalidCharImageException() {
		Mockito.doThrow(new DefaultException("Caracteres para nome inválido!")).when(dao).save(massa);
		
		dao.save(massa);
	}
	
}
