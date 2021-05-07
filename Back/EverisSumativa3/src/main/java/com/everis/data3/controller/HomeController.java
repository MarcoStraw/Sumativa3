package com.everis.data3.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everis.data3.model.Cliente;
import com.everis.data3.service.ClienteService;



@Controller
public class HomeController {
	@Autowired
	ClienteService cService;

	@RequestMapping("/")
	public String index(HttpSession session) {
		session.setAttribute("registrado",0);
		return "login.jsp";
	}
	
	@RequestMapping("/registro")
	public String registro(@Valid @ModelAttribute("cliente") Cliente cliente,Model model) {
		model.addAttribute("cliente", new Cliente());
		System.out.println("Registro");

		return "registro.jsp";
	}
	
}

