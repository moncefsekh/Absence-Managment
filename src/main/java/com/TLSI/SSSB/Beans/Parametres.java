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

@XmlRootElement(name = "parametres")
public class Parametres {

    private int nbrAbsence;
    private int nbrJustifier;
    private String dateDebutSemestre1;
    private String dateDebutSemestre2;
    private String dateFinSemestre1;
    private String dateFinSemestre2;
    @XmlElement
    public int getNbrAbsence() {
        return nbrAbsence;
    }

    public void setNbrAbsence(int nbrAbsence) {
        this.nbrAbsence = nbrAbsence;
    }
    @XmlElement
    public int getNbrJustifier() {
        return nbrJustifier;
    }

    public void setNbrJustifier(int nbrJustifier) {
        this.nbrJustifier = nbrJustifier;
    }
    @XmlElement
    public String getDateDebutSemestre1() {
        return dateDebutSemestre1;
    }

    public void setDateDebutSemestre1(String dateDebutSemestre1) {
        this.dateDebutSemestre1 = dateDebutSemestre1;
    }
    @XmlElement
    public String getDateDebutSemestre2() {
        return dateDebutSemestre2;
    }

    public void setDateDebutSemestre2(String dateDebutSemestre2) {
        this.dateDebutSemestre2 = dateDebutSemestre2;
    }
    @XmlElement
    public String getDateFinSemestre1() {
        return dateFinSemestre1;
    }

    public void setDateFinSemestre1(String dateFinSemestre1) {
        this.dateFinSemestre1 = dateFinSemestre1;
    }
    @XmlElement
    public String getdateFinSemestre2() {
        return dateFinSemestre2;
    }

    public void setdateFinSemestre2(String dateFinSemestre2) {
        this.dateFinSemestre2 = dateFinSemestre2;
    }
    
    
}
