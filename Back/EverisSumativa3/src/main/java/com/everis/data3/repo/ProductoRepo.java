package com.everis.data3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.data3.model.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {

}
