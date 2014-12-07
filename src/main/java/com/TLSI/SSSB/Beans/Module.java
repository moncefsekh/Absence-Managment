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

@XmlRootElement(name = "module")
public class Module {
    
    private Integer id_Module;
    private String Semestre;
    private String Code_module;
    private String Nom_module;
     private String type;
     private Integer nbrCredit;
     private String annee ;
     private Integer idSpecialite;
    @XmlElement
    public Integer getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Integer idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
     
     
    @XmlElement
    public Integer getNbrCredit() {
        return nbrCredit;
    }

    public void setNbrCredit(Integer nbrCredit) {
        this.nbrCredit = nbrCredit;
    }
    @XmlElement
    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
     
     
        @XmlElement       
 
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
   
    
    @XmlElement
    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }
    
    
    @XmlElement
    public Integer getId_Module() {
        return id_Module;
    }

    public void setId_Module(Integer id_Module) {
        this.id_Module = id_Module;
    }
    @XmlElement
    public String getCode_module() {
        return Code_module;
    }

    public void setCode_module(String Code_module) {
        this.Code_module = Code_module;
    }
    @XmlElement
    public String getNom_module() {
        return Nom_module;
    }

    public void setNom_module(String Nom_module) {
        this.Nom_module = Nom_module;
    }
    
    
    
}
