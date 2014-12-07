/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import com.TLSI.SSSB.Beans.Compte_Utilisateur;
import com.TLSI.SSSB.Beans.Enseignant;
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
public class EnseignantDAOImpl implements EnseignantDAO{
    private static final String REQ_GETINFO_ENS = "SELECT idEnseignant,nom,prenom,date_naissance,grade FROM Enseignant Where Compte_auth_idCompte_auth = ?";
    private static final String REQ_AUTH = "SELECT idCompte_auth FROM Compte_auth WHERE nom_utilisateur = ? and mot_de_passe = ? ";
    
    private static final String REQ_SEND_ETATAVANCEMENT = " INSERT INTO Dates (etat_avancement) VALUES (?)"
            + "WHERE Seance_idSeance = ? and idDates = ?";
    
    private static final String REQ_GET_ENSEIGNANT = "SELECT distinct idEnseignant,nom,prenom,date_naissance,grade, count(distinct code_module),Compte_auth_idCompte_auth \n" +
"FROM Enseignant \n" +
"INNER JOIN Seance ON ( idEnseignant = Enseignant_idEnseignant )\n" +
"INNER JOIN Module ON ( Module_idModule = idModule)\n" +
"INNER JOIN Specialite ON (Specialite_idSpecialite = idSpecialite )\n" +
"WHERE code_specialite =? \n" +
"GROUP BY idEnseignant";
    
    private static final String REQ_GET_ALL_ENSEIGNANT = "SELECT idEnseignant,nom,prenom,grade \n" +
"FROM Enseignant";
    
    private static final String  REQ_CREER_COMPTE= "INSERT INTO compte_auth (nom_utilisateur,mot_de_passe) VALUES (?,?)";
    
    private static final String REQ_MODIFIER_COMPTE = "UPDATE compth_auth set nom_utilisateur = ? , "
            + "mot_de_passe = ? "
            + "WHERE idCompte_auth = ?";
    
    private static final String REQ_AJOUTER_ENSEIGNANT = "INSERT INTO enseignant (nom,prenom,date_naissance,grade,Compte_auth_idCompte_auth) VALUES (?,?,?,?,?)";
    
    private static final String REQ_ISEXIST = "SELECT idEnseignant From Enseignant "
            + "WHERE nom = ? and prenom = ?";
    
    private static final String REQ_GET_IDCOMPTE = "SELECT idCompte_auth from Compte_auth "
            + "WHERE nom_utilisateur = ? and mot_de_passe = ?";
    
    private static final String REQ_ENSEIGNANT_ACTUEL = "SELECT nom,prenom ,type,"
            + "(SELECT code_module from module where sc.Module_idModule = idModule) as codeModule \n" +
"FROM Enseignant\n" +
"INNER JOIN Seance as sc ON (idEnseignant = Enseignant_idEnseignant )\n" +
"INNER JOIN Dates ON (idSeance = Seance_idSeance )\n" +
"WHERE DATE(date_seance) = CURDATE() OR DATE(date_changement_seance) = CURDATE()";
    
    private static final String REQ_ENSEIGNAT_MODULE = "SELECT code_module , type \n" +
"From Enseignant \n" +
"INNER JOIN Seance ON ( idEnseignant = Enseignant_idEnseignant )\n" +
"INNER JOIN Module ON ( Module_idModule = idModule)\n" +
"WHERE idEnseignant =? \n" +
"GROUP BY code_module";
    
    private static final String REQ_MODIFER_ENS = "UPDATE Enseignant SET\n" +
"nom =?  ,\n" +
"prenom = ?,\n" +
"date_naissance = ?,\n" +
"grade = ?\n" +
"WHERE idEnseignant = ?\n" +
"";
    
    
    private DAOFactory daoFactory;

