package com.FP_Final.FP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.FP_Final.FP.repository.ArticulosRepository;
import com.FP_Final.FP.model.Articulos;

import org.springframework.stereotype.Service;

@Service
public class ArticuloService {
	
	@Autowired
    private ArticulosRepository art;
	
	public List<Articulos> getAll(){
		return art.findAll();
	}
	
	 public List<Articulos> searchByKeyword(String keyword) {
	        return art.findByNombreContaining(keyword);
	    }

	
}
