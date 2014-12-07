/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Compte_Utilisateur;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SSSB
 */
public class ResponsableDAOImpl implements ResponsableDAO {

    private static final String REQ_AUTH = "SELECT idCompte_auth FROM Compte_auth WHERE nom_utilisateur = ? and mot_de_passe = ? ";

    private DAOFactory daoFactory;

    ResponsableDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Boolean authResponsable(String user, String pw) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean res = false;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, REQ_AUTH, user, pw);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = (resultSet.getInt(1) == 1); 
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return res;

    }

}
