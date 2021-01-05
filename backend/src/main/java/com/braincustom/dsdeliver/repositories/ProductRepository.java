package com.braincustom.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braincustom.dsdeliver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//buscando o nome pela ordem crescente
	List<Product> findAllByOrderByNameAsc();

}
