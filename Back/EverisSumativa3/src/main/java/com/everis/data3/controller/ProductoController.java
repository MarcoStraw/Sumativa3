package com.everis.data3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.data3.model.Producto;
import com.everis.data3.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoService pService;
	
private static final int CANT_PRODUCTOS = 3;
	
	@RequestMapping("")
	public String inicioProducto(HttpSession session,Model model) {
		Integer registrado = (Integer) session.getAttribute("registrado");
		if(registrado==1) {
			model.addAttribute("listaProductos", pService.findAll());
			
			Page<Producto> productos= pService.productosPaginados(0, CANT_PRODUCTOS);
			
			int totalPagina= productos.getTotalPages();
			model.addAttribute("totalPagina", totalPagina);
			model.addAttribute("productos", productos);
			return "producto.jsp";
		}
		
		return "login.jsp";
	}
	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam("nombre") String nombre,
			@RequestParam("desc") String descripcion,
			@RequestParam("precio") String precio
			) {
		Producto prod = new Producto();
		prod.setNombre(nombre);
		prod.setPrecio(Float.parseFloat(precio));
		prod.setDescripcion(descripcion);
		
		pService.save(prod);
		
		return "redirect:/producto";
	}

	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id,HttpSession session, Model model) {
		Producto prod= pService.findById(id);	
		model.addAttribute("producto", prod);
		return "editproducto.jsp";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute("producto")Producto prod,HttpSession session ) {
		pService.save(prod);
		return "redirect:/producto";
	}
	
	/**
	 * Paginacion
	 * */
	@RequestMapping("/paginacion/{numeroPagina}")
	public String getProductosPagina(@PathVariable("numeroPagina") int numeroPagina,
			Model model) {
		//paginas iterable comienzan en 0 cero. 1 a maxPag (ultima pagina)
		Page<Producto> productos= pService.productosPaginados(numeroPagina-1, CANT_PRODUCTOS);
		//productos.
		int totalPagina= productos.getTotalPages();
		model.addAttribute("totalPagina", totalPagina);
		model.addAttribute("productos", productos);
		
		return "producto.jsp";
	}
	

}
