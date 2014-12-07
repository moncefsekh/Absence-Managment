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
@XmlRootElement(name = "groupe")
public class Groupe {
    
    private Integer id_Groupe;
    private String Code_groupe;
    private String Nom_groupe;
    private String annee;
    private Integer idSection;
    private Integer idSpecialite;
    @XmlElement
    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }
    @XmlElement
    public Integer getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Integer idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
    
    
    @XmlElement
    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    
    @XmlElement
    public Integer getId_Groupe() {
        return id_Groupe;
    }

    public void setId_Groupe(Integer id_Groupe) {
        this.id_Groupe = id_Groupe;
    }
    @XmlElement
    public String getCode_groupe() {
        return Code_groupe;
    }

    public void setCode_groupe(String Code_groupe) {
        this.Code_groupe = Code_groupe;
    }
    @XmlElement
    public String getNom_groupe() {
        return Nom_groupe;
    }

    public void setNom_groupe(String Nom_groupe) {
        this.Nom_groupe = Nom_groupe;
    }
    
    
    
}
