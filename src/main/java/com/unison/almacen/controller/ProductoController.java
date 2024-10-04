package com.unison.almacen.controller;

import com.unison.almacen.exception.NotFoundException;
import com.unison.almacen.model.Producto;
import com.unison.almacen.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
@Controller
@RequestMapping("/productos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/*")
    public String productos(){
        return "productos";
    }

    @GetMapping("/listar")
    public List<Producto> listar(){ return productoRepository.findAll().stream().toList();}

    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Producto productoDetalles){
        Producto p = new Producto();
        p.setNombre(productoDetalles.getNombre());
        p.setPrecio(productoDetalles.getPrecio());

        productoRepository.save(p);

        return ResponseEntity.ok(p);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificar(@PathVariable long id, @RequestBody Producto productoDetalles){
        Producto producto = productoRepository.findById(id).orElseThrow(NotFoundException::new);
        producto.setNombre(productoDetalles.getNombre());
        producto.setPrecio(productoDetalles.getPrecio());
        Producto productoActualizado = productoRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        productoRepository.deleteById(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }
}
