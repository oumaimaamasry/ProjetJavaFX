package com.emsi.javafx.dao;

import com.emsi.javafx.entitites.Compte;

import java.util.List;

public interface daoCompte {
    void insert (Compte compte);
    void update (Compte compte);
    void deleteById(Integer id);
    Compte findById(Integer id);
    List<Compte> getAllCompte();
    List<Compte> importFromExcel(String filePath);
   // void exportToExcel(List<Compte> comptes, String filePath);

}
