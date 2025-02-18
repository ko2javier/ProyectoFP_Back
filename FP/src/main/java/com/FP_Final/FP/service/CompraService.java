package com.FP_Final.FP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.FP_Final.FP.model.ComprasCliente;
import com.FP_Final.FP.repository.ComprasRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraService {
	

	@Autowired
    private ComprasRepository compras;
	
	
	 public List<ComprasCliente> getAll_Cliente(String dni) {
	        return compras.findByDnicliente(dni);
	    }
	 
	 public List<ComprasCliente> getAll_producto(String prod) {
	        return compras.findByProducto(prod);
	    }
	 public  List<ComprasCliente> getAll(){
		 return compras.findAll();
	 }
	 
}
