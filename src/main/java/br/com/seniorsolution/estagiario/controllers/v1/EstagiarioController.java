package br.com.seniorsolution.estagiario.controllers.v1;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.seniorsolution.estagiario.model.adapters.EstagiarioDTOAdapter;
import br.com.seniorsolution.estagiario.model.dto.ListResultDTO;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.services.EstagiarioService;
import br.com.seniorsolution.estagiario.services.StorageService;
import br.com.seniorsolution.estagiario.validations.Validador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "EstagiariosControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstagiarioController {
	
    @Autowired
    private Validador validador;
    
    @Autowired
    private EstagiarioService estagiarioService;   
    
    @Autowired
    private StorageService storageService;  
    
    @RequestMapping(method=RequestMethod.GET)
    @ApiOperation("Retorna a lista de todos os estagiários")
    public ListResultDTO<EstagiarioDTO> listarTodos(
    		 @RequestParam(required = false, name = "nome", defaultValue = "") final String nome,
             @RequestParam(required = false, name = "faculdade", defaultValue = "") final String faculdade,
             @RequestParam(required = false, name = "periodo", defaultValue = "") final String periodo,
             @RequestParam(required = false, name = "semestre", defaultValue = "") final String semestre,
             @RequestParam(required = false, name = "curso", defaultValue = "") final String curso,
             @RequestParam(required = false, name = "limit", defaultValue = "0") final int limit,
             @RequestParam(required = false, name = "offset", defaultValue = "0") final int offset) {
        return this.estagiarioService.listarTodos(nome, faculdade, periodo, semestre, curso, limit, offset);
    }    
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET) 
    @ApiOperation("Restorna um estagiário por ID")
    public ResponseEntity<EstagiarioDTO> listarPorId(@PathVariable(name = "id") String id) {
        this.validador.executarValidacoes(id);
        return ResponseEntity.ok(this.estagiarioService.listarPorId(id));
    }    
    
    @RequestMapping(method = RequestMethod.POST)  
    @ApiOperation("Cadastra um estagiário")
    public ResponseEntity<EstagiarioDTO> cadastrar(MultipartFile file,
    		@Valid EstagiarioDTO estagiario,
    		BindingResult result) {
        this.validador.executarValidacoes(result);
        this.validador.executarValidacoes(file);
        return ResponseEntity.ok(this.estagiarioService.cadastrar(estagiario, file));
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT) 
    @ApiOperation("Realiza a alteração de um estagiário")
    public ResponseEntity<EstagiarioDTO> atualizar(MultipartFile file, @PathVariable(name = "id") String id,
		@Valid EstagiarioDTO estagiario, BindingResult result) {
        this.validador.executarValidacoes(id);
        this.validador.executarValidacoes(result);
        this.validador.executarValidacoes(file);
        return ResponseEntity.ok(this.estagiarioService.atualizar(estagiario, id, file));
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) 
    @ApiOperation("Exclui um estagiário")
    public ResponseEntity<Integer> remover(@PathVariable(name = "id") String id) throws IOException {
        this.validador.executarValidacoes(id);
        this.estagiarioService.remover(id);
        return ResponseEntity.ok(1);
    }
    
    @RequestMapping(value = "/removerFoto/{id}", method = RequestMethod.DELETE) 
    @ApiOperation("Exclui uma foto por ID do estagiario")
    public ResponseEntity<Integer> removerFoto(@PathVariable(name = "id") String id) throws IOException {
        this.validador.executarValidacoes(id);
        this.estagiarioService.removerFoto(id);
        return ResponseEntity.ok(1);
    }
    
    @RequestMapping(value = "/files/{filename:.+}", method = RequestMethod.GET)   
    @ApiOperation("Carrega imagem por nome")
    public ResponseEntity<Resource> carregarImagemPorNome(@PathVariable String filename) {
        Resource file = storageService.carregarArquivo(filename); 
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    @RequestMapping(value = "/imagem/{id}", method = RequestMethod.GET)     
    @ApiOperation("Carrega a imagem por ID")
    public ResponseEntity<Resource> carregarImagemPorID(@PathVariable(name = "id") String id) {
        this.validador.executarValidacoes(id);
        EstagiarioDTO estagiario = this.estagiarioService.listarPorId(id);
        Resource file = storageService.carregarArquivo(EstagiarioDTOAdapter.converterDTOToDocument(estagiario)); 
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename=\"" + file.getFilename() + "\"").body(file); 
    }
    
}
