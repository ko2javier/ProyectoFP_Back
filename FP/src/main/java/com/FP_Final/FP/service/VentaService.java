package com.FP_Final.FP.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.FP_Final.FP.model.Articulos;
import com.FP_Final.FP.model.VentaDTO;
import com.FP_Final.FP.model.Ventas;
import com.FP_Final.FP.repository.ArticulosRepository;
import com.FP_Final.FP.repository.VentasRepository;
import org.springframework.security.oauth2.jwt.Jwt;

@Service
public class VentaService {
	
	@Autowired
    private VentasRepository ventas_repository;
	@Autowired
    private ArticulosRepository articulosRepository;
	
	
	public String obtenerUsuarioAutenticado() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof Jwt) {
	        Jwt jwt = (Jwt) principal;
	        // "sub" es el claim por defecto para el 'subject' del token,
	        // pero podría ser "preferred_username" u otro claim, según tu configuración:
	        String username = jwt.getClaim("sub");
	        System.out.println("Usuario autenticado desde JWT: " + username);
	        return username;
	    } else {
	        // fallback, por si en algún contexto tenemos un principal distinto
	        return principal.toString();
	    }
	}

	

	public List<Ventas> getAll() {
		return ventas_repository.findAll();
	}
	
	public List<Ventas> getAll_username(String id_username) {
		return ventas_repository.findByusername(id_username);
	}
	
	//Registrar venta
	public Ventas registrarVenta(VentaDTO ventaDTO) {
	    String username = obtenerUsuarioAutenticado(); //Usuario autenticado
	    System.out.println("Registrando venta para usuario: " + username);
	   // Articulos articulo = articulosRepository.findByCodigo(ventaDTO.getCodigo());
	    
	    Ventas nuevaVenta = new Ventas(
	        LocalDate.now(), LocalTime.now(), username,
	        ventaDTO.getDnicliente(), ventaDTO.getNameproducto(),
	        ventaDTO.getCantidad(), ventaDTO.getImporte(), ventaDTO.getCodigo()
	    );

	    return ventas_repository.save(nuevaVenta);
	}
	public List<Ventas> registrarVentas(List<VentaDTO> ventasDTO) {
        String username = obtenerUsuarioAutenticado(); //Usuario autenticado
        List<Ventas> nuevasVentas = new ArrayList<>();

        for (VentaDTO dto : ventasDTO) {
        	/*
        	Articulos articulo = articulosRepository.findByCodigo(dto.getCodigo());
        	if(articulo == null) {
				System.out.println("No se ha encontrado el articulo con codigo: " + dto.getCodigo());
				continue;
			}else {
				System.out.println("Se ha encontrado el articulo con codigo: " + dto.getCodigo());
			}*/
        	
        	// Guardar en la DB
        	/*
        	 * creo la entidad Ventas y la guardo en la DB */
        	
        	nuevasVentas.add(ventas_repository.save(
				new Ventas(
					LocalDate.now(),
					LocalTime.now(),
					username,            // id_username
					dto.getDnicliente(),
					dto.getNameproducto(),
					dto.getCantidad(),
					dto.getImporte(),dto.getCodigo()
					
				)));
        	
        	/*
            // Crear la entidad Ventas
            Ventas venta = new Ventas(
                LocalDate.now(),
                LocalTime.now(),
                username,            // id_username
                dto.getDnicliente(),
                dto.getNameproducto(),
                dto.getCantidad(),
                dto.getImporte()
				,articulo
            );
            */   
            
        }

        return nuevasVentas;
    }


}
