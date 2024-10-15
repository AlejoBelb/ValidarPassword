-- Función para validar contraseña
CREATE OR REPLACE FUNCTION Validar_Contrasena(
  p_usuario IN VARCHAR2,
  p_contrasena IN VARCHAR2
) RETURN NUMBER
IS
BEGIN
  -- Validar la longitud de la contraseña
  IF NOT (LENGTH(p_contrasena) BETWEEN 13 AND 20) THEN
    INSERT INTO Taller_Cuatro_Log (id_log, usuario, motivo)
    VALUES (Taller_Cuatro_SEQ.NEXTVAL, p_usuario, 'Longitud no válida');
    RETURN 0;
  END IF;

  -- Validar que contenga al menos una minúscula
  IF NOT REGEXP_LIKE(p_contrasena, '[a-z]') THEN
    INSERT INTO Taller_Cuatro_Log (id_log, usuario, motivo)
    VALUES (Taller_Cuatro_SEQ.NEXTVAL, p_usuario, 'No contiene minúscula');
    RETURN 0;
  END IF;

  -- Validar que contenga al menos una mayúscula
  IF NOT REGEXP_LIKE(p_contrasena, '[A-Z]') THEN
    INSERT INTO Taller_Cuatro_Log (id_log, usuario, motivo)
    VALUES (Taller_Cuatro_SEQ.NEXTVAL, p_usuario, 'No contiene mayúscula');
    RETURN 0;
  END IF;

  -- Validar que contenga al menos un número
  IF NOT REGEXP_LIKE(p_contrasena, '[0-9]') THEN
    INSERT INTO Taller_Cuatro_Log (id_log, usuario, motivo)
    VALUES (Taller_Cuatro_SEQ.NEXTVAL, p_usuario, 'No contiene número');
    RETURN 0;
  END IF;

  -- Validar que contenga al menos un carácter especial
  IF NOT REGEXP_LIKE(p_contrasena, '[^a-zA-Z0-9]') THEN
    INSERT INTO Taller_Cuatro_Log (id_log, usuario, motivo)
    VALUES (Taller_Cuatro_SEQ.NEXTVAL, p_usuario, 'No contiene carácter especial');
    RETURN 0;
  END IF;

  -- Si la contraseña es válida, insertar en la tabla y retornar 1 (true)
  INSERT INTO Taller_Cuatro (id_taller_cuatro, usuario, contrasena)
  VALUES (Taller_Cuatro_SEQ.NEXTVAL, p_usuario, p_contrasena);

  RETURN 1;

EXCEPTION
  WHEN OTHERS THEN
    -- Manejo de errores genérico
    RAISE_APPLICATION_ERROR(-20001, 'Ocurrió un error al validar la contraseña: ' || SQLERRM);
    RETURN 0; -- Retornar 0 en caso de error
END Validar_Contrasena;
/

-- tabla de logs
CREATE TABLE Taller_Cuatro_Log (
  id_log NUMBER PRIMARY KEY,
  usuario VARCHAR2(50),
  motivo VARCHAR2(100),
  fecha DATE DEFAULT SYSDATE
);

-- Listar todas las contraseñas
CREATE OR REPLACE PROCEDURE Obtener_Taller_Cuatro
IS
BEGIN
  FOR rec IN (SELECT * FROM Taller_Cuatro) LOOP
    DBMS_OUTPUT.PUT_LINE('ID: ' || rec.id_taller_cuatro || 
                         ', Usuario: ' || rec.usuario || 
                         ', Contraseña: ' || rec.contrasena);
  END LOOP;
END Obtener_Taller_Cuatro;
/
