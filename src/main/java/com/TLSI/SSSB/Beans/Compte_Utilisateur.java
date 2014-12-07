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

@XmlRootElement(name = "absence")
public class Compte_Utilisateur {
    
    private int idCompte;
    private String nomUtilisateur;
    private String motDePasse ;
@XmlElement
    public int getId_Compte() {
        return idCompte;
    }

    public void setId_Compte(int idCompte) {
        this.idCompte = idCompte;
    }
@XmlElement
    public String getnomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNom_Utilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
@XmlElement
    public String getmotDePasse() {
        return motDePasse;
    }

    public void setMot_de_passe(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    
    
}
