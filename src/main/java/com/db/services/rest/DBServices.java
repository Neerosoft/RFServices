package com.db.services.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.services.daos.ProductsDAO;
import com.db.services.entitys.Products;

@RestController
@RequestMapping("products")
public class DBServices {
	
	
	@Autowired
	private ProductsDAO dao;
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Products>>getProducts(){
		List<Products>lstproductos=dao.findAll();		
		
		return ResponseEntity.ok(lstproductos);
	}
	
	
	@RequestMapping(value="{id}")
	public ResponseEntity<Products> getProductsById(@PathVariable("id") Long id){
		Optional<Products>optionalproductos=dao.findById(id);
		if(optionalproductos.isPresent()) {
			return ResponseEntity.ok(optionalproductos.get());
		}
		else {
			return ResponseEntity.noContent().build();
			
		}
		
		
	}
	
	
	@PostMapping
	public ResponseEntity<Products> createProduct(@RequestBody Products product ){
		Products newProduct=dao.save(product);
		return ResponseEntity.ok(newProduct);
		
	}
	
	@DeleteMapping(value="{id}")	
	public ResponseEntity<Void>deleteProduct(@PathVariable("id") Long id){
		dao.deleteById(id);		
		return ResponseEntity.ok(null);
		
	}
	
	@PutMapping
	public ResponseEntity<Products> updateProduct(@RequestBody Products product){
		Optional<Products> optional=dao.findById(product.getId());
		if(optional.isPresent()) {
			Products updateProducts=optional.get();
			updateProducts.setName(product.getName());
			dao.save(updateProducts);
			return ResponseEntity.ok(updateProducts);
			
		}
		else {
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String saludo() {
		return "Hola Spring";
	}

}
