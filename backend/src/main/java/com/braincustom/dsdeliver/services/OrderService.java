package com.braincustom.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.braincustom.dsdeliver.dto.OrderDTO;
import com.braincustom.dsdeliver.dto.ProductDTO;
import com.braincustom.dsdeliver.entities.Order;
import com.braincustom.dsdeliver.entities.OrderStatus;
import com.braincustom.dsdeliver.entities.Product;
import com.braincustom.dsdeliver.repositories.OrderRepository;
import com.braincustom.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	//inserindo um novo pedido no BD, já associados com os produtos dele
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		//instanciando um novo objeto do tipo Order(Pedido)
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		//fazendo um for para percorrer todos os ProductDTO e associá-los 
		for(ProductDTO prod : dto.getProducts()) {
			Product product = productRepository.getOne(prod.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
	//alterando um dado no cadastro
	@Transactional
	public OrderDTO setDelivered(Long id){
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);//pedido entregue!
		order = repository.save(order);
		return new OrderDTO(order);
	}
}
