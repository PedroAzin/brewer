package com.azin.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.azin.brewer.storage.FotoStorage;
import com.azin.brewer.dto.FotoDto;

public class FotoStororageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDto> result;
	private FotoStorage fotoStorage;

	public FotoStororageRunnable(MultipartFile[] files, DeferredResult<FotoDto> result, FotoStorage fotoStorage) {
		this.files = files;
		this.result = result;
		this.fotoStorage = fotoStorage;
		
	}

	@Override
	public void run() {
		System.out.println("Files  " + files[0].getOriginalFilename());
		
		String contentType = files[0].getContentType();
		String novoNome = this.fotoStorage.upload(files);
		result.setResult(new FotoDto(novoNome, contentType));
	}

}
