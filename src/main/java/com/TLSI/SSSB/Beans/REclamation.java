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
@XmlRootElement(name = "reclamation")
public class REclamation {
    private String raison_reclamation;
    private String date_reclamation;
    @XmlElement
    public String getRaison_reclamation() {
        return raison_reclamation;
    }

    public void setRaison_reclamation(String raison_reclamation) {
        this.raison_reclamation = raison_reclamation;
    }
    @XmlElement
    public String getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(String date_reclamation) {
        this.date_reclamation = date_reclamation;
    }
    
    
}
