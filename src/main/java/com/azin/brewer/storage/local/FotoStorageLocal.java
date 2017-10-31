package com.azin.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.azin.brewer.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);

	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		this.local = FileSystems.getDefault().getPath(System.getenv("HOMEPATH"), ".brewerfotos");
		criarPastas();
	}

	private void criarPastas() {

		try {
			Files.createDirectories(this.local);
			this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Pastas criadas");
				LOGGER.debug("Pasta " + this.local.toAbsolutePath());
				LOGGER.debug("Pasta Temp " + this.localTemporario.toAbsolutePath());

			}
		} catch (IOException e) {
			new RuntimeException("Erro ao criar pastas", e);
		}

	}

	@Override
	public String upload(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			 novoNome =   renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString()
						+ FileSystems.getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar arquivo temporario", e);
			}
		}
		return novoNome;
	}

	private String renomearArquivo(String nomeOriginal) {
		return  UUID.randomUUID().toString() + "_" + nomeOriginal;
		
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Error ao recuperar foto");
		}
	}

}
