package com.everis.data3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.data3.model.Rol;

@Repository
public interface RolRepo extends JpaRepository<Rol, Long> {
	
	List<Rol> findAll();
	
	List<Rol> findByNombre(String name);

}
