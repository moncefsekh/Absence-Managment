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
@XmlRootElement(name = "seance")
public class Seance {
    
    private Integer id_Seance;
    private String annee_specialite;
    private String type;
    private String date_seance ;
    private String heure_seance;
    private String date_changement;
    private String heure_changement;
    private String code_module;
    private String code_section;
    private String nom_groupe;
    private String code_specialite;
    private Integer id_Dates;
    private String nomModule;
    private String nomEnseignant;
    private String prenomEnseignant;
    
    @XmlElement
    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }
    @XmlElement
    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }
    @XmlElement
    public String getPrenomEnseignant() {
        return prenomEnseignant;
    }

    public void setPrenomEnseignant(String prenomEnseignant) {
        this.prenomEnseignant = prenomEnseignant;
    }
    
    
    @XmlElement
    public Integer getId_Dates() {
        return id_Dates;
    }

    public void setId_Dates(Integer id_Dates) {
        this.id_Dates = id_Dates;
    }
    
    
    @XmlElement
    public String getDate_changement() {
        return date_changement;
    }

    public void setDate_changement(String date_changement) {
        this.date_changement = date_changement;
    }
    @XmlElement
    public String getHeure_changement() {
        return heure_changement;
    }

    public void setHeure_changement(String heure_changement) {
        this.heure_changement = heure_changement;
    }
    
    
    
    @XmlElement
    public String getAnnee_specialite() {
        return annee_specialite;
    }

    public void setAnnee_specialite(String annee_specialite) {
        this.annee_specialite = annee_specialite;
    }
    
    
    
    @XmlElement
    public String getHeure_seance() {
        return heure_seance;
    }

    public void setHeure_seance(String heure_seance) {
        this.heure_seance = heure_seance;
    }
    
    
    

    @XmlElement
    public String getCode_specialite() {
        return code_specialite;
    }

    public void setCode_specialite(String code_specialite) {
        this.code_specialite = code_specialite;
    }
    

      @XmlElement
    public String getCode_section() {
        return code_section;
    }

    public void setCode_section(String code_section) {
        this.code_section = code_section;
    }
    
    
    

     @XmlElement
    public Integer getId_Seance() {
        return id_Seance;
    }

    public void setId_Seance(Integer id_Seance) {
        this.id_Seance = id_Seance;
    }

     @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

     @XmlElement
    public String getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }
     @XmlElement

    public String getcode_module() {
        return code_module;
    }

    public void setcode_module(String code_module) {
        this.code_module = code_module;
    }

     @XmlElement
    public String getNom_groupe() {
        return nom_groupe;
    }

    public void setNom_groupe(String nom_groupe) {
        this.nom_groupe = nom_groupe;
    }

   
    
    
    
}
