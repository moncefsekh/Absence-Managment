/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import java.util.List;

/**
 *
 * @author SSSB
 */
public interface ModuleDAO {
    List get_Modules (String codeSpecialite, String anneeSpecialite) throws DAOException ;
    
    List getModuleBysSpecialite (String codeSpecialite) throws DAOException;
    
    int ajouterModule (String code_module,String nom_module,int nbrCredit,String semestre,int idSpecialite)throws DAOException;
    
    void modifierModule (int idModule,String codeModule,String nomModule,int nbrCredit,String semestre,int idSpecialite)throws DAOException;
}
