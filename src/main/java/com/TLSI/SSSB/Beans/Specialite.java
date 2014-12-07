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
@XmlRootElement(name = "specialite")
public class Specialite {
    
    private Integer id_Specialite;
    private String Code_specialite;
    private String Nom_specialite;
    private String annee ;
    @XmlElement
    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    
    @XmlElement
    public Integer getId_Specialite() {
        return id_Specialite;
    }

    public void setId_Specialite(Integer id_Specialite) {
        this.id_Specialite = id_Specialite;
    }
    @XmlElement
    public String getCode_specialite() {
        return Code_specialite;
    }

    public void setCode_specialite(String Code_specialite) {
        this.Code_specialite = Code_specialite;
    }
    @XmlElement
    public String getNom_specialite() {
        return Nom_specialite;
    }

    public void setNom_specialite(String Nom_specialite) {
        this.Nom_specialite = Nom_specialite;
    }
    
    
    
}
