/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Parametres;

import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Islam
 */
public class ParametresDAOImpl implements ParametresDAO{
    
    private static final String REQ_MODIFERPARM = "UPDATE parametres set"
            + "nbr_absence = ? ,"
            + "nbr_justifier = ? ,"
            + "date_debut_semestre1= ? ,"
            + "date_debut_semestre2= ? ,"
            + "date_fin_semestre1= ? ,"
            + "date_fin_semestre2= ?";
    
    
    private static final String REQ_GETPARAMETRES = "SELECT * \n" +
"FROM parametres";
    
    private DAOFactory daoFactory;

    ParametresDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Parametres getParametres() throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         Parametres parametre = new Parametres();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GETPARAMETRES);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
               parametre.setDateDebutSemestre1(resultSet.getString(3));
               parametre.setDateDebutSemestre2(resultSet.getString(4));
               parametre.setDateFinSemestre1(resultSet.getString(5));
               parametre.setdateFinSemestre2(resultSet.getString(6));
               parametre.setNbrAbsence(resultSet.getInt(1));
               parametre.setNbrJustifier(resultSet.getInt(2));
              
                
               
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return parametre;    
    
    }

    @Override
    public void modifierParametres(int nbrAbsence, int nbrJustifier, String dateDebutSemstre1, String dateDebutSemestre2, String dateFinSemestre1, String dateFinsemestre2) throws DAOException {
    Connection connexion = null;
     PreparedStatement preaperedStatment = null;
        
        
        
        
        try {
            connexion = daoFactory.getConnection();
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFERPARM,
                   nbrAbsence,nbrJustifier,dateDebutSemstre1,dateDebutSemestre2,dateFinSemestre1,dateFinsemestre2);

            int statu = preaperedStatment.executeUpdate();

            if (statu == 0) {
                throw new DAOException("Échec de l'envoie de l'absence, aucun champ modifié dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }
  
    
    }
    
}
