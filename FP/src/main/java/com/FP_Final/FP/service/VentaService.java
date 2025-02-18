package com.FP_Final.FP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.FP_Final.FP.repository.VentasRepository;
import com.FP_Final.FP.model.Ventas;

import org.springframework.stereotype.Service;

@Service
public class VentaService {
	
	@Autowired
    private VentasRepository ventas;
	

	public List<Ventas> getAll() {
		return ventas.findAll();
	}
	
	public List<Ventas> getAll_username(String id_username) {
		return ventas.findByusername(id_username);
	}
	

}
