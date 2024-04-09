package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelVacante implements CRUD {
    @Override
    public Object insert(Object obj) {

        // 1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        // 2.Convertir el objeto
        Vacante objVacante = (Vacante) obj;

        try {

            // 3. Escribir el sql
            String sql = "INSERT INTO vacante (empresa_id, titulo, descripcion, duracion, estado) \n" +
                    "VALUES (?, ?, ?, ?, ?);";

            // 4. PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los valores a ????
            objPrepare.setInt(1, objVacante.getEmpresa_id());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getDescripcion());
            objPrepare.setString(4, objVacante.getDuracion());
            objPrepare.setString(5, objVacante.getEstado());

            // 6. Ejecutar el Query
            objPrepare.execute();

            // 7. Obtener el resultado con los id (Llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objRest.next()) {
                objVacante.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Vacante añadida correctamente.");

        } catch (SQLException e) {
            System.out.println("ERROR >>" + e.getMessage());
        }

        // 9. Cerrar la conexión
        ConfigDB.closeConnection();

        return objVacante;
    }

    @Override
    public List<Object> findAll() {

        // 1. Crear lista pata guardar lo que nos devuelve la base de datos
        List<Object> listVacantes = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {

            // 3. Escribir el Query en sql
            String sql = "SELECT * FROM vacante \n" +
                    "INNER JOIN empresa ON empresa.id = vacante.empresa_id;";

            // 4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {

                // 6.1 Crear una nueva instancia de empresa
                Vacante objVacante = new Vacante();
                Empresa objEMpresa = new Empresa();

                // 6.2 Llenar el objeto con la información de la bd
                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objEMpresa.setId(objResult.getInt("empresa.id"));
                objEMpresa.setNombre(objResult.getString("empresa.nombre"));
                objEMpresa.setSector(objResult.getString("empresa.sector"));
                objEMpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEMpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEMpresa);
                // 6.3 Agregar la empresa a la lista

                listVacantes.add(objVacante);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR >>" + e.getMessage());
            System.out.println("ERROR >>" + e.getMessage());
        }

        // 7. Cerrar la conexion
        ConfigDB.closeConnection();
        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {

        // 1. Convertir el objeto
        Vacante objVacante = (Vacante) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isUpdate = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = "UPDATE vacante SET empresa_id = ?, titulo = ?, tecnologia = ?, \n" +
                    "descripcion= ?, duracion = ?, estado = ? WHERE = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objVacante.getEmpresa_id());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getTecnologia());
            objPrepare.setString(4, objVacante.getDescripcion());
            objPrepare.setString(5, objVacante.getDuracion());
            objPrepare.setString(6, objVacante.getEstado());
            objPrepare.setInt(7, objVacante.getId());


            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();
            //int estado = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Vacante actualizada correctamente.");
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
        Vacante objVacante = (Vacante) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear una variable de estado
        boolean isDeleted = false;

        try {

            // 4. Escribir la sentencia SQL
            String sql = " DELETE FROM vacante WHERE id = ?;";

            // 5. Creamos el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor a ?
            objPrepare.setInt(1, objVacante.getId());

            // 7. Ejecutamos el Query
            int totalAffectedRows = objPrepare.executeUpdate();

            // Si las filas afectadas fueron mayor a cero quiere decir que si elimino algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Vacante eliminada correctamente");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >> " + e.getMessage());
        }

        // 8. Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public List<Object> findByName(String titulo) {

        //Creamos la lista
        List<Object> listVacante = new ArrayList<>();

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Sentencia SQL
            String slq = "SELECT * FROM vacante WHERE titulo LIKE ?;";

            //Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1, "%" + titulo + "%");

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
