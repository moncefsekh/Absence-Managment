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
@XmlRootElement(name = "section")
public class Section {
    
    private Integer idSection;
    private String codeSection;
    private String nomsection;
    private String annne;
    private Integer idSpecialite;
    @XmlElement
    public Integer getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Integer idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
    
    
    
    @XmlElement
    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }
    @XmlElement
    public String getCodeSection() {
        return codeSection;
    }

    public void setCodeSection(String codeSection) {
        this.codeSection = codeSection;
    }
    @XmlElement
    public String getNomsection() {
        return nomsection;
    }

    public void setNomsection(String nomsection) {
        this.nomsection = nomsection;
    }
    @XmlElement
    public String getAnnne() {
        return annne;
    }

    public void setAnnne(String annne) {
        this.annne = annne;
    }

   
}
