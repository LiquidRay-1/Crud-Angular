package com.agenda.agenda.service;

import com.agenda.agenda.model.Contacto;
import com.agenda.agenda.repository.ContactoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateService {

    @Autowired
    private ContactoRepository contactoRepository;


    public ResponseEntity<Map<String,Object>> modificarContacto(Contacto contacto, Long id){

        Map<String,Object> mapa = new HashMap<>();

        if(contacto.getNombre().equals("") || contacto.getApellido().equals("") || contacto.getTelefono() == null){
            mapa.put("error", 701);
            return new ResponseEntity<>(mapa, HttpStatus.BAD_REQUEST);
        }else{
            Contacto newContact = contactoRepository.findById(id).orElse(null);

            if(contacto == null){
                mapa.put("NotFound", 703);
                return new ResponseEntity<>(mapa, HttpStatus.NOT_FOUND);
            }
            newContact.setNombre(contacto.getNombre());
            newContact.setApellido(contacto.getApellido());

            contactoRepository.save(newContact);

            mapa.put("success", newContact);
        }
        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }
}
