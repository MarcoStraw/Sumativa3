package com.everis.data3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.data3.model.Venta;
import com.everis.data3.repo.VentaRepo;

@Service
public class VentasService {
	
	@Autowired
	VentaRepo vRepo;
	
	public void save(Venta ventas) {
		vRepo.save(ventas);
	}

	public Object findAll() {
		return vRepo.findAll();
	}

	public Venta findById(Long id) {
		return vRepo.findById(id).get();
	}

}
