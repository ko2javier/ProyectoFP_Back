package com.FP_Final.FP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FP_Final.FP.model.Articulos;
import com.FP_Final.FP.service.ArticuloService;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Articulos>> searchArticulos(@PathVariable String keyword) {
        List<Articulos> articulos = articuloService.searchByKeyword(keyword);
        if (articulos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articulos);
    }
    
    @GetMapping("/All")
    public ResponseEntity<List<Articulos>> searchAll() {
        List<Articulos> articulos = articuloService.getAll();
        if (articulos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si no hay artículos
        }
        
        return ResponseEntity.ok(articulos);
    }
    
    
}
