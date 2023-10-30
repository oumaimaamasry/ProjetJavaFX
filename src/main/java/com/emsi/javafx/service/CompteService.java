package com.emsi.javafx.service;

import com.emsi.javafx.dao.implem.CompteImplem;
import com.emsi.javafx.entitites.Compte;

import java.util.List;

public class CompteService {
    private CompteImplem compteImplem = new CompteImplem();

    public CompteService() {
    }

    public List<Compte> getAllCompte() {
        return this.compteImplem.getAllCompte();
    }

    public void update(Compte compte) {
            this.compteImplem.update(compte);
    }
    public void save(Compte compte) {
            this.compteImplem.insert(compte);
    }
    public void remove(Compte compte) {
        this.compteImplem.deleteById(compte.getCode());
    }
    public Compte getCompteById(Compte compte){ this.compteImplem.findById(compte.getCode());
        return compte;
    }
    public List<Compte> importFromExcel(){
        return this.compteImplem.importFromExcel("src/main/resources/comptes.xlsx");
    }
}
