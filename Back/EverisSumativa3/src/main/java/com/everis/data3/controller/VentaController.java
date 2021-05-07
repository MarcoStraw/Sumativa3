package com.everis.data3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.data3.model.Venta;
import com.everis.data3.service.VentasService;

@Controller
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	VentasService vService;
	
	@RequestMapping("")
	public String inicioVenta(HttpSession session){
		Integer registrado = (Integer) session.getAttribute("registrado");
		if (registrado == 1 ) {
			return "venta.jsp";
		}
		return "login.jsp";
	}

	@RequestMapping("/agregar")
	public String agregarVenta(@RequestParam("nombre") String nombre){
		Venta venta = new Venta();
		venta.setNombre(nombre);
		vService.save(venta);
		return "redirect:/carro";
	}

}
