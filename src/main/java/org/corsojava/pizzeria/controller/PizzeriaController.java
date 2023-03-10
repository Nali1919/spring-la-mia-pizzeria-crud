package org.corsojava.pizzeria.controller;

import java.util.List;

import org.corsojava.pizzeria.model.Pizzeria;
import org.corsojava.pizzeria.repository.PizzeriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzeria")

public class PizzeriaController {

	@Autowired
	PizzeriaRepository pizzeriarep;

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		List<Pizzeria> elencopizzeria;

		if (keyword != null && !keyword.isEmpty())
			elencopizzeria = pizzeriarep.findByNameLike("%" + keyword + "%");
		else
			elencopizzeria = pizzeriarep.findAll();
		model.addAttribute("elencopizzeria", elencopizzeria);
		return "index";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Pizzeria pizzeria = pizzeriarep.getReferenceById(id);
		model.addAttribute("detail", pizzeria);
		return "detail";
	}

	@GetMapping("/create") 
	public String create(Model model) {

		Pizzeria p = new Pizzeria();
		p.setName("Inserisci Nome Pizza");
		model.addAttribute("pizzeria", p);
		return "create";
	}

	@PostMapping("/create") 
	public String store(@Valid @ModelAttribute("pizzeria") Pizzeria formPizzeria, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "create";

		}
		pizzeriarep.save(formPizzeria);

		return "redirect:/pizzeria";

	}
	@GetMapping("/edit/{id}")		
	public String edit(@PathVariable("id") Integer id, Model model) {		
		Pizzeria pizza=pizzeriarep.getReferenceById(id);  
		
		model.addAttribute("pizza", pizza);
		return "edit";
	}
	
	@PostMapping("/edit/{id}")		
	public String update(
			@Valid @ModelAttribute Pizzeria formPizzeria,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "edit";
		
		pizzeriarep.save(formPizzeria);
		
		return "redirect:/pizzeria";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
	 
	   pizzeriarep.deleteById(id);
	   
	   return "redirect:/pizzeria";
	}

}
