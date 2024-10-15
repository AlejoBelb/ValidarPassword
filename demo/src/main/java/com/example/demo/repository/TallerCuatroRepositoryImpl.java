package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class TallerCuatroRepositoryImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public TallerCuatroRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean validarContrasena(String usuario, String contrasena) {
        return jdbcTemplate.execute((ConnectionCallback<Boolean>) connection -> {
            try (CallableStatement callableStatement = connection.prepareCall("{? = call Validar_Contrasena(?, ?)}")) {
                // Registrar el primer parámetro como tipo NUMBER
                callableStatement.registerOutParameter(1, Types.NUMERIC);
                // Configurar los parámetros de entrada
                callableStatement.setString(2, usuario);
                callableStatement.setString(3, contrasena);
                // Ejecutar la función
                callableStatement.execute();
                // Obtener el resultado de la función
                int resultado = callableStatement.getInt(1);
                // Convertir el resultado numérico en booleano (1 = true, 0 = false)
                return resultado == 1;
            }
        });
    }

    public List<Map<String, Object>> obtenerTallerCuatro() {
        String sql = "SELECT id_taller_cuatro, usuario, contrasena FROM Taller_Cuatro";
        return jdbcTemplate.queryForList(sql);
    }
}

