package model;

import database.CRUD;
import database.ConfigDB;
import entity.Contratacion;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelContratacion implements CRUD {
    @Override
    public Object insert(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2.Convertir el objeto
        Contratacion objContratacion = (Contratacion) obj;

        try {

            // Validacion


            // 3. Escribir el sql
            String sql = "INSERT INTO contratacion (vacante_id, coder_id, estado, salario) \n" +
                    "VALUES (?, ?, ?, ?);";

            // 4. PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los valores a ????
            objPrepare.setInt(1, objContratacion.getVacante_id());
            objPrepare.setInt(2, objContratacion.getCoder_id());
            objPrepare.setString(3, objContratacion.getEstado());
            objPrepare.setDouble(4, objContratacion.getSalario());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {
                objContratacion.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Contratacion añadida correctamente.");


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>" + e.getMessage());
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 9. Cerrar la conexión
        ConfigDB.closeConnection();

        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Contratacion objContratacion = (Contratacion) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = " DELETE FROM contratacion WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objContratacion.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Contratacion eliminada correctamente");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
