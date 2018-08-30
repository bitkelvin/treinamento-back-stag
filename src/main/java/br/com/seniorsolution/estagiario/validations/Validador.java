package br.com.seniorsolution.estagiario.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.seniorsolution.estagiario.exceptions.DefaultException;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.services.EstagiarioService;
import br.com.seniorsolution.estagiario.services.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Validador {

	@Autowired
	private EstagiarioService estagiarioService;

	@Autowired
	private MessageService message;
	
	private static String validacao = "validacao.warn2";

	public void executarValidacoes(MultipartFile file) {
		if (file != null) {
			String type = file.getContentType();
			if (type.contains("jpeg") || type.contains("png") || type.contains("jpg")) {
				log.warn(message.getMessage("validacao.tipoimagem"));
			} else {
				log.warn(message.getMessage("validacao.imagem"));
				throw new DefaultException(message.getMessage("validacao.imagem"));
			}
		}
	}

	public void executarValidacoes(BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			String msg = message.getMessage("estagiario.erros.list")
					+ erros.toString().substring(1, erros.toString().length() - 1);
			log.warn(msg);
			throw new DefaultException(msg);
		}
	}

	public void executarValidacoes(String id){
		if (this.estagiarioService.listarPorId(id) == null) {
			String msg = message.getMessage("estagiario.erros.desc");
			log.warn(msg);
			throw new DefaultException(msg);
		} else {
			log.info(message.getMessage("estagiarioService.log2"), id);
		}
	}

	public void executarValidacoes(List<EstagiarioEntity> e) {
		if (e == null || e.isEmpty()) {
			String msg = message.getMessage("validacao.warn1");
			log.warn(msg);
			throw new DefaultException(msg);
		}
	}

	public void executarValidacoes(EstagiarioEntity estagiario, Page<EstagiarioEntity> estagiarioEmail,
			Page<EstagiarioEntity> estagiarioCpf, boolean alteracao) {

		this.validaCaracteres(estagiario);

		if ((verificaExistencia(estagiarioEmail) && verificaExistencia(estagiarioCpf))
				&& ((alteracao && verificaId(estagiarioEmail.getContent(), estagiario)
						&& verificaId(estagiarioCpf.getContent(), estagiario)) || !alteracao)) {
			this.retornaMensagemValidacao(MensagemValidacaoType.EMAIL_E_CPF);
		}

		if (verificaExistencia(estagiarioCpf)
				&& ((alteracao && verificaId(estagiarioCpf.getContent(), estagiario)) || !alteracao)) {
			this.retornaMensagemValidacao(MensagemValidacaoType.CPF);
		}

		if (verificaExistencia(estagiarioEmail)
				&& ((alteracao && verificaId(estagiarioEmail.getContent(), estagiario)) || !alteracao)) {
			this.retornaMensagemValidacao(MensagemValidacaoType.EMAIL);
		}
	}

	private boolean verificaId(List<EstagiarioEntity> estagiarioEncontrado, EstagiarioEntity estagiarioAtual) {
		return !estagiarioEncontrado.get(0).getId().equals(estagiarioAtual.getId());
	}

	private boolean verificaExistencia(Page<EstagiarioEntity> estagiarioEncontrado) {
		return estagiarioEncontrado != null && estagiarioEncontrado.getTotalElements() > 0;
	}

	private void validaCaracteres(EstagiarioEntity estagiario) {
		String[] caracteres = { "\"", "\\", "/", ":", "*", "?", ">", "<", "|" };
		for (String nome : caracteres) {
			if (estagiario.getNome().contains(nome)) {
				throw new DefaultException(this.message.getMessage("validacao.caracter"));
			}
		}
	}

	private void retornaMensagemValidacao(MensagemValidacaoType tipoMensagem) {
		String msg = null;
		switch (tipoMensagem.getNome()) {
		case "cpf":
			msg = message.getMessage("validacao.cpf");
			log.warn(msg);
			throw new DefaultException(message.getMessage("validacao.cpf"));
		case "email":
			msg = message.getMessage("validacao.email");
			log.warn(msg);
			throw new DefaultException(message.getMessage("validacao.email"));
		case "cpfemail":
			msg = message.getMessage("validacao.emailecpf");
			log.warn(msg);
			throw new DefaultException(msg);
		default:
			throw new DefaultException(message.getMessage("validacao.emailecpf"));
		}
	}

	public void executarValidacoes(EstagiarioEntity currentEstagiario, EstagiarioEntity estagiario) {
		if (currentEstagiario == null) {
			log.warn(message.getMessage(validacao), estagiario.getId());
		}
	}

	public void executarValidacoes(EstagiarioEntity estagiario) {
		if (estagiario == null) {
			log.warn(message.getMessage(validacao));
			throw new DefaultException(message.getMessage(validacao));
		}
	}

}
