/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import com.TLSI.SSSB.Beans.Module;

import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SSSB
 */
public class ModuleDAOImpl implements ModuleDAO{
    private static final String REQ_MODULES = "SELECT code_module,Semestre \n" +
"FROM Module\n" +
"INNER JOIN Specialite ON ( Specialite_idSpecialite = idSpecialite )\n" +
"WHERE code_specialite = ? and annee_specialite =?";
    
    private static final String REQ_GET_MODULE = "SELECT code_module,nom_module,annee_specialite,semestre,nbr_credit,idModule,Specialite_idSpecialite \n" +
"FROM Module\n" +
"INNER JOIN Specialite ON ( Specialite_idSpecialite = idSpecialite)\n" +
"WHERE code_specialite = ?";
    
    private static final String REQ_ADD_MODULE = "INSERT INTO Module (code_module,nom_module,nbr_credit,Semestre,Specialite_idSpecialite)\n" +
" VALUES (?,?,?,?,?)";
    
    private static final String REQ_ISEXIST = "SELECT idModule FROM Module"
            + " WHERE nom_module = ? and Specialite_idSpecialite = ? ";
    
    private static final String REQ_MODIFIER_MODULE = "UPDATE Module SET\n" +
"code_module = ? ,\n" +
"nom_module = ? ,\n" +
"nbr_credit = ? ,\n" +
"Specialite_idSpecialite = ? ,\n" +
"semestre = ? \n" +
"WHERE idModule = ? ;";
    
    private DAOFactory daoFactory;

    ModuleDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Module> get_Modules(String codeSpecialite, String anneeSpecialite) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Module> modules = new ArrayList();
        
        
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_MODULES,codeSpecialite,anneeSpecialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Module module = new Module ();
                module.setCode_module(resultSet.getString(1));
                module.setSemestre(resultSet.getString(2));
                
                modules.add( module);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return modules;
        
        
        
    }

    @Override
    public List getModuleBysSpecialite(String codeSpecialite) throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Module> modules = new ArrayList();
        
        
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_MODULE,codeSpecialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Module module = new Module ();
                module.setCode_module(resultSet.getString(1));
                module.setNom_module(resultSet.getString(2));
                module.setAnnee(resultSet.getString(3));
                
                module.setSemestre(resultSet.getString(4));
                module.setNbrCredit(resultSet.getInt(5));
                module.setId_Module(resultSet.getInt(6));
                module.setIdSpecialite(resultSet.getInt(7));
                
                modules.add( module);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return modules;
        
            
    }

    @Override
    public int ajouterModule(String codeModule, String nomModule, int nbrCredit, String semestre, int idSpecialite) throws DAOException {
        Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
        int IsExist = 0;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,
                   nomModule,idSpecialite);
              resultSet = preaperedStatment2.executeQuery();
              if(!resultSet.next()){
              IsExist = 1 ;
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ADD_MODULE,
                   codeModule,nomModule,nbrCredit,semestre,idSpecialite);
               preaperedStatment.executeUpdate();
              }
            
           

           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

        return IsExist;
    }

    @Override
    public void modifierModule(int idModule,String codeModule, String nomModule, int nbrCredit, String semestre, int idSpecialite) throws DAOException {

      Connection connexion = null;
        PreparedStatement preaperedStatment = null;
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_MODULE,
                   codeModule,nomModule,nbrCredit,idSpecialite,semestre,idModule);
               preaperedStatment.executeUpdate();
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    }

   
    
}
