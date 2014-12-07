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
public class Detail_Absence {
    private String Nom ;
    private String Prenom;
    private String Num_carte;
    private String date_naissance;
    private String nom_midule;
    private String type_seance;
    private int nbr_absence;
    private int nbr_justifiee;
    
    
    @XmlElement
    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    @XmlElement
    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    @XmlElement
    public String getNum_carte() {
        return Num_carte;
    }

    public void setNum_carte(String Num_carte) {
        this.Num_carte = Num_carte;
    }
    @XmlElement
    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }
    @XmlElement
    public String getNom_midule() {
        return nom_midule;
    }

    public void setNom_midule(String nom_midule) {
        this.nom_midule = nom_midule;
    }
    @XmlElement
    public String getType_seance() {
        return type_seance;
    }

    public void setType_seance(String type_seance) {
        this.type_seance = type_seance;
    }
    @XmlElement
    public int getNbr_absence() {
        return nbr_absence;
    }

    public void setNbr_absence(int nbr_absence) {
        this.nbr_absence = nbr_absence;
    }
    @XmlElement
    public int getNbr_justifiee() {
        return nbr_justifiee;
    }

    public void setNbr_justifiee(int nbr_justifiee) {
        this.nbr_justifiee = nbr_justifiee;
    }
    
    
    
    
}
