package com.azin.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azin.brewer.controller.page.PageWrapper;
import com.azin.brewer.model.Cerveja;
import com.azin.brewer.model.Origem;
import com.azin.brewer.model.Sabor;
import com.azin.brewer.repository.Cervejas;
import com.azin.brewer.repository.Estilos;
import com.azin.brewer.repository.filters.CervejaFilter;
import com.azin.brewer.service.CervejaService;

@Controller
@RequestMapping("/cerveja")
public class CervejaController {

	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CervejaService cervejaService;
	
	@Autowired
	private Cervejas cervejas;

	@GetMapping("/form")
	public ModelAndView novo(Cerveja cerveja) {

		ModelAndView modelAndView = new ModelAndView("cerveja/form");
		modelAndView.addObject("estilos", estilos.findAll());
		modelAndView.addObject("origens", Origem.values());
		modelAndView.addObject("sabores", Sabor.values());
		return modelAndView;
	}

	@PostMapping("/form")
	public ModelAndView salvar(@Valid Cerveja cerveja, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			model.addAttribute(cerveja);
			return novo(cerveja);
		}
		cervejaService.save(cerveja);
		attributes.addFlashAttribute("msg", "Cerveja " + cerveja.getNome() + " salva com sucesso !");
		return new ModelAndView("redirect:/cerveja/form");

	}
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter , BindingResult result,@PageableDefault(size=2) Pageable pageable , HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cerveja/list");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		PageWrapper<Cerveja> pageWrapper = new PageWrapper<>(cervejas.listarPor(cervejaFilter,pageable), httpServletRequest);
		mv.addObject("page", pageWrapper);
		return mv;
	}

}
