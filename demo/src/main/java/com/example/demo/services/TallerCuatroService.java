package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TallerCuatroRepositoryImpl;

import java.util.List;
import java.util.Map;

@Service
public class TallerCuatroService {

    @Autowired
    private TallerCuatroRepositoryImpl repository;

    public boolean validarContrasena(String usuario, String contrasena) {
    	try {
            return repository.validarContrasena(usuario, contrasena);
        } catch (Exception e) {
            // Registra el error y lanza una excepción personalizada
            e.printStackTrace();
            throw new RuntimeException("Error al validar la contraseña: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> obtenerTallerCuatro() {
        return repository.obtenerTallerCuatro();
    }
}
