package controller;

import entity.Empresa;
import entity.Vacante;
import model.ModelVacante;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class VacanteControlador {

    public static void insert() {

        Object[] optionsEmpresas = Utils.listToArray(EmpresaController.instanceModel().findAll());

        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(null,
                "Seleccione una empresa:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsEmpresas,
                optionsEmpresas[0]);

        String titulo = JOptionPane.showInputDialog("Ingrese el titulo");
        String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia");
        String descripcion = JOptionPane.showInputDialog("Descripcion del titulo");
        String duracion = JOptionPane.showInputDialog("Duracion de la vacante");
        String estado = JOptionPane.showInputDialog("Estado de la vacante");

        instanceModel().insert(new Vacante(objEmpresa.getId(), titulo, tecnologia, descripcion, duracion, estado, objEmpresa));

    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {

        String listString = "Lista de vacantes";

        for (Object temp : list) {

            Vacante objVacante = (Vacante) temp;
            listString += objVacante.toString() + "\n";
        }

        return listString;
    }

    public static void update() {

        Object[] options = Utils.listToArray(VacanteControlador.instanceModel().findAll());
        Object[] optionsEmpresa = Utils.listToArray(EmpresaController.instanceModel().findAll());

        Vacante objVcante = (Vacante) JOptionPane.showInputDialog(null,
                "Selccione la vacante que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        Empresa objEMpresa = (Empresa) JOptionPane.showInputDialog(null,
                "Selccione la nueva empresa",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsEmpresa,
                optionsEmpresa[0]
        );

        String titulo = JOptionPane.showInputDialog(null, "Ingrese el nuevo titulo de la vacante:", objVcante.getTitulo());
        String tecnologia = JOptionPane.showInputDialog(null, "Ingrese la nueva tecnologia de la vacante:", objVcante.getTecnologia());
        String descripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la vacante:", objVcante.getDescripcion());
        String duracion = JOptionPane.showInputDialog(null, "Ingrese la duracion de la vacante", objVcante.getDuracion());
        String vacante = JOptionPane.showInputDialog(null, "Ingrese el estado de la vacante", objVcante.getEstado());

        instanceModel().update(new Vacante(objEMpresa.getId(), titulo, tecnologia, descripcion, duracion, vacante, objEMpresa));
    }

    public static void delete() {

        Object[] options = Utils.listToArray(VacanteControlador.instanceModel().findAll());

        Vacante objVcante = (Vacante) JOptionPane.showInputDialog(null,
                "Seleccione la vacante que desea eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objVcante);

    }

    public static void getAllT() {

        String titulo = JOptionPane.showInputDialog("Ingrese una vacante por titulo");

        String list = getAll(instanceModel().findByName(titulo));

        JOptionPane.showMessageDialog(null, list);
    }


    public static void getAllTecnologia() {

        String tecnologia = JOptionPane.showInputDialog("Ingrese una vacante por tecnologia ");

        String list = getAll(instanceModel().findByTecnologia(tecnologia));

        JOptionPane.showMessageDialog(null, list);
    }

    public static ModelVacante instanceModel() {
        return new ModelVacante();
    }

}
