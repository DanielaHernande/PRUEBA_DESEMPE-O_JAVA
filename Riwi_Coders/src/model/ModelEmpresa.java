package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelEmpresa implements CRUD {

    @Override
    public Object insert(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2.Convertir el objeto
        Empresa objEmpresa = (Empresa) obj;

        try {

            // 3. Escribir el sql
            String sql = "INSERT INTO empresa (nombre, sector, ubicacion, contacto) " +
                    "VALUES (?, ?, ?, ?);";

            // 4. PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los valores a ????
            objPrepare.setString(1, objEmpresa.getNombre());
            objPrepare.setString(2, objEmpresa.getSector());
            objPrepare.setString(3, objEmpresa.getUbicacion());
            objPrepare.setString(4, objEmpresa.getContacto());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {
                objEmpresa.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Empresa añadida correctamente.");


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>" + e.getMessage());
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 9. Cerrar la conexión
        ConfigDB.closeConnection();

        return objEmpresa;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listEmpresas = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM empresa;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear una nueva instancia de empresa
                Empresa objEmpresa = new Empresa();

                // 6.2 Llenar el objeto con la información de la bd
                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                // 6.3 Agregar la empresa a la lista
                listEmpresas.add(objEmpresa);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>" + e.getMessage());
            System.out.println("ERROR >>" + e.getMessage());
        }

        // 7. Cerrar la conexion
        ConfigDB.closeConnection();
        return listEmpresas;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Convertir el objeto
        Empresa objEmpresa = (Empresa) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isUpdate = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "UPDATE empresa SET nombre = ?, sector = ?, \n" +
                    "ubicacion = ?, contacto = ? WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setString(1, objEmpresa.getNombre());
            objPrepare.setString(2, objEmpresa.getSector());
            objPrepare.setString(3, objEmpresa.getUbicacion());
            objPrepare.setString(4, objEmpresa.getContacto());
            objPrepare.setInt(5, objEmpresa.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Empresa actualizada correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("ERRO >>" + e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Empresa objEmpresa = (Empresa) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = " DELETE FROM empresa WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objEmpresa.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Empresa eliminada correctamente");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
