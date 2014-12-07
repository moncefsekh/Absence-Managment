/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Actualite;
import java.util.List;

/**
 *
 * @author Islam
 */
public interface ActualiteDAO {
    List <Actualite> getActualiteJour (String specialite,String annne_specialite) throws DAOException;
    List <Actualite> getActualiteSemaine ( String specialite,String anne_specialite)throws DAOException;
}
