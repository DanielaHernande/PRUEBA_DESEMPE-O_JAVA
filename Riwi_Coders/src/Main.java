import controller.CoderController;
import controller.EmpresaController;
import controller.VacanteControlador;
import database.ConfigDB;
import entity.Coder;
import model.ModelCoder;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ConfigDB.openConnection();

        String option = "", option1 = "", option2 = "", option3 = "", option4 = "";

        do {

            option = JOptionPane.showInputDialog("""
                                        
                    1. Opciones de la empresa.
                    2. Opciones de vacantes.
                    3. opcioines de Coder.
                    4. Opciones de las empresas.
                    5. Exit.
                                        
                    Choose an option:
                    """);

            switch (option) {

                case "1":

                    do {

                        option1 = JOptionPane.showInputDialog("""
                                                    
                                1. Ingresar una empresa.
                                2. Listar todas las empresas.
                                3. Actualizar una empresa.
                                4. Eliminar una empresa.
                                5. Exit.
                                                    
                                Choose an option:
                                """);


                        switch (option1) {

                            case "1":
                                EmpresaController.insert();
                                break;

                            case "2":
                                EmpresaController.getAll();
                                break;

                            case "3":
                                EmpresaController.update();
                                break;

                            case "4":
                                EmpresaController.delete();
                                break;
                        }

                    } while (option1.equals("5"));
                    break;

                case "2":

                    do {

                        option2 = JOptionPane.showInputDialog("""
                                                    
                                1. Ingresar una nueva vacante.
                                2. Buscar vacantes por titulo.
                                3. Buscar vacantes por tecnologia.
                                4. Actualizar una vacante.
                                4. Eliminar una vacante.
                                6. Exit.
                                                    
                                Choose an option:
                                """);


                        switch (option2) {

                            case "1":
                                VacanteControlador.insert();
                                break;

                            case "2":
                                VacanteControlador.getAllT();
                                break;

                            case "3":
                                VacanteControlador.getAllTecnologia();
                                break;

                            case "4":
                                VacanteControlador.update();
                                break;

                            case "5":
                                VacanteControlador.delete();
                                break;
                        }

                    } while (option2.equals("6"));
                    break;

                case "3":

                    do {

                        option3 = JOptionPane.showInputDialog("""
                                                    
                                1. Ingresar un nuevo Coder.
                                2. Buscar coder por cohorte.
                                3. Buscar coder por clan.
                                4. Buscar coder por tecnologia
                                5. Actualizar una coder.
                                6. Eliminar una coder.
                                7. Exit.
                                                    
                                Choose an option:
                                """);

                        switch (option3) {

                            case "1":
                                CoderController.insert();
                                break;

                            case "2":
                                CoderController.
                                break;

                            case "5":
                                CoderController.update();
                                break;

                            case "6":
                                CoderController.delete();
                                break;
                        }


                    } while (option3.equals("7"));
                    break;
            }

        } while (!option.equals("5"));
    }
}