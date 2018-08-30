package br.com.seniorsolution.estagiario.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.seniorsolution.estagiario.exceptions.DefaultException;
import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;
import br.com.seniorsolution.estagiario.services.MessageService;
import br.com.seniorsolution.estagiario.services.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private MessageService message;

	@Value("${imagem.caminho}")
	private Path caminhoPastaImagem;
	
	private static String validator1 = "storageService.erro1";

	private static String image = "image/";

	@Override
	public void armazenar(MultipartFile arquivo, EstagiarioEntity estagiario) {
		try {
			Files.copy(arquivo.getInputStream(), this.caminhoPastaImagem.resolve(arquivo.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			File oldfile = new File(this.caminhoPastaImagem.resolve(arquivo.getOriginalFilename()).toString());
			File newfile = new File(
					this.caminhoPastaImagem.resolve(estagiario.getNome() + estagiario.getCpf()).toString()
							+ arquivo.getContentType().replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new DefaultException(message.getMessage(validator1));
		}
	}

	@Override
	public void alterar(MultipartFile arquivo, EstagiarioEntity estagiario) {
		try {
			if (estagiario.getFoto() != null) {
				Files.delete(Paths.get(estagiario.getFoto()));
			}
			estagiario.setFoto(caminhoPastaImagem.toString() + "\\" + estagiario.getNome() + estagiario.getCpf()
					+ arquivo.getContentType().replace(image, "."));
			Files.copy(arquivo.getInputStream(), this.caminhoPastaImagem.resolve(arquivo.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			File oldfile = new File(this.caminhoPastaImagem.resolve(arquivo.getOriginalFilename()).toString());
			File newfile = new File(
					this.caminhoPastaImagem.resolve(estagiario.getNome() + estagiario.getCpf()).toString()
							+ arquivo.getContentType().replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new DefaultException(message.getMessage(validator1));
		}
	}

	@Override
	public Resource carregarArquivo(String nomeArquivo) {
		try {
			Path arquivo = caminhoPastaImagem.resolve(nomeArquivo);
			Resource resource = new UrlResource(arquivo.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new DefaultException(message.getMessage("storageService.erro2"));
			}
		} catch (MalformedURLException e) {
			throw new DefaultException(message.getMessage("storageService.erro3"));
		}
	}

	@Override
	public Resource carregarArquivo(EstagiarioEntity estagiario) {
		try {
			Path arquivo = Paths.get(estagiario.getFoto());
			Resource resource = new UrlResource(arquivo.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new DefaultException(message.getMessage("storageService.erro2"));
			}
		} catch (MalformedURLException e) {
			throw new DefaultException(message.getMessage("storageService.erro3"));
		}
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(caminhoPastaImagem);
		} catch (IOException e) {
			throw new DefaultException(message.getMessage("storageService.erro5"));
		}
	}
	
	private void toUseBoolean(Boolean validFileRename) {
		if(!validFileRename) {
			throw new DefaultException(message.getMessage(validator1));
		}
	}

}
