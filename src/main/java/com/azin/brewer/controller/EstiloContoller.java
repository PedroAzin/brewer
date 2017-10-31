package com.azin.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azin.brewer.model.Estilo;
import com.azin.brewer.service.EstiloService;

@Controller
@RequestMapping("/estilo")
public class EstiloContoller {

	@Autowired
	private EstiloService estiloService;

	@GetMapping("/form")
	public ModelAndView form(Estilo estilo) {
		return new ModelAndView("/estilo/form");
	}

	@PostMapping("/form")
	public ModelAndView salvar(@Valid Estilo estilo, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return form(estilo);
		}
		try {
			estiloService.salvar(estilo);
			attributes.addFlashAttribute("msg", "Estilo salvo com sucesso! " + estilo.getNome());
			return new ModelAndView("redirect:/estilo/form");
		} catch (RuntimeException e) {
			bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
			return form(estilo);
		}

	}

	@PostMapping(value = "/cadastroRapido")
	public ResponseEntity<?> cadastroRapido(@Valid @RequestBody Estilo estilo, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}

		try {
			estilo = estiloService.salvar(estilo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok(estilo);
	}
}
