package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Producto;

public interface ProductoService {
	public Iterable<Producto> findAll();
	public Page<Producto> findAll(Pageable pageable);
	public Optional<Producto> findById(Long codigo);
	public Producto save(Producto prod);
	public void deleteById(Long codigo);
}
