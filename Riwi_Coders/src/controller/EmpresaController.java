package controller;

import entity.Empresa;
import model.ModelEmpresa;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class EmpresaController {

    public static void insert() {

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre de la empresa:");
        String sector = JOptionPane.showInputDialog("Ingresa el sector al cual pertenece la empresa:");
        String ubicacion = JOptionPane.showInputDialog("Ingresa la ubicacion de la empresa:");
        String contacto = JOptionPane.showInputDialog("Ingresa el contacto de la empresa:");

        instanceModel().insert(new Empresa(nombre, sector, ubicacion, contacto));
    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {

        String listString = "Lista de empresas";

        for (Object temp : list) {

            Empresa objEmpresa = (Empresa) temp;
            listString += objEmpresa.toString() + "\n";
        }

        return listString;
    }

    public static void update() {

        Object[] options = Utils.listToArray(EmpresaController.instanceModel().findAll());

        Empresa objEMpresa = (Empresa) JOptionPane.showInputDialog(null,
                "Selccione la empresa que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de la empresa:", objEMpresa.getNombre());
        String sector = JOptionPane.showInputDialog(null, "Ingrese el nuevo sector de la empresa:", objEMpresa.getSector());
        String ubicacion = JOptionPane.showInputDialog(null, "Ingrese la nueva ubicación de la empresa:", objEMpresa.getUbicacion());
        String contacto = JOptionPane.showInputDialog(null, "Ingrese el nuevo contacto de la empresa:", objEMpresa.getContacto());

        instanceModel().update(new Empresa(nombre, sector, ubicacion, contacto));
    }

    public static void delete() {

        Object[] options = Utils.listToArray(EmpresaController.instanceModel().findAll());

        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(null,
                "Seleccione la empresa que desa eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objEmpresa);

    }

    public static ModelEmpresa instanceModel() {
        return new ModelEmpresa();
    }

}
