/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Agent;
import com.TLSI.SSSB.Beans.Enseignant;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Islam
 */
public class AgentDAOImpl implements AgentDAO{
    private static final String REQ_GET_AGETNS = "SELECT * FROM Agent_s ";
    
    private static final String REQ_ADD_AGENT = "INSERT INTO Agent_s (nom,prenom,Compte_auth_idCompte_auth) VALUES (?,?,?)";
    
    private static final String REQ_CREATE_COMPTE = "INSERT INTO compte_auth (nom_utilisateur,mot_de_passe) VALUES (?,?)";
    
    private static final String REQ_GET_IDCOMPTE = "SELECT idCompte_auth from Compte_auth "
            + "WHERE nom_utilisateur = ? and mot_de_passe = ?";
    private static final String REQ_ISEXIST = "SELECT idAgent From Agent_s "
            + "WHERE nom = ? and prenom = ?";
    
    private static final String REQ_MODIFIER_AGENT = "UPDATE Agent_s set "
            + "nom = ?"
            + ",prenom = ?"
            + "WHERE idAgent = ?";
    
private DAOFactory daoFactory;

    AgentDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public List getAgent() throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList <Agent> agents = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_GET_AGETNS);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                Agent agent = new Agent ();
                agent.setIdAgent(resultSet.getInt(1));
                agent.setNom(resultSet.getString(2));
                agent.setPrenom(resultSet.getString(3));
                agents.add(agent);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return agents;     
    
    }

    @Override
    public void ajouterAgent(String nom, String prenom) throws DAOException {
        
    Connection connexion = null;
    PreparedStatement preaperedStatment = null;
    PreparedStatement preaperedStatment2 = null;
    PreparedStatement preaperedStatment3 = null;
    PreparedStatement preaperedStatment4 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        Integer exist = 0;
        int idCompte=0 ;
        
        try {
            connexion = daoFactory.getConnection();
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,nom,prenom);

            preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_CREATE_COMPTE,
                   nom,prenom);

           
            preaperedStatment4 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_GET_IDCOMPTE,
                nom,prenom);


            resultSet =  preaperedStatment.executeQuery();
            if(!resultSet.next()){
               exist = 1 ;
               preaperedStatment2.executeUpdate();
               resultSet2 = preaperedStatment4.executeQuery();
               if(resultSet2.next()){
                   idCompte = resultSet2.getInt(1);
               
                preaperedStatment3 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ADD_AGENT,nom,prenom,idCompte);
               preaperedStatment3.executeUpdate();
               }
           } 
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }
    }

    @Override
    public void modifierAgent(int idAgent, String nom, String prenom) throws DAOException {
    Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_AGENT,
                   nom,prenom,idAgent);
               preaperedStatment.executeUpdate();
              /*  preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_COMPTE,
                   nomEnseignant,prenomEnseignant);
               preaperedStatment2.executeUpdate();*/
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

        
    
    }
    
}
