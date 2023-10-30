package com.emsi.javafx.entitites;

public class Compte {
    private int code;
    private String titulaire;
    private String dateCreation;
    private String typeCompte;

    public Compte(){}

    public Compte(String titulaire, String dateCreation, String typeCompte) {
        this.titulaire = titulaire;
        this.dateCreation = dateCreation;
        this.typeCompte = typeCompte;
    }

    public Compte(int code, String titulaire, String dateCreation, String typeCompte) {
        this.code = code;
        this.titulaire = titulaire;
        this.dateCreation = dateCreation;
        this.typeCompte = typeCompte;
    }

    public int getCode() {


        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "code=" + code +
                ", titulaire='" + titulaire + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", typeCompte='" + typeCompte + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return code == compte.code && titulaire.equals(compte.titulaire) && dateCreation.equals(compte.dateCreation) && typeCompte.equals(compte.typeCompte);
    }


}
