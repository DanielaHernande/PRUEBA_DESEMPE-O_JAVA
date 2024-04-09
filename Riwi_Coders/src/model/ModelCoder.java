package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelCoder implements CRUD {

    @Override
    public Object insert(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2.Convertir el objeto
        Coder objCoder = (Coder) obj;

        try {

            // 3. Escribir el sql
            String sql = "INSERT INTO coder (nombre, apellidos, documento, cohorte, clan,cv) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            // 4. PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los valores a ????
            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getClan());
            objPrepare.setString(6, objCoder.getCv());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {
                objCoder.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Coder añadido correctamente.");


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>" + e.getMessage());
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 9. Cerrar la conexión
        ConfigDB.closeConnection();

        return objCoder;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listCoder = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM coder;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear una nueva instancia de empresa
                Coder objCoder = new Coder();

                // 6.2 Llenar el objeto con la información de la bd
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setCv(objResult.getString("cv"));


                // 6.3 Agregar la empresa a la lista
                listCoder.add(objCoder);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>" + e.getMessage());
            System.out.println("ERROR >>" + e.getMessage());
        }

        // 7. Cerrar la conexion
        ConfigDB.closeConnection();
        return listCoder;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Convertir el objeto
        Coder objCoder = (Coder) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isUpdate = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "UPDATE coder SET nombre = ?, apellidos = ?, documento = ?,\n" +
                    "cohorte = ?, clan = ?, cv = ? WHERE id = ?;  ";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getClan());
            objPrepare.setString(6, objCoder.getCv());
            objPrepare.setInt(7, objCoder.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Coder actualizada correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >>" + e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto
        Coder objCoder = (Coder) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = " DELETE FROM coder WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objCoder.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Coder eliminada correctamente");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public List<Object> findByCohorte(String cohorte) {

        //Creamos la lista
        List<Object> listVacante = new ArrayList<>();

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia SQL
            String slq = "SELECT *\n" +
                    "FROM coder\n" +
                    "WHERE coder.cohorte LIKE \"%1%\";";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1, "%" + cohorte + "%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));

                listVacante.add(objVacante);
            }

        } catch (Exception e) {
            System.out.println("ERROR >>" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacante;
    }

    public List<Object> findByTecnologia(String tecnologia) {

        //Creamos la lista
        List<Object> listVacante = new ArrayList<>();

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia SQL
            String slq = "SELECT *\n" +
                    "FROM vacante\n" +
                    "WHERE vacante.tecnologia LIKE ?;";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1, "%" + tecnologia + "%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));

                listVacante.add(objVacante);
            }

        } catch (Exception e) {
            System.out.println("ERROR >>" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacante;
    }
}
