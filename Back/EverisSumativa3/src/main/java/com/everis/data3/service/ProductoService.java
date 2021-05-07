package com.everis.data3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.everis.data3.model.Producto;
import com.everis.data3.repo.ProductoRepo;

@Service
public class ProductoService {

	@Autowired
	ProductoRepo pRepo;

	public Page<Producto> productosPaginados(int numeroPagina, int cantElementos) {

		// obtener todos los productos y ordenarlos por nombre de forma ascendente
		PageRequest pageRequest = PageRequest.of(numeroPagina, cantElementos);

		return pRepo.findAll(pageRequest);
	}

	public void save(Producto curso) {
		pRepo.save(curso);
	}

	public List<Producto> findAll() {

		return pRepo.findAll();
	}

	public Producto findById(Long id) {
		return pRepo.findById(id).get();
	}

}
