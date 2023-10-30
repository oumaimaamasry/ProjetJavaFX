package com.emsi.javafx.ImportExportToDataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.emsi.javafx.entitites.Compte;
import com.emsi.javafx.service.CompteService;

public class DataTxt {
    private CompteService compteService = new CompteService();

    public void importDataFromTextFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Compte> compteList = new ArrayList<>();
            String readLine = br.readLine();

            while (readLine != null) {
                String[] compteData = readLine.split("\\|");

                Compte cpt = new Compte();
                cpt.setCode(Integer.parseInt(compteData[0].trim()));
                cpt.setTitulaire(compteData[1].trim());
                cpt.setDateCreation(compteData[2].trim());
                cpt.setTypeCompte((compteData[3].trim()));

                compteList.add(cpt);
                readLine = br.readLine();
            }
            System.out.println("Importing Data From text is done!\nWaiting for saving to database...");
            for (Compte c : compteList) {
                compteService.save(c);
            }
            System.out.println("Saving From Text To Database is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToTextFile(String filePath) {
        ArrayList<Compte> comptes = (ArrayList<Compte>) compteService.getAllCompte();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Compte c : comptes) {
                String compteData = c.getCode() + " | " + c.getTitulaire() + " | " + c.getDateCreation() + " | " +
                        c.getTypeCompte() ;

                bw.write(compteData);
                bw.newLine();
            }

            System.out.println("Exporting Data From Database To Text is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
