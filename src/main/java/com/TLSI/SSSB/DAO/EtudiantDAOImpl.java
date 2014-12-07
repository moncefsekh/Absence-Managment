/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Compte_Utilisateur;
import com.TLSI.SSSB.Beans.Detail_Absence;
import com.TLSI.SSSB.Beans.Etudiant;
import com.TLSI.SSSB.Beans.InfoEtudiant;

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
public class EtudiantDAOImpl implements EtudiantDAO{
    private static final String REQ_AUTH = "SELECT * FROM Compte_auth WHERE nom_utilisateur = ? and mot_de_passe = ? ";
    
    private static final String REQ_NBR_ETUDIANT = "SELECT COUNT( num_etudiant ) \n" +
"FROM Etudiant\n" +
"INNER JOIN Groupe ON ( Groupe_idGroupe = idGroupe ) \n" +
"INNER JOIN Section ON ( Section_idSection = idSection ) \n" +
"INNER JOIN Specialite ON ( Specialite_idSpecialite = idSpecialite ) \n" +
"WHERE code_specialite = ? ";
     private static final String REQ_ETUDIANT_GROUPE ="SELECT nom, prenom, num_etudiant, idSeance\n" +
"FROM Etudiant\n" +
"INNER JOIN Groupe AS Gr ON ( Groupe_idGroupe = Gr.idGroupe ) \n" +
"INNER JOIN Section AS Sec ON ( Section_idSection = Sec.idSection ) \n" +
"INNER JOIN Specialite Sp ON ( Sec.Specialite_idSpecialite = Sp.idSpecialite ) \n" +
"INNER JOIN Module AS md ON ( Sp.idSpecialite = md.Specialite_idSpecialite ) \n" +
"INNER JOIN Seance ON ( idModule = Module_idModule ) \n" +
"WHERE code_specialite = ? \n" +
"AND annee_specialite = ? \n" +
"AND code_section = ? \n" +
"AND code_groupe = ? \n" +
"AND idSeance =? ";
     private static final String REQ_ALL_ETUDIANT = "SELECT Etudiant.* , (SELECT count(Absence.Etudiant_num_carte) FROM Absence WHERE Absence.Etudiant_num_carte = Etudiant.num_etudiant) as nombreAbsences, Groupe.code_groupe \n" +
"FROM Etudiant,Groupe,Section,Specialite \n" +
"WHERE (Etudiant.Groupe_idGroupe = Groupe.idGroupe)\n" +
"AND (Groupe.Section_idSection = Section.idSection)\n" +
"AND (Section.Specialite_idSpecialite = Specialite.idSpecialite)\n" +
"AND (code_specialite = ?)\n" +
"AND (annee_specialite = ?)";
     
     private static final String REQ_INFO_ETUDIANT = "SELECT code_groupe,code_section,code_specialite,annee_specialite FROM Etudiant\n" +
"INNER JOIN Groupe ON (Groupe_idGroupe = idGroupe)\n" +
"INNER JOIN Section ON (Section_idSection = idSection)\n" +
"INNER JOIN Specialite ON (Specialite_idSpecialite = idSpecialite)\n" +
"WHERE Compte_auth_idCompte_auth = ?";
     
     private static final String REQ_ETUDIANT_ID="SELECT * FROM Etudiant WHERE idEtudiant=?";
     
      private static final String REQ_DETAIL_ABSENCE = "SELECT code_module,COUNT(idAbsence),COUNT(Etat_justifier),Nom_Etudiant,Prenom_Etudiant,Num_Carte,Date_naissance_Etudiant,Type from etudiant\n" +
"INNER JOIN absence as ab ON ( idEtudiant = ab.Etudiant_idEtudiant)\n" +
"INNER JOIN seance as sea ON (Seance_idSeance = sea.idSeance)\n" +
"INNER JOIN enseignement as ensg ON (Enseignement_idEnseignement = ensg.idEnseignement)\n" +
"INNER JOIN module as modu ON (Module_idModule = modu.idModule)\n" +
"WHERE Etudiant_idEtudiant = ?\n" +
"GROUP BY modu.code_module";
     
      private DAOFactory daoFactory;

    EtudiantDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List <Etudiant> getAllEtudiant(String specialite,String annee) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList <Etudiant> etudiants = new ArrayList();
        
        
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_ALL_ETUDIANT,specialite,annee);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Etudiant etudiant = new Etudiant ();
                etudiant.setIdEtudiant(resultSet.getInt(1));
                etudiant.setNom_Etudiant(resultSet.getString(2));
                etudiant.setPrenom_Etudiant(resultSet.getString(3));
                etudiant.setNomPrenomMere(resultSet.getString(4));
                etudiant.setPrenomPere(resultSet.getString(5));             
                etudiant.setDate_naissance_Etudiant(resultSet.getString(6));
                etudiant.setDate_Bac(resultSet.getString(7));
              
                etudiant.setMoy_Bac(resultSet.getFloat(8));
                etudiant.setSexe(resultSet.getBoolean(9));
                etudiant.setNombreAbsences(resultSet.getInt(12));
                etudiant.setCodeGroupe(resultSet.getString(13));
                
                etudiants.add( etudiant);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return etudiants;
        
        
        
        
    }

    

    @Override
    public Etudiant getEtudiantById(int id) throws DAOException {
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         Etudiant etudiant = new Etudiant();
          try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_ETUDIANT_ID,id);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
              
               etudiant.setNom_Etudiant(resultSet.getString(2));
               etudiant.setPrenom_Etudiant(resultSet.getString(3));
               etudiant.setDate_naissance_Etudiant(resultSet.getString(4));
               etudiant.setNum_Carte(resultSet.getString(5));
               etudiant.setDate_Bac(resultSet.getString(6));
               etudiant.setMoy_Bac(resultSet.getFloat(7));
               // a changer dans la BD
               etudiant.setSexe(true);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return etudiant;
        
        
        
    }

    @Override
    public List detailAbsenceEtudiant(int id) throws DAOException {
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        ArrayList detail = new ArrayList();
         
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_DETAIL_ABSENCE, id);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
               Detail_Absence detail_absence = new Detail_Absence();
                detail_absence.setDate_naissance(resultSet.getDate(7).toString());
                detail_absence.setNbr_absence(resultSet.getInt(2));
                detail_absence.setNbr_justifiee(resultSet.getInt(3));
                detail_absence.setNom(resultSet.getString(4));
                detail_absence.setNom_midule(resultSet.getString(1));
                detail_absence.setNum_carte(resultSet.getString(6));
                detail_absence.setPrenom(resultSet.getString(5));
                detail_absence.setType_seance(resultSet.getString(8));
                
                detail.add(detail_absence);
                
                int i = 1 ;
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return detail;
        
        
    }

   

    @Override
    public InfoEtudiant getInfoEtudiant(int id) throws DAOException {
        
         Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         InfoEtudiant infoEtudiant = new InfoEtudiant();
          try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_INFO_ETUDIANT,id);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
              
               infoEtudiant.setCode_groupe(resultSet.getString(1));
               infoEtudiant.setCode_section(resultSet.getString(2));
               infoEtudiant.setCode_specialite(resultSet.getString(3));
            }
        } catch ( SQLException e ) { 
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return infoEtudiant;
        
        
    }

    @Override
    public List<Etudiant> getEtudiantGroupe(String specialite, String annee_specialite, String section, String groupe, Integer idSeance) {
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Etudiant> etudiants = new ArrayList();
        
        
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_ETUDIANT_GROUPE,specialite,annee_specialite,section,groupe,idSeance);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Etudiant etudiant = new Etudiant ();
                etudiant.setNom_Etudiant(resultSet.getString(1));
                etudiant.setPrenom_Etudiant(resultSet.getString(2));
                etudiant.setNum_Carte(resultSet.getString(3));
               
                
                etudiants.add( etudiant);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return etudiants;
    }

    @Override
    public Etudiant getNbrEtudiant(String specialite) throws DAOException {
        
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         Etudiant etudiant = new Etudiant();
          try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_NBR_ETUDIANT,specialite);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
              
               etudiant.setNbrEtudiants(resultSet.getInt(1));
               // a changer dans la BD
              etudiant.setSexe(true);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return etudiant;
        
        
    }

    @Override
    public InfoEtudiant authEtudiant(String user, String pw) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       
        InfoEtudiant infoEtudiant = null;
        Compte_Utilisateur compte = new Compte_Utilisateur();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_AUTH,user,pw);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                
              compte.setId_Compte(resultSet.getInt(1));
              infoEtudiant=getInfoEtudiant(resultSet.getInt(1)); 
               
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return infoEtudiant;
            
    
    }

    
    
}
