package com.FP_Final.FP.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.FP_Final.FP.repository.ArticulosRepository;
import com.FP_Final.FP.model.Articulos;
import com.FP_Final.FP.model.UpdateDTO;

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
	 
	 public List<Articulos> updateStockBatch(List<UpdateDTO> updates) {
		    // 1- Itero sobre la lista de actualizaciones
		 
		    List<Articulos> updatedList = new ArrayList<>();
		    for (UpdateDTO update : updates) {
		        // 2- Busco el artículo por código
		    	
		        Articulos articulo = art.findByCodigo(update.getCodigo());
		        if (articulo == null || articulo.getCantidad() < update.getCantidadVendida()) {
		            throw new RuntimeException("Artículo no encontrado o cantidad insuficiente: " + update.getCodigo());
		        }
		        // 3- hago update to stock
		        articulo.setCantidad(articulo.getCantidad() - update.getCantidadVendida());
		        
		        // 4- Save !!
		        updatedList.add(art.save(articulo));
		    }
		    return updatedList;
		}
	 
	 public Articulos updateItem(UpdateDTO updates) {
		    // 1- Busco el artículo
		 
		 Articulos articulo = art.findByCodigo(updates.getCodigo());
		 
		        if (articulo == null ) {
		            throw new RuntimeException("Artículo no encontrado : " + updates.getCodigo());
		        }
		        // 3- hago update to stock
		        articulo.setCantidad(updates.getCantidad());
		        articulo.setPrecio(updates.getPrecio());
		        
		        // 4- Save !!
		        return art.save(articulo);
		        
		    
		
		}

	 
	 public void deleteArticuloById(int id) {
		    if (!art.existsById(id)) {
		        throw new RuntimeException("Artículo no encontrado con id: " + id);
		    }
		    art.deleteById(id);
		}



	
}
