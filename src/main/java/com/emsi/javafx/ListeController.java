package com.emsi.javafx;

import com.emsi.javafx.entitites.Compte;
import com.emsi.javafx.service.CompteService;
import com.emsi.javafx.service.DataServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ListeController {
    @FXML
    public TableView<Compte> comptesTableView;

    public CompteService compteService = new CompteService();
    public static Compte tableComptes = null;


    public void initialize() {
        setupTableSelectionListener();
    }

    @FXML
    public void create() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form.fxml"));
       // Parent root = fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(600);
        stage.setMaxWidth(500);
        stage.setMaxHeight(600);
        stage.setTitle("compte Creation");
        stage.setOnHidden(event -> {
            read();
        });
        stage.show();
    }

    @FXML
    public void read() {
        comptesTableView.getItems().clear();
        ArrayList<Compte> comptes = (ArrayList<Compte>) compteService.getAllCompte();
        for (Compte c : comptes) {
            comptesTableView.getItems().add(c);
        }
    }

    @FXML
    public void update() throws IOException {
        Compte selectedCompte = comptesTableView.getSelectionModel().getSelectedItem();
        if (selectedCompte == null) {

            showErrorMessage("information", "Invalid Selection", "Please select a compte to update.");
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(600);
        stage.setMaxWidth(500);
        stage.setMaxHeight(600);
        stage.setTitle("compte Modification");
        stage.setOnHidden(event -> {
            read();
        });
        stage.show();

    }

    @FXML
    public void delete() {
        Compte selectedCompte= comptesTableView.getSelectionModel().getSelectedItem();

        if (selectedCompte == null) {

            showErrorMessage("information", "Invalid Selection", "Please select a compte to delete.");
            return;
        }
        compteService.remove(selectedCompte);
        System.out.println(" deleted successfully!");
        showErrorMessage("warning", "Valid input", " Are you to delete this account ");
        read();
    }
    @FXML
    public void importData() {
        DataServiceImpl dataServiceImpl = new DataServiceImpl();
        dataServiceImpl.importFromExcel("src/main/resources/Data/comptes.xlsx");
        System.out.println(" imported successfully!");
        showErrorMessage("information", "Valid input", "imported successfully!");
        read();
    }

    @FXML
    public void exportData() {
        DataServiceImpl dataServiceImpl = new DataServiceImpl();
        dataServiceImpl.exportToExcel( "src/main/resources/Data/outPutData.xlsx");
        System.out.println("exported successfully!");
        showErrorMessage("information", "Valid input", " exported successfully!");
        read();
    }

    public void setupTableSelectionListener() {
        comptesTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Compte>() {
            @Override
            public void changed(ObservableValue<? extends Compte> observable, Compte oldValue, Compte newValue) {
                if (newValue != null) {
                    tableComptes = newValue;
                    System.out.println(tableComptes);
                } else {
                    tableComptes = null;
                }
            }
        });
    }

    public void showErrorMessage(String type, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (type == "error") {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        else if (type == "information") {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        else if (type == "warning") {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        else if (type == "confirm") {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