    EnseignantDAOImpl(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Enseignant> getEnseignant(String specialite)  throws DAOException{
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList <Enseignant> enseignants = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_GET_ENSEIGNANT, specialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                Enseignant enseignant = new Enseignant ();
               enseignant.setIdEnseignant(resultSet.getInt(1));
               enseignant.setNom(resultSet.getString(2));
               enseignant.setPrenom(resultSet.getString(3));
               enseignant.setDateNaissance(resultSet.getString(4));
               enseignant.setGrade(resultSet.getString(5));
               enseignant.setNbrModules(resultSet.getInt(6));
               enseignant.setIdCompth(resultSet.getInt(7));
                
                enseignants.add(enseignant);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return enseignants; 
    }
    
    @Override
    public List<Enseignant> getAllEnseignant()  throws DAOException{
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList <Enseignant> enseignants = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_GET_ALL_ENSEIGNANT);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
               Enseignant enseignant = new Enseignant ();
               enseignant.setIdEnseignant(resultSet.getInt(1));
               enseignant.setNom(resultSet.getString(2));
               enseignant.setPrenom(resultSet.getString(3));
               enseignant.setGrade(resultSet.getString(4));
               enseignants.add(enseignant);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return enseignants; 
    }

    @Override
    public int ajouterEnseignant(String nom, String prenom, String dateNaissance, String grade) throws DAOException {
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
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,
                  nom,prenom);

            preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_CREER_COMPTE,
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
               
                preaperedStatment3 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_AJOUTER_ENSEIGNANT,
                nom,prenom,dateNaissance,grade,idCompte);
               preaperedStatment3.executeUpdate();
               }
           }
           
           
           

           
                throw new DAOException("Échec de l'envoie de l'absence, aucun champ modifié dans la table.");
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }
        
    }

    @Override
    public List  getEnseignantActuel() throws DAOException {
        
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List <Enseignant> enseignants = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ENSEIGNANT_ACTUEL);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                Enseignant enseignant = new Enseignant ();
               enseignant.setNom(resultSet.getString(1));
               enseignant.setPrenom(resultSet.getString(2));
               enseignant.setGrade(resultSet.getString(3)+" "+resultSet.getString(4));//grade represente code module
              
                
                enseignants.add(enseignant);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return enseignants; 
    }

    @Override
    public List getModuleEnseignant(Integer idEnseignant) throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList <Module> modules = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ENSEIGNAT_MODULE, idEnseignant);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                Module module = new Module ();
                module.setCode_module(resultSet.getString(1));
                module.setType(resultSet.getString(2));
                
                modules.add(module);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return modules;     
    }

    @Override
    public void modifierEnseignant(int idEnseignant, String nomEnseignant, String prenomEnseignant, String dateNaissance, String grade) throws DAOException {
Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFER_ENS,
                   nomEnseignant,prenomEnseignant,dateNaissance,grade,idEnseignant);
               preaperedStatment.executeUpdate();
                preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIER_COMPTE,
                   nomEnseignant,prenomEnseignant);
               preaperedStatment2.executeUpdate();
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    
    }

    @Override
    public int envoyerEtatAvancement(String etatAvancement,int idSeance,int idDates) throws DAOException {
       Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        int resultat = 0;
        
      try {
            connexion = daoFactory.getConnection();
            
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_SEND_ETATAVANCEMENT,
                   etatAvancement,idSeance,idDates);
              resultat =  preaperedStatment.executeUpdate();
               
          
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }
    return resultat;
    }

    @Override
    public Enseignant authEnseignant(String user, String pw) throws DAOException {
       
     Connection connexion = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
       Enseignant enseignant = null ;
        
        Compte_Utilisateur compte = new Compte_Utilisateur();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_AUTH,user,pw);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
             compte.setId_Compte(resultSet.getInt(1));
              preparedStatement1 = initialisationRequetePreparee(connexion,REQ_GETINFO_ENS,resultSet.getInt(1));
               resultSet1 =  preparedStatement1.executeQuery();
               if(resultSet1.next()){
                   enseignant = new Enseignant ();
                   enseignant.setIdEnseignant(resultSet1.getInt(1));
                   enseignant.setNom(resultSet1.getString(2));
                   enseignant.setPrenom(resultSet1.getString(3));
                   enseignant.setDateNaissance(resultSet1.getString(4));
                   enseignant.setGrade(resultSet1.getString(5));
               }
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return enseignant;
            
    }
   
    }
    

