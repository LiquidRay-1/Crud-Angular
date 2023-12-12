package com.agenda.agenda.service;

import com.agenda.agenda.model.Contacto;
import com.agenda.agenda.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ContactoService{

    @Autowired
    private ContactoRepository contactoRepository;


    // GUARDAR CONTACTOS
    public ResponseEntity<Map<String,Object>>  gurdarContacto(Contacto contacto){

        Map<String,Object> mapa = new HashMap<>();

        if(contacto.getNombre().equals("") || contacto.getApellido().equals("") || contacto.getTelefono() == null){
            mapa.put("error", 701);
            return new ResponseEntity<>(mapa, HttpStatus.BAD_REQUEST);
        }else{
            Contacto contactoGuardado = contactoRepository.save(contacto);
            mapa.put("success", contactoGuardado);
        }
        return new ResponseEntity<>(mapa, HttpStatus.CREATED);
    }

    // LISTA DE CONTACTOS
    public ResponseEntity<Map<String,Object>>  listaContactos(){

        Map<String, Object> mapa = new HashMap<>();
        List<Contacto> contactos = contactoRepository.findAll();

        if(contactos.isEmpty()){
            mapa.put("empty", 702);
        }else{
            mapa.put("success",contactos);
        }
        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }


    //ELIMINAR CONTACTOS
    public ResponseEntity<Map<String,Object>> eliminarContacto(Long id){

        Map<String,Object> mapa = new HashMap<>();

        if(id == null){
            return new ResponseEntity<>(mapa, HttpStatus.BAD_REQUEST);
        }else{
            Contacto contacto = contactoRepository.findById(id).orElse(null);

            if(contacto == null){
                mapa.put("NotFound", 703);
                return new ResponseEntity<>(mapa, HttpStatus.NOT_FOUND);
            }
            contactoRepository.deleteById(id);
            return new ResponseEntity<>(mapa, HttpStatus.OK);
        }
    }

    // BUSCAR CONTACTOS POR NOMBRE
    public ResponseEntity<Map<String,Object>> buscarPorNombre(String nombre){

        Map<String,Object> mapa= new HashMap<>();

        List<Contacto> contactos = contactoRepository.findByNombre(nombre);

        if(contactos.isEmpty()){
            mapa.put("NotFound", 703);
            return new ResponseEntity<>(mapa, HttpStatus.NOT_FOUND);
        }else{
            mapa.put("success",contactos);
        }
        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }

}
