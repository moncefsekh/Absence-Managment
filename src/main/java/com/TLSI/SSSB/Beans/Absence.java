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
 * @author Islam
 */
@XmlRootElement(name = "absence")
public class Absence {
    
    private String date_absence;
    private String heure_absence;
    private String code_midule;
    private String type_seance;
    private Integer nbr_absence;
    private Integer nbr_absence_just;
    private boolean statu_justifier;
    private String num_carte ;
    private String idSeance;
    private String codeSpecialite;
    private String nom ;
    private String prenom;
    private String anneeSpecilaite;
@XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
@XmlElement
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
@XmlElement
    public String getAnneeSpecilaite() {
        return anneeSpecilaite;
    }

    public void setAnneeSpecilaite(String anneeSpecilaite) {
        this.anneeSpecilaite = anneeSpecilaite;
    }
    
    
    
@XmlElement
    public String getCodeSpecialite() {
        return codeSpecialite;
    }

    public void setCodeSpecialite(String codeSpecialite) {
        this.codeSpecialite = codeSpecialite;
    }
    
    
@XmlElement
    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }
@XmlElement
    public String getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(String idSeance) {
        this.idSeance = idSeance;
    }
    
@XmlElement
    public Integer getNbr_absence_just() {
        return nbr_absence_just;
    }

    public void setNbr_absence_just(Integer nbr_absence_just) {
        this.nbr_absence_just = nbr_absence_just;
    }

    
    @XmlElement
    public String getHeure_absence() {
        return heure_absence;
    }

    public void setHeure_absence(String heure_absence) {
        this.heure_absence = heure_absence;
    }
    
    

        @XmlElement
    public String getDate_absence() {
        return date_absence;
    }

    public void setDate_absence(String date_absence) {
        this.date_absence = date_absence;
    }
    @XmlElement
    public String getCode_midule() {
        return code_midule;
    }

    public void setCode_midule(String code_midule) {
        this.code_midule = code_midule;
    }
    @XmlElement
    public String getType_seance() {
        return type_seance;
    }

    public void setType_seance(String type_seance) {
        this.type_seance = type_seance;
    }
    @XmlElement
    public Integer getNbr_absence() {
        return nbr_absence;
    }

    public void setNbr_absence(Integer nbr_absence) {
        this.nbr_absence = nbr_absence;
    }
    @XmlElement
    public boolean isStatu_justifier() {
        return statu_justifier;
    }

    public void setStatu_justifier(boolean statu_justifier) {
        this.statu_justifier = statu_justifier;
    }
    
    
            


}