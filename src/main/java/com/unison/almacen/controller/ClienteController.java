package com.unison.almacen.controller;

import com.unison.almacen.exception.NotFoundException;
import com.unison.almacen.model.Cliente;
import com.unison.almacen.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/*")
    public String clientes(){
        return "clientes";
    }

    @GetMapping("/listar")
    public List<Cliente> listar(){ return clienteRepository.findAll().stream().toList();}

    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Cliente clienteDetalles){
        Cliente c = new Cliente();
        c.setNombreUsuario(clienteDetalles.getNombreUsuario());
        c.setPassword(clienteDetalles.getPassword());

        clienteRepository.save(c);

        return ResponseEntity.ok(c);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificar(@PathVariable long id, @RequestBody Cliente clienteDetalles){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(NotFoundException::new);
        cliente.setNombreUsuario(clienteDetalles.getNombreUsuario());
        cliente.setPassword(clienteDetalles.getPassword());
        Cliente productoActualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }
}
