package com.azin.brewer.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azin.brewer.model.Cerveja;

@Controller
@RequestMapping("/cerveja")
public class CervejaController {
	
    private static final Logger logger = LogManager.getLogger(CervejaController.class);


	@GetMapping("/form")
	public String novo(Cerveja cerveja) {
		logger.error("TEste");
		return "cerveja/form";
	}

	@PostMapping("/form")
	public String salvar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			model.addAttribute("msg", "Erro");
			model.addAttribute(cerveja);
			return novo(cerveja);
		}

		attributes.addFlashAttribute("msg", "Cerveja " + cerveja.getNome() + "salva com sucesso !");
		return "redirect:/cerveja/form";

	}

}
