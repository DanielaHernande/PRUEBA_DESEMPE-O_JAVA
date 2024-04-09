package controller;

import entity.Coder;
import entity.Empresa;
import model.ModelCoder;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CoderController {

    public static void insert() {

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del coder:");
        String apellidos = JOptionPane.showInputDialog("Ingresa el apellido del coder:");
        String documento = JOptionPane.showInputDialog("Ingresa el documento del coder:");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cohorte del coder:"));
        String clan = JOptionPane.showInputDialog("Ingresa el clan del coder:");
        String cv = JOptionPane.showInputDialog("Ingresa el cv del cohoder:");

        instanceModel().insert(new Coder(nombre, apellidos, documento, cohorte, clan, cv));
    }

    public static void getAll() {

        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {

        String listString = "Lista de Coder";

        for (Object temp : list) {

            Coder objCoder = (Coder) temp;
            listString += objCoder.toString() + "\n";
        }

        return listString;
    }

    public static void update() {

        Object[] options = Utils.listToArray(CoderController.instanceModel().findAll());

        Coder objCoder = (Coder) JOptionPane.showInputDialog(null,
                "Selccione el coder que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del  coder:", objCoder.getNombre());
        String apellidos = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido del coder:", objCoder.getApellidos());
        String documento = JOptionPane.showInputDialog(null, "Ingrese el nuevo documento del coder", objCoder.getDocumento());
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cohorte del coder:", objCoder.getCohorte()));
        String clan = JOptionPane.showInputDialog(null, "Ingrese el nuevo clan del coder", objCoder.getClan());
        String cv = JOptionPane.showInputDialog(null, "Ingrese el nuevo cv del coder", objCoder.getCv());

        instanceModel().update(new Coder(nombre, apellidos, documento, cohorte, clan, cv));
    }

    public static void delete() {

        Object[] options = Utils.listToArray(CoderController.instanceModel().findAll());

        Coder objCoder = (Coder) JOptionPane.showInputDialog(null,
                "Seleccione el coder que desea eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objCoder);

    }

    public static void getAllCohorte() {

        String cohorte = JOptionPane.showInputDialog("Ingrese un coder por cohorte");

        String list = getAll(instanceModel().findByCohorte(Integer.parseInt(cohorte)));

        JOptionPane.showMessageDialog(null, list);
    }

    public static void getAllClan() {

        String clan = JOptionPane.showInputDialog("Ingrese una coder por clan ");

        String list = getAll(instanceModel().findByclan(clan));

        JOptionPane.showMessageDialog(null, list);
    }

    public static ModelCoder instanceModel() {
        return new ModelCoder();
    }



}
