package com.braincustom.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.braincustom.dsdeliver.dto.OrderDTO;
import com.braincustom.dsdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll(){
		List<OrderDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//criando um endpoint para salvar
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
		dto = service.insert(dto);
		//código 201 para retornar objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	//criando o PUT para salvar alterações
	@PutMapping("/{id}/delivered")
	public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id){
		OrderDTO dto = service.setDelivered(id);
		return ResponseEntity.ok().body(dto);
	}
	
}
