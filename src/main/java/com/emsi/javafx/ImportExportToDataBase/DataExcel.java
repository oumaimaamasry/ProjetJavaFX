package com.emsi.javafx.ImportExportToDataBase;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.emsi.javafx.entitites.Compte;
import com.emsi.javafx.service.CompteService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DataExcel {
    private CompteService compteService = new CompteService();

    public void importDataFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            ArrayList<Compte> cptList = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                Compte cpt = new Compte();
                Cell codeCell = row.getCell(0);
                Cell titulaireCell = row.getCell(1);
                Cell dateCreationCell = row.getCell(2);
                Cell typeCompteCell = row.getCell(3);


                cpt.setCode((int) codeCell.getNumericCellValue());
                cpt.setTitulaire(titulaireCell.getStringCellValue());
                cpt.setDateCreation(dateCreationCell.getStringCellValue());
                cpt.setTypeCompte(typeCompteCell.getStringCellValue());


                cptList.add(cpt);
            }

            System.out.println("Importing Data From Excel is done!\nWaiting for saving to database...");
            for (Compte c : cptList) {
                compteService.save(c);
            }
            System.out.println("Saving From Excel To Database is done!");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToExcel(String filePath) {
        ArrayList<Compte> comptes = (ArrayList<Compte>) compteService.getAllCompte();

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("compte Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("code");
            headerRow.createCell(1).setCellValue("titulaire");
            headerRow.createCell(2).setCellValue("dateCreation");
            headerRow.createCell(3).setCellValue("typeCompte");

            int rowIndex = 1;

            for (Compte c : comptes) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(c.getCode());
                dataRow.createCell(1).setCellValue(c.getTitulaire());
                dataRow.createCell(2).setCellValue(c.getDateCreation());
                dataRow.createCell(3).setCellValue(c.getTypeCompte());
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            workbook.close();
            System.out.println("Exporting Data From Database To Excel is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

