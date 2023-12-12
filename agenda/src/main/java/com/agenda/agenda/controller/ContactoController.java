package com.agenda.agenda.controller;

import com.agenda.agenda.model.Contacto;
import com.agenda.agenda.service.ContactoService;
import com.agenda.agenda.service.UpdateService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @Autowired
    private UpdateService updateService;


    // GUARDAR CONTACTO
    @PostMapping
    public ResponseEntity<Map<String,Object>>  save(@RequestBody Contacto contacto) {
        ResponseEntity<Map<String, Object>> response  =  contactoService.gurdarContacto(contacto);
        return response;
    }

    //LISTAR CONTACTOS
    @GetMapping
    public ResponseEntity<Map<String,Object>> list(){
        ResponseEntity<Map<String,Object>> response = contactoService.listaContactos();
        return response;
    }
   //ELIMINAR CONTACTO
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id){
        Map<String,Object> mapa = new HashMap<>();
        ResponseEntity<Map<String,Object>> response = contactoService.eliminarContacto(id);
        return response;
    }


    // BUSCAR CONTACTO POR NOMBRE
    @GetMapping("/{nombre}")
    public ResponseEntity<Map<String,Object>> show(@PathVariable String nombre){
        Map<String,Object> mapa = new HashMap<>();
        ResponseEntity<Map<String,Object>> response = contactoService.buscarPorNombre(nombre);
        return response;
    }

    //MODIFICAR CONTACTO
    @PutMapping("{id}")
    public ResponseEntity<Map<String,Object>>  update(@PathVariable Long id, @RequestBody Contacto contacto) {
        ResponseEntity<Map<String, Object>> response  =  updateService.modificarContacto(contacto,id);
        return response;
    }



}
