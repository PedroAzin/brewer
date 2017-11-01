package com.azin.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.azin.brewer.dto.FotoDto;
import com.azin.brewer.storage.FotoStorage;
import com.azin.brewer.storage.FotoStororageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotoController {
	
	@Autowired
	private FotoStorage fotoStorage;

	@PostMapping
	public DeferredResult<FotoDto> upload( @RequestParam(name="files[]") MultipartFile[] files) {
		
		DeferredResult<FotoDto> result = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStororageRunnable(files,result, fotoStorage));
		thread.start();
		return result;
	}
	
	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
		return fotoStorage.recuperarFotoTemporaria(nome);
	}
	
	@GetMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable String nome) {
		return fotoStorage.recuperar(nome);
	}
}
