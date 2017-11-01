package com.azin.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	String upload(MultipartFile[] files);

	byte[] recuperarFotoTemporaria(String nome);

	void salvar(String foto);

	byte[] recuperar(String nome);

}
