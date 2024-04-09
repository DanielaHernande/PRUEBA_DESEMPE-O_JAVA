package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;
import model.ModelContratacion;
import model.ModelEmpresa;
import utils.Utils;

import javax.swing.*;

public class ContratacionController {

/*    public static void insert() {


        Object[] optionsV = Utils.listToArray(VacanteControlador.instanceModel().findAll());
        Object[] optionsC = Utils.listToArray(CoderController.instanceModel().findAll());

        Vacante objVcante = (Vacante) JOptionPane.showInputDialog(null,
                "Selccione la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsV,
                optionsV[0]
        );


        Coder objCOder = (Coder) JOptionPane.showInputDialog(null,
                "Selccione la el coder",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsC,
                optionsC[0]
        );


        String estado = JOptionPane.showInputDialog("Ingrese el estado");
        Double salario = Double.valueOf(JOptionPane.showInputDialog("Ingrese el salario"));

        instanceModel().insert(new Contratacion(objVcante.getId(), objCOder.getId(), estado,salario, objVcante, objCOder));
    }*/

    public static ModelContratacion instanceModel() {
        return new ModelContratacion();
    }
}
