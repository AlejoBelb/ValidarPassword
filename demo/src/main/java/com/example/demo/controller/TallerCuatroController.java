package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TallerCuatroDTO;
import com.example.demo.services.TallerCuatroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TallerCuatroController {

    @Autowired
    private TallerCuatroService service;

    @GetMapping("/validarContrasena")
    public ResponseEntity<?> validarContrasena(@RequestParam String usuario, @RequestParam String contrasena) {
        //return service.validarContrasena(usuario, contrasena);
    	try {
            boolean esValida = service.validarContrasena(usuario, contrasena);
            return ResponseEntity.ok(esValida);
        } catch (Exception e) {
            // Devuelve un mensaje de error específico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al validar la contraseña: " + e.getMessage());
        }
    }

    @GetMapping("/obtenerTallerCuatro")
    public ResponseEntity<List<TallerCuatroDTO>> obtenerTallerCuatro() {
        try {
            List<TallerCuatroDTO> result = service.obtenerTallerCuatro().stream().map(record -> {
                TallerCuatroDTO dto = new TallerCuatroDTO();
                dto.setIdTallerCuatro(((Number) record.get("id_taller_cuatro")).longValue());
                dto.setUsuario((String) record.get("usuario"));
                dto.setContrasena((String) record.get("contrasena"));
                return dto;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
