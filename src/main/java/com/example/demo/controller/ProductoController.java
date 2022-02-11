package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	
	//Create
		@PostMapping
		public ResponseEntity <?> create(@RequestBody Producto prod){
			return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(prod));
		}
		
		
		//Read
		@GetMapping("/{id}")
			public ResponseEntity<?> read(@PathVariable(value= "id") Long productoId){
				Optional<Producto> oProducto = productoService.findById(productoId);
				
				if(!oProducto.isPresent()) {
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok(oProducto);
			}
		
		//Update
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Producto productoUpdate, @PathVariable(value= "id") Long productoId){
			Optional<Producto> producto= productoService.findById(productoId);
			
			if(!producto.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			producto.get().setDescripcion(productoUpdate.getDescripcion());
			producto.get().setPrecio(productoUpdate.getPrecio());
			producto.get().setCantidad(productoUpdate.getCantidad());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto.get()));
		}
		
		//Delete
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable(value= "id") Long productoId){
			
			if(!productoService.findById(productoId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			productoService.deleteById(productoId);
			return ResponseEntity.ok().build();
		}
		
		//Read all user
		@GetMapping
		public List<Producto> readAll (){
			
			List<Producto> producto = StreamSupport
					.stream(productoService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return producto;
		}
}
