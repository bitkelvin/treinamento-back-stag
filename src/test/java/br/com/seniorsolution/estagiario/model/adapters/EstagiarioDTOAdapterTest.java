package br.com.seniorsolution.estagiario.model.adapters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.template.EstagiarioTemplateLoader;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(SpringJUnit4ClassRunner.class)
public class EstagiarioDTOAdapterTest {
    
    private EstagiarioEntity document;
    
    private EstagiarioDTO dto;
    
    @BeforeClass
    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.seniorsolution.estagiario.template");
    }
    
    @Before
    public void setup() {
        document = Fixture.from(EstagiarioEntity.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO);
        dto = Fixture.from(EstagiarioDTO.class).gimme(EstagiarioTemplateLoader.ESTAG_VALIDO_DTO);
    }
    
    @Test
    public void documentToDTO() {
        EstagiarioDTO documentToDTO = EstagiarioDTOAdapter.converterDocumentToDTO(document);
        assertThat(documentToDTO.getNome(), equalTo(document.getNome()));
        
        assertNull(documentToDTO.getCalculo());
        
        assertThat(documentToDTO.getCpf(), equalTo(document.getCpf()));
        assertThat(documentToDTO.getCuriosidade(), equalTo(document.getCuriosidade()));
        assertThat(documentToDTO.getCurso(), equalTo(document.getCurso()));
        assertThat(documentToDTO.getDataNascimento(), equalTo(document.getDataNascimento()));
        assertThat(documentToDTO.getEmail(), equalTo(document.getEmail()));
        assertThat(documentToDTO.getEndereco().getCidade(), equalTo(document.getEndereco().getCidade()));
        assertThat(documentToDTO.getEndereco().getLogradouro(), equalTo(document.getEndereco().getLogradouro()));
        assertThat(documentToDTO.getEndereco().getEstado(), equalTo(document.getEndereco().getEstado()));
        assertThat(documentToDTO.getFaculdade(), equalTo(document.getFaculdade()));
        
        assertNull(documentToDTO.getFoto());
        
        assertThat(documentToDTO.getId(), equalTo(document.getId()));
        assertThat(documentToDTO.getPeriodo(), equalTo(document.getPeriodo()));
        assertThat(documentToDTO.getSemestre(), equalTo(document.getSemestre()));
    }
    
    @Test
    public void DTOtoDocument() {
        EstagiarioEntity DTOtoDocument = EstagiarioDTOAdapter.converterDTOToDocument(dto);
        assertThat(DTOtoDocument.getNome(), equalTo(dto.getNome()));
        assertThat(DTOtoDocument.getCpf(), equalTo(dto.getCpf()));
        assertThat(DTOtoDocument.getCuriosidade(), equalTo(dto.getCuriosidade()));
        assertThat(DTOtoDocument.getCurso(), equalTo(dto.getCurso()));
        assertThat(DTOtoDocument.getDataNascimento(), equalTo(dto.getDataNascimento()));
        assertThat(DTOtoDocument.getEmail(), equalTo(dto.getEmail()));
        assertThat(DTOtoDocument.getEndereco().getCidade(), equalTo(dto.getEndereco().getCidade()));
        assertThat(DTOtoDocument.getEndereco().getLogradouro(), equalTo(dto.getEndereco().getLogradouro()));
        assertThat(DTOtoDocument.getEndereco().getEstado(), equalTo(dto.getEndereco().getEstado()));
        assertThat(DTOtoDocument.getFaculdade(), equalTo(dto.getFaculdade()));
        
        assertNull(DTOtoDocument.getFoto());
        
        assertThat(DTOtoDocument.getId(), equalTo(dto.getId()));
        assertThat(DTOtoDocument.getPeriodo(), equalTo(dto.getPeriodo()));
        assertThat(DTOtoDocument.getSemestre(), equalTo(dto.getSemestre()));
    }
    
    @Test
    public void documentNull() {
        EstagiarioEntity model = EstagiarioDTOAdapter.converterDTOToDocument(null);
        assertNull(model);
    }
    
    @Test
    public void dtoNull() {
        EstagiarioDTO model = EstagiarioDTOAdapter.converterDocumentToDTO(null);
        assertNull(model);
    }
}
