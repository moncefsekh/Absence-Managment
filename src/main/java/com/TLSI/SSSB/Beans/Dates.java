/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Beans;

/**
 *
 * @author SSSB
 */
public class Dates {
    private int id_dates;
    private String date_seance ;
    private String date_changement_seance ;
    private String date_effet;

    public int getId_dates() {
        return id_dates;
    }

    public void setId_dates(int id_dates) {
        this.id_dates = id_dates;
    }

    public String getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }

    public String getDate_changement_seance() {
        return date_changement_seance;
    }

    public void setDate_changement_seance(String date_changement_seance) {
        this.date_changement_seance = date_changement_seance;
    }

    public String getDate_effet() {
        return date_effet;
    }

    public void setDate_effet(String date_effet) {
        this.date_effet = date_effet;
    }
    
    
}
