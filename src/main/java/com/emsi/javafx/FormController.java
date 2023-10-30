package com.emsi.javafx;

import com.emsi.javafx.entitites.Compte;
import com.emsi.javafx.service.CompteService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FormController {
    @FXML
    public TextField titulaireTextField;
    @FXML
    public TextField codeTextField;
    @FXML
    public TextField dateTextField;
    @FXML
    public TextField typeTextField;

    @FXML
    public Button createUpdateButton;

    public CompteService compteService = new CompteService();

    public void initialize() {
        System.out.println("****************");
        System.out.println(ListeController.tableComptes);

        if (ListeController.tableComptes != null) {
            createUpdateButton.setText("Update");
            fillTable();
        } else {
            createUpdateButton.setText("Create");
        }
    }
    @FXML
    public void create() {
        if (ListeController.tableComptes != null) {
            update();
            return;
        }
        String titulaire = titulaireTextField.getText();
        String dateCreation = dateTextField.getText();
        String typeCompte = typeTextField.getText();

        Compte cptInsert = new Compte(titulaire, dateCreation, typeCompte);
        compteService.save(cptInsert);

        System.out.println(" inserted successfully!");

        Stage formStage = (Stage) titulaireTextField.getScene().getWindow();
        formStage.close();
    }

    @FXML
    public void fillTable() {
        Compte newValue = ListeController.tableComptes;
        titulaireTextField.setText(newValue.getTitulaire());
        dateTextField.setText(newValue.getDateCreation());
        typeTextField.setText(String.valueOf(newValue.getTypeCompte()));
    }

    @FXML
    public void update() {
        Compte selected = ListeController.tableComptes;
        if (selected == null) {
            System.out.println("Please select to update.");

            return;
        }
        System.out.println(selected);
        int code = selected.getCode();
        //int code1 = Integer.parseInt(codeTextField.getText());
        String titulaire = titulaireTextField.getText();
        String dateCreation = dateTextField.getText();
        String typeCompte = typeTextField.getText();


        Compte compteUpdate = new Compte(code,titulaire, dateCreation, typeCompte);
        compteService.update(compteUpdate);


        Stage formStage = (Stage) titulaireTextField.getScene().getWindow();
        formStage.close();
    }
    @FXML
    public void clear() {
        titulaireTextField.clear();
        dateTextField.clear();
        typeTextField.clear();
    }
}
