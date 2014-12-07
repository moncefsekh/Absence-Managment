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
@XmlRootElement(name = "actualites")
public class Actualite {
    private String code_module;
    private String Type;
    private String date_seance;
    private String heure_seance;
    private String date_changement;
    private String heure_changement;
    private String etat_avancement;

    @XmlElement
    public String getHeure_changement() {
        return heure_changement;
    }

    public void setHeure_changement(String heure_changement) {
        this.heure_changement = heure_changement;
    }
    
    
@XmlElement
    public String getHeure_seance() {
        return heure_seance;
    }

    public void setHeure_seance(String heure_seance) {
        this.heure_seance = heure_seance;
    }
    
    
    
    @XmlElement
    public String getCode_module() {
        return code_module;
    }

    public void setCode_module(String code_module) {
        this.code_module = code_module;
    }
    @XmlElement    

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    @XmlElement
    public String getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }
    @XmlElement
    public String getDate_changement() {
        return date_changement;
    }

    public void setDate_changement(String date_changement) {
        this.date_changement = date_changement;
    }
    @XmlElement
    public String getEtat_avancement() {
        return etat_avancement;
    }

    public void setEtat_avancement(String etat_avancement) {
        this.etat_avancement = etat_avancement;
    }
    
    
}
