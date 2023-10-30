package com.emsi.javafx.dao.implem;


import com.emsi.javafx.dao.daoCompte;
import com.emsi.javafx.entitites.Compte;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class CompteImplem implements daoCompte {
    private Connection conn = DB.getConnection();
    public void insert(Compte compte) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement("INSERT INTO Compte (code,titulaire,dateCreation,typeCompte) VALUES (?,?,?,?)", 1);
            ps.setInt(1, compte.getCode());
            ps.setString(2, compte.getTitulaire());
            ps.setString(3, compte.getDateCreation());
            ps.setString(4, compte.getTypeCompte());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    compte.setCode(id);
                }
                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException var9) {
            System.err.println("problème d'insertion d'un compte");
        } finally {
            DB.closeStatement(ps);
        }

    }

    public void update(Compte compte) {
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement("UPDATE Compte SET titulaire = ?,dateCreation= ?,typeCompte=? WHERE code = ?");
            ps.setString(1, compte.getTitulaire());
            ps.setString(2, compte.getDateCreation());
            ps.setString(3, compte.getTypeCompte());
            ps.setInt(4, compte.getCode());
            ps.executeUpdate();
        } catch (SQLException var7) {
            System.err.println("problème de mise à jour d'un compte");
        } finally {
            DB.closeStatement(ps);
        }

    }

    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement("DELETE FROM Compte WHERE code = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException var7) {
            System.err.println("problème de suppression d'un compte");
        } finally {
            DB.closeStatement(ps);
        }

    }

    public Compte findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.conn.prepareStatement("SELECT * FROM Compte WHERE code = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Compte compte = new Compte();
                compte.setCode(rs.getInt("code"));
                compte.setTitulaire(rs.getString("titulaire"));
                compte.setDateCreation(rs.getString("dateCreation"));
                compte.setTypeCompte(rs.getString("typeCompte"));
                Compte var6 = compte;
                return var6;
            }
        } catch (SQLException var9) {
            System.err.println("problème de requête pour trouver le compte");
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
        return null;
    }

    public List<Compte> getAllCompte() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.conn.prepareStatement("SELECT * FROM Compte");
            rs = ps.executeQuery();
            List<Compte> listCompte = new ArrayList();

            while(rs.next()) {
                Compte compte = new Compte();
                compte.setCode(rs.getInt("code"));
                compte.setTitulaire(rs.getString("titulaire"));
                compte.setDateCreation(rs.getString("dateCreation"));
                compte.setTypeCompte(rs.getString("typeCompte"));
                listCompte.add(compte);
            }

            ArrayList var6 = (ArrayList) listCompte;
            return var6;
        } catch (SQLException var9) {
            System.err.println("problème de requête pour sélectionner un compte");
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
        return null;
    }

    @Override
    public List<Compte> importFromExcel(String filePath) {

        ArrayList<Compte> comptes = new ArrayList<>();

            try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
                Sheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    Compte compte = new Compte();
                    Cell numeroCell = row.getCell(0);
                    compte.setCode((int) numeroCell.getNumericCellValue());

                    Cell proprietaireCell = row.getCell(1);
                    compte.setTitulaire(proprietaireCell.getStringCellValue());

                    Cell dateCreationCell = row.getCell(1);
                    compte.setDateCreation(dateCreationCell.getStringCellValue());

                    Cell typeCompteCell = row.getCell(2);
                    compte.setTypeCompte(typeCompteCell.getStringCellValue());

                    comptes.add(compte);
                }
            } catch (IOException  e) {
                e.printStackTrace();
            }

            return comptes;
        }

 /*   @Override
    public void exportToExcel(List<Compte> comptes, String filePath) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Comptes Bancaires");

        int rowNum = 0;
        for (Compte compte : comptes) {
            Row row = sheet.createRow(rowNum++);

            Cell numeroCell = row.createCell(0);
            numeroCell.setCellValue(compte.getCode());

            Cell proprietaireCell = row.createCell(1);
            proprietaireCell.setCellValue(compte.getTitulaire());

            Cell dateCell = row.createCell(2);
            dateCell.setCellValue(compte.getDateCreation());
            Cell typeCompteCell = row.createCell(2);
            typeCompteCell.setCellValue(compte.getTypeCompte());
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
