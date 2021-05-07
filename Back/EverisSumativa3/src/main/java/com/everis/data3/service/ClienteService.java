package com.everis.data3.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.data3.model.Cliente;
import com.everis.data3.repo.ClienteRepo;
import com.everis.data3.repo.RolRepo;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepo cRepo;
	
	@Autowired
	RolRepo rRepo;
	
	public Cliente save(Cliente cliente) {

		Long cantidad = cRepo.count();
		if(cantidad>0) {
			this.saveUser(cliente);
		}else {
			this.saveAdmin(cliente);
		}
		System.out.println(cantidad);
		
		return cliente; //pRepository.save(cliente);
	}

	public Cliente saveAdmin(Cliente cliente) {
		 String hashed = BCrypt.hashpw(cliente.getPassword(), BCrypt.gensalt());
		 cliente.setPassword(hashed);
		 cliente.setRoles(rRepo.findByNombre("ROL_ADMIN"));
		return cRepo.save(cliente);
	}
	
	public Cliente saveUser(Cliente cliente) {
		 String hashed = BCrypt.hashpw(cliente.getPassword(), BCrypt.gensalt());
		 cliente.setPassword(hashed);
		 cliente.setRoles(rRepo.findByNombre("ROL_USER"));
		return cRepo.save(cliente);
	}
	
	public List<Cliente> findAll() {
		
		return cRepo.findAll();
	}
	
	public Cliente findByEmail(String email) {
		return cRepo.findByEmail(email);
	}
	
	public boolean autenticacion(String email,String password) {
		Cliente cliente = cRepo.findByEmail(email);
		
		//existencia de el cliente
		if(cliente == null) {
			return false;
		}else {
			if(BCrypt.checkpw(password, cliente.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	

}
