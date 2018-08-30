package br.com.seniorsolution.estagiario.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.seniorsolution.estagiario.helper.FF4JHelper;
import br.com.seniorsolution.estagiario.integration.EstagiarioIntegration;
import br.com.seniorsolution.estagiario.model.adapters.EstagiarioDTOAdapter;
import br.com.seniorsolution.estagiario.model.dto.ListResultDTO;
import br.com.seniorsolution.estagiario.model.dto.v1.EstagiarioDTO;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.model.entities.QEstagiarioEntity;
import br.com.seniorsolution.estagiario.repositories.EstagiarioRepository;
import br.com.seniorsolution.estagiario.services.EstagiarioService;
import br.com.seniorsolution.estagiario.services.MessageService;
import br.com.seniorsolution.estagiario.services.StorageService;
import br.com.seniorsolution.estagiario.validations.Validador;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstagiarioServiceImpl implements EstagiarioService {

	private static final int MAXLIMIT = 20;

	@Autowired
	@Qualifier(FF4JHelper.GOOD_PATH)
	private EstagiarioIntegration estagiarioIntegration;

	@Autowired
	private EstagiarioRepository estagiarioRepository;

	@Autowired
	private MessageService message;

	@Autowired
	private Validador validador;

	@Autowired
	private StorageService storageService;

	@Value("${imagem.caminho}")
	private Path caminhoPastaImagem;

	@Override
	public ListResultDTO<EstagiarioDTO> listarTodos(String nome, String faculdade, String periodo, String semestre,
			String curso, int limit, int offset) {
		log.info(message.getMessage("estagiarioService.log1"));
		List<EstagiarioEntity> estagiarios = estagiarioRepository.findAll();
		this.validador.executarValidacoes(estagiarios);

		int thisLimit = limit;
		int thisOffSet = offset;

		Pageable page = null;

		if (thisLimit > 20) {
			log.warn(message.getMessage("estagiarioService.warn1"), MAXLIMIT);
			thisLimit = MAXLIMIT;
		}

		if (thisOffSet < 0) {
			thisOffSet = 0;
		}

		if (thisLimit > 0) {
			page = new PageRequest(thisOffSet, thisLimit);
		}

		QEstagiarioEntity qEstagiarios = new QEstagiarioEntity("estagiario");
		BooleanExpression filterByNome = qEstagiarios.nome.startsWithIgnoreCase(nome);
		BooleanExpression filterByFaculdade = qEstagiarios.faculdade.startsWithIgnoreCase(faculdade);
		BooleanExpression filterBySemestre = qEstagiarios.semestre.startsWithIgnoreCase(semestre);
		BooleanExpression filterByPeriodo = qEstagiarios.periodo.startsWithIgnoreCase(periodo);
		BooleanExpression filterByCurso = qEstagiarios.curso.startsWithIgnoreCase(curso);

		Page<EstagiarioEntity> findAll = this.estagiarioRepository.findAll(
				filterByNome.and(filterByFaculdade.and(filterByPeriodo.and(filterBySemestre.and(filterByCurso)))),
				page);

		List<EstagiarioDTO> estagiarioDTOs = new ArrayList<>();

		if (findAll != null) {
			for (EstagiarioEntity estags : findAll.getContent()) {
				estagiarioDTOs.add(this.estagiarioIntegration.calcularData(estags, false));
			}

			ListResultDTO<EstagiarioDTO> lstResult = new ListResultDTO<>();

			lstResult.setContent(estagiarioDTOs);
			lstResult.setFirstPage(findAll.isFirst());
			lstResult.setLastPage(findAll.isLast());
			lstResult.setTotalPages(findAll.getTotalPages());
			lstResult.setLimit(findAll.getSize());
			lstResult.setOffset(findAll.getNumber());
			lstResult.setNumberOfElements(findAll.getNumberOfElements());
			lstResult.setTotalElements(findAll.getTotalElements());

			return lstResult;
		}

		return null;
	}

	@Override
	public EstagiarioDTO listarPorId(String id) {
		EstagiarioEntity estagiario = this.estagiarioRepository.findOne(id);
		this.validador.executarValidacoes(estagiario);
		return this.estagiarioIntegration.calcularData(estagiario, false);
	}

	@Override
	public EstagiarioDTO cadastrar(EstagiarioDTO estagiarioDTO, MultipartFile file) {
		log.info(message.getMessage("estagiarioService.log3"), estagiarioDTO);
		EstagiarioEntity estagiario = EstagiarioDTOAdapter.converterDTOToDocument(estagiarioDTO);
		this.prepararValidacao(estagiario, false);
		if (file != null) {
			estagiario.setFoto(caminhoPastaImagem.toString() + "\\" + estagiario.getNome() + estagiario.getCpf()
					+ file.getContentType().replace("image/", "."));
			storageService.armazenar(file, estagiario);
		}
		this.estagiarioRepository.save(estagiario);
		return this.estagiarioIntegration.calcularData(estagiario, true);
	}

	@Override
	public EstagiarioDTO atualizar(EstagiarioDTO estagiarioDTO, String id, MultipartFile file) {

		EstagiarioEntity estagiario = EstagiarioDTOAdapter.converterDTOToDocument(estagiarioDTO);

		estagiario.setId(id);

		EstagiarioEntity currentEstagiario = estagiarioRepository.findOne(estagiario.getId());
		log.info(message.getMessage("estagiarioService.log4"), currentEstagiario);

		this.validador.executarValidacoes(currentEstagiario, estagiario);
		this.prepararValidacao(estagiario, true);

		log.info(message.getMessage("estagiarioService.log5"), estagiario);

		currentEstagiario.setNome(estagiario.getNome());
		currentEstagiario.setEmail(estagiario.getEmail());
		currentEstagiario.setCpf(estagiario.getCpf());
		currentEstagiario.setDataNascimento(estagiario.getDataNascimento());
		currentEstagiario.setEndereco(estagiario.getEndereco());
		currentEstagiario.setCuriosidade(estagiario.getCuriosidade());
		currentEstagiario.setFaculdade(estagiario.getFaculdade());
		currentEstagiario.setSemestre(estagiario.getSemestre());
		currentEstagiario.setCurso(estagiario.getCurso());
		currentEstagiario.setPeriodo(estagiario.getPeriodo());

		if (file != null) {
			storageService.alterar(file, currentEstagiario);
		}
		this.estagiarioRepository.save(currentEstagiario);
		return this.estagiarioIntegration.calcularData(currentEstagiario, true);
	}

	@Override
	public void remover(String id) throws IOException {
		log.info(message.getMessage("estagiarioService.log6"), id);
		EstagiarioDTO estagiario = this.listarPorId(id);
		if (estagiario.getFoto() != null) {
			Files.deleteIfExists(caminhoPastaImagem.resolve(estagiario.getFoto()));
		}
		this.estagiarioRepository.delete(id);
	}

	@Override
	public void removerFoto(String id) throws IOException {
		log.info(message.getMessage("estagiarioService.log6"), id);
		EstagiarioDTO estagiarioDTO = this.listarPorId(id);
		if (estagiarioDTO.getFoto() != null) {
			Files.deleteIfExists(caminhoPastaImagem.resolve(estagiarioDTO.getFoto()));
			estagiarioDTO.setFoto(null);
		}
		EstagiarioEntity estagiario = EstagiarioDTOAdapter.converterDTOToDocument(estagiarioDTO);
		this.estagiarioRepository.save(estagiario);
	}

	public void prepararValidacao(EstagiarioEntity estagiario, boolean alteracao) {

		QEstagiarioEntity qEstagiario = new QEstagiarioEntity("estagiario");
		BooleanExpression validEmail = qEstagiario.email.equalsIgnoreCase(estagiario.getEmail());
		BooleanExpression validCPF = qEstagiario.cpf.equalsIgnoreCase(estagiario.getCpf());

		Pageable page = null;

		Page<EstagiarioEntity> estagiarioCpf = estagiarioRepository.findAll(validCPF, page);
		Page<EstagiarioEntity> estagiarioEmail = estagiarioRepository.findAll(validEmail, page);

		this.validador.executarValidacoes(estagiario, estagiarioEmail, estagiarioCpf, alteracao);
	}

}