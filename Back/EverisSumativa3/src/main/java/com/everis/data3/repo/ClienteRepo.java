package com.everis.data3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.data3.model.Cliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

	Cliente findByEmail(String email);
}
