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
@XmlRootElement(name = "infoEtudiant")
public class InfoEtudiant {
    

    private String code_groupe;
    private String code_section;
    private String code_specialite;
    @XmlElement
    public String getCode_groupe() {
        return code_groupe;
    }

    public void setCode_groupe(String code_groupe) {
        this.code_groupe = code_groupe;
    }
    @XmlElement
    public String getCode_section() {
        return code_section;
    }

    public void setCode_section(String code_section) {
        this.code_section = code_section;
    }
    @XmlElement
    public String getCode_specialite() {
        return code_specialite;
    }

    public void setCode_specialite(String code_specialite) {
        this.code_specialite = code_specialite;
    }
    
    
    
}
