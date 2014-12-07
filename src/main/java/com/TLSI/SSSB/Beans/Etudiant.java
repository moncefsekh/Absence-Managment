/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author SSSB
 */
@XmlRootElement(name = "etudiant")
//@XmlType(propOrder = {"id_Etudiant","Nom_Etudiant","Prenom_Etudiant","Date_naissance_Etudiant","Num_Carte","Date_Bac","Moy_Bac","sexe"})
public class Etudiant {
    private Integer id_Etudiant ;
    private String Nom_Etudiant;
    private String Prenom_Etudiant;
    private String Date_naissance_Etudiant;
    private String Num_Carte;
    private String Date_Bac;
    private Float Moy_Bac;
    private boolean sexe ;
    private String nomPrenomMere;
    private String prenomPere;
    private String adresse;
    private Integer id_Compte;
    private Integer nbrEtudiants;
    private String codeGroupe;
    private Integer nombreAbsences;
    
    @XmlElement
    public Integer getNbrEtudiants() {
        return nbrEtudiants;
    }

    public void setNbrEtudiants(Integer nbrEtudiants) {
        this.nbrEtudiants = nbrEtudiants;
    }
    
    
    
    @XmlElement
    public String getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(String codeGroupe) {
        this.codeGroupe = codeGroupe;
    }
    @XmlElement
    public Integer getNombreAbsences() {
        return nombreAbsences;
    }

    public void setNombreAbsences(Integer nombreAbsences) {
        this.nombreAbsences = nombreAbsences;
    }
    

    @XmlElement
    public String getNomPrenomMere() {
        return nomPrenomMere;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }
    @XmlElement
    public String getPrenomPere() {
        return prenomPere;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }
    @XmlElement
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    

    @XmlElement
    public Integer getId_Compte() {
        return id_Compte;
    }

    public void setId_Compte(Integer id_Compte) {
        this.id_Compte = id_Compte;
    }
    @XmlElement
    public Integer getIdEtudiant() {
        return id_Etudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.id_Etudiant = idEtudiant;
    }
    @XmlElement
    public String getNom_Etudiant() {
        return Nom_Etudiant;
    }

    public void setNom_Etudiant(String Nom_Etudiant) {
        this.Nom_Etudiant = Nom_Etudiant;
    }
    @XmlElement
    public String getPrenom_Etudiant() {
        return Prenom_Etudiant;
    }

    public void setPrenom_Etudiant(String Prenom_Etudiant) {
        this.Prenom_Etudiant = Prenom_Etudiant;
    }
    @XmlElement
    public String getDate_naissance_Etudiant() {
        return Date_naissance_Etudiant;
    }

    public void setDate_naissance_Etudiant(String Date_naissance_Etudiant) {
        this.Date_naissance_Etudiant = Date_naissance_Etudiant;
    }
    @XmlElement
    public String getNum_Carte() {
        return Num_Carte;
    }

    public void setNum_Carte(String Num_Carte) {
        this.Num_Carte = Num_Carte;
    }
    @XmlElement
    public String getDate_Bac() {
        return Date_Bac;
    }

    public void setDate_Bac(String Date_Bac) {
        this.Date_Bac = Date_Bac;
    }
    @XmlElement
    public Float getMoy_Bac() {
        return Moy_Bac;
    }

    public void setMoy_Bac(Float Moy_Bac) {
        this.Moy_Bac = Moy_Bac;
    }
    @XmlElement
    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }
    
    
    
}
