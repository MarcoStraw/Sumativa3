package com.everis.data3.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.data3.model.Cliente;
import com.everis.data3.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService cService;
	
	@RequestMapping("/registro")
	public String inicioCliente(Model model) {
		model.addAttribute("listaCursos", cService.findAll());
		return "cliente.jsp";
	}
	
	@RequestMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente) {
		System.out.println("cliente - guardar");
		cliente= cService.save(cliente);
		
		return"";
	}
	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido,
			@RequestParam("email") String email,
			@RequestParam("password") String password
			){
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setEmail(email);
		cliente.setPassword(password);
		
		cliente= cService.save(cliente);
		
		
		return "redirect:/cliente/login";
	}
	
	///cliente/login
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			Model model) {
		
		//boolean respuesta = cService.autenticacion(email, password);
		
		
		if(cService.autenticacion(email, password)) {
			session.setAttribute("email", email);
			session.setAttribute("registrado",1);//boolean
			session.setAttribute("contador",100);
			
			String mail = (String) session.getAttribute("email");
			Integer registrado = (Integer) session.getAttribute("registrado");
			Integer contador = (Integer) session.getAttribute("contador");
		
			System.out.println(mail+" - "+registrado+" - "+contador);
			
			//model.addAttribute("registrado", true);
			return "index.jsp";
		}else {
			session.removeAttribute("registrado");
			session.setAttribute("registrado",0);
			//session.invalidate();
			model.addAttribute("mensaje", "Datos erroneos");
			return "login.jsp";
		}
		
	}
	///cliente/login
	@GetMapping("/login")
	public String getLogin(HttpSession session) {
		session.setAttribute("registrado",0);
		return "login.jsp";
	}
	
	
	
}
