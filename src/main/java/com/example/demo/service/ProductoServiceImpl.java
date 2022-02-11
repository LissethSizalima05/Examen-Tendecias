package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	@Transactional(readOnly= true)
	public Iterable<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Page<Producto> findAll(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly= true)
	public Optional<Producto> findById(Long codigo) {
		return productoRepository.findById(codigo);
	}

	@Override
	public Producto save(Producto prod) {
		return productoRepository.save(prod);
	}

	@Override
	public void deleteById(Long codigo) {
		productoRepository.deleteById(codigo);
	}

}
