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

@XmlRootElement(name = "enseignant")
public class Enseignant {
    
    private Integer idEnseignant;
    private String nom;
    private String prenom;
    private String grade;
    private String dateNaissance;
    private Integer idCompth;
    @XmlElement
    public Integer getIdCompth() {
        return idCompth;
    }

    public void setIdCompth(Integer idCompth) {
        this.idCompth = idCompth;
    }
    
    
    
    private Integer nbrModules;
    @XmlElement
    public Integer getNbrModules() {
        return nbrModules;
    }

    public void setNbrModules(Integer nbrModules) {
        this.nbrModules = nbrModules;
    }
    
    @XmlElement
    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }
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
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    @XmlElement
    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
   
    
    

   
    
}
