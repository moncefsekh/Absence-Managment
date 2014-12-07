/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Parametres;

/**
 *
 * @author Islam
 */
public interface ParametresDAO {
    Parametres getParametres ()throws DAOException ;
    
    void modifierParametres (int nbrAbsence,int nbrJustifier,String dateDebutSemstre1,String dateDebutSemestre2,String dateFinSemestre1,String dateFinsemestre2)throws DAOException;
}
