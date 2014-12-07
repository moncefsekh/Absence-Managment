/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import com.TLSI.SSSB.Beans.Seance;
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
public class SeanceDAOImpl implements SeanceDAO{
    private static final String REQ_CHANGER_SEANCE = "UPDATE Dates set date_changement_seance = ? WHERE idDates = ?";
    
    private static final String REQ_ISEXIST = "SELECT idSeance FROM Seance WHERE "
            + "type = ? and Enseignant_idEnseignant = ? and Module_idModule = ? ";
    
    private static final String REQ_ADDSEANCE = "INSERT INTO Seance (type,Enseignant_idEnseignant,Module_idModule) VALUES (?,?,?) ";
    
    private static final String REQ_MODIFIERSEANCE = "UPDATE Seance set "
            + "type = ?,"
            + "Enseignant_idEnseignant = ?,"
            + "Module_idModule = ? WHERE idSeance = ?";
    private static final String REQ_MODIFIERDATES = "UPDATE Dates set \n" +
"            date_seance = DATE_ADD(?, INTERVAL 7*? DAY) WHERE Seance_idSeance = ? and idDates = ? + ? ";
    
    private static final String REQ_GETSEANCE = "SELECT code_groupe, code_module, \n" +
"TYPE , DATE_FORMAT( date_seance,  '%Y-%m-%d' ) AS date_seance, DATE_FORMAT( date_seance,  '%H:%i' ) AS heure_seance, code_section, code_specialite, annee_specialite, idSeance\n" +
", DATE_FORMAT( date_changement_seance, '%Y-%m-%d' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance, '%H:%i' ) AS heure_changement_seance,idDates,nom_module,nom,prenom FROM Groupe\n" +
"INNER JOIN Section ON Section_idSection = idSection\n" +
"INNER JOIN Specialite ON Specialite_idSpecialite = idSpecialite\n" +
"INNER JOIN Module AS \n" +
"MODE ON idSpecialite = mode.Specialite_idSpecialite\n" +
"INNER JOIN Seance AS sean ON idModule = Module_idModule\n" +
"INNER JOIN Dates ON sean.idSeance = Seance_idSeance\n" +
"INNER JOIN Enseignant ON sean.Enseignant_idEnseignant = idEnseignant\n" +
"WHERE code_specialite = ? AND \n" +
"DATE( date_seance ) \n" +
"BETWEEN DATE_SUB( NOW() , INTERVAL 7 \n" +
"DAY ) \n" +
"AND  '2015-01-06'"
            + "GROUP BY idDates ";
    private static final String REQ_GET_SEANCE_JOURE = "SELECT code_groupe, code_module, \n" +
"TYPE ,DATE_FORMAT( date_seance, '%d-%m-%Y' ) AS date_seance, \n" +
"DATE_FORMAT( date_seance, '%H:%i' ) AS heure_seance, code_section, code_specialite,annee_specialite ,idSeance \n" +
", DATE_FORMAT( date_changement_seance, '%d-%m-%Y' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance, '%H:%i' ) AS heure_changement_seance,idDates FROM Groupe\n" +
"INNER JOIN Section ON Section_idSection = idSection\n" +
"INNER JOIN Specialite ON Specialite_idSpecialite = idSpecialite\n" +
"INNER JOIN Module AS \n" +
"MODE ON idSpecialite = mode.Specialite_idSpecialite\n" +
"INNER JOIN Seance AS sean ON idModule = Module_idModule\n" +
"INNER JOIN Dates ON sean.idSeance = Seance_idSeance\n" +
"INNER JOIN Enseignant ON sean.Enseignant_idEnseignant = idEnseignant\n" +
"WHERE idEnseignant =?\n" +
"AND DATE( date_seance ) = DATE( CURDATE( ) ) ";
    public static final String REQ_GET_ALL_SEANCE = "SELECT code_groupe, code_module, \n" +
"TYPE , DATE_FORMAT( date_seance,  '%d-%m-%Y' ) AS date_seance, DATE_FORMAT( date_seance,  '%H:%i' ) AS heure_seance, code_section, code_specialite, annee_specialite, idSeance\n" +
", DATE_FORMAT( date_changement_seance, '%d-%m-%Y' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance, '%H:%i' ) AS heure_changement_seance,idDates FROM Groupe\n" +
"INNER JOIN Section ON Section_idSection = idSection\n" +
"INNER JOIN Specialite ON Specialite_idSpecialite = idSpecialite\n" +
"INNER JOIN Module AS \n" +
"MODE ON idSpecialite = mode.Specialite_idSpecialite\n" +
"INNER JOIN Seance AS sean ON idModule = Module_idModule\n" +
"INNER JOIN Dates ON sean.idSeance = Seance_idSeance\n" +
"INNER JOIN Enseignant ON sean.Enseignant_idEnseignant = idEnseignant\n" +
"WHERE idEnseignant =?\n" +
"AND DATE( date_seance ) \n" +
"BETWEEN DATE_SUB( NOW( ) , INTERVAL 7 \n" +
"DAY ) \n" +
"AND  '2014-05-31'";
    public static final String REQ_GET_SEANCE_SEMAINE = "SELECT nom_groupe, code_module,\n" +
"TYPE ,DATE_FORMAT( date_seance, '%d-%m-%Y' ) AS date_seance,\n" +
"DATE_FORMAT( date_seance, '%H:%i' ) AS heure_seance,code_section,code_specialite\n" +
", DATE_FORMAT( date_changement_seance, '%d-%m-%Y' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance, '%H:%i' ) AS heure_changement_seance FROM Groupe \n" +
"INNER JOIN Section ON Section_idSection = idSection\n" +
"INNER JOIN Specialite ON Specialite_idSpecialite = idSpecialite\n" +
"INNER JOIN Module AS\n" +
"MODE ON idSpecialite = mode.Specialite_idSpecialite\n" +
"INNER JOIN Seance AS sean ON idModule = Module_idModule\n" +
"INNER JOIN Dates ON sean.idSeance = Seance_idSeance\n" +
"INNER JOIN Enseignant ON sean.Enseignant_idEnseignant = idEnseignant\n" +
"WHERE idEnseignant =? and WEEKOFYEAR(date_seance) = WEEKOFYEAR(NOW())";
    
    
    private static final String REQ_SPE_AN_SEC = "SELECT code_specialite, annee_specialite, code_section \n" +
"FROM Enseignant\n" +
"INNER JOIN Seance ON ( idEnseignant = Enseignant_idEnseignant ) \n" +
"INNER JOIN Module ON ( Module_idModule = idModule ) \n" +
"INNER JOIN Specialite AS spc ON ( Specialite_idSpecialite = spc.idSpecialite ) \n" +
"INNER JOIN Section AS sec ON ( spc.idSpecialite = sec.Specialite_idSpecialite ) \n" +
"WHERE idEnseignant =?"
            + " GROUP BY code_section ";
    private static final String REQ_GR = "SELECT nom_groupe\n" +
"FROM Enseignant\n" +
"INNER JOIN Seance ON ( idEnseignant = Enseignant_idEnseignant ) \n" +
"INNER JOIN Module ON ( Module_idModule = idModule ) \n" +
"INNER JOIN Specialite AS spc ON ( Specialite_idSpecialite = spc.idSpecialite ) \n" +
"INNER JOIN Section AS sec ON ( spc.idSpecialite = sec.Specialite_idSpecialite ) \n" +
"INNER JOIN Groupe ON ( sec.idSection = Section_idSection ) \n" +
"WHERE idEnseignant =?\n" +
"AND code_specialite =  ?\n" +
"AND annee_specialite =  ?\n" +
"AND code_section =  ?"
            + " GROUP BY nom_groupe ";
    private static final String REQ_MOD = "SELECT type , code_module\n" +
"FROM Enseignant\n" +
"INNER JOIN Seance ON ( idEnseignant = Enseignant_idEnseignant ) \n" +
"INNER JOIN Module ON ( Module_idModule = idModule ) \n" +
"INNER JOIN Specialite AS spc ON ( Specialite_idSpecialite = spc.idSpecialite ) \n" +
"INNER JOIN Section AS sec ON ( spc.idSpecialite = sec.Specialite_idSpecialite ) \n" +
"INNER JOIN Groupe ON ( sec.idSection = Section_idSection ) \n" +
"WHERE idEnseignant =?\n" +
"AND code_specialite =  ?\n" +
"AND annee_specialite =  ?\n" +
"AND code_section =  ?"
            + "AND nom_groupe = ? GROUP BY type ";
    
    private static final String REQ_DATES_SEANCE="SELECT DATE_FORMAT( date_seance,  '%Y-%m-%d' ) AS date_seance, DATE_FORMAT( date_seance,  '%H:%i' ) AS heure_seance, DATE_FORMAT( date_changement_seance,  '%Y-%m-%d' ) AS date_changement_seance, DATE_FORMAT( date_changement_seance,  '%H:%i' ) AS heure_changement_seance\n" +
"FROM Seance \n" +
"INNER JOIN Dates ON ( idSeance = Seance_idSeance ) \n" +
"WHERE idDates =?";
    
private DAOFactory daoFactory;

    SeanceDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Seance> getSeanceJour(int id) throws DAOException {
         Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_SEANCE_JOURE,id);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
                seance.setNom_groupe(resultSet.getString(1));
                seance.setcode_module(resultSet.getString(2));
                seance.setType(resultSet.getString(3));
                seance.setDate_seance(resultSet.getString(4));
                seance.setHeure_seance(resultSet.getString(5));
                seance.setCode_section(resultSet.getString(6));
                seance.setCode_specialite(resultSet.getString(7));
                seance.setAnnee_specialite(resultSet.getString(8));
                seance.setId_Seance(resultSet.getInt(9));
                seance.setDate_changement(resultSet.getString(10));
                seance.setHeure_changement(resultSet.getString(11));
                seance.setId_Dates(resultSet.getInt(12));
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;
        
        
    }

    @Override
    public List<Seance> getSeanceSemain(int id) throws DAOException {
            
            Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_SEANCE_SEMAINE,id);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
                seance.setNom_groupe(resultSet.getString(1));
                seance.setcode_module(resultSet.getString(2));
                seance.setType(resultSet.getString(3));
                seance.setDate_seance(resultSet.getString(4));
                seance.setHeure_seance(resultSet.getString(5));
                seance.setCode_section(resultSet.getString(6));
                seance.setCode_specialite(resultSet.getString(7));
                seance.setDate_changement(resultSet.getString(8));
                seance.setHeure_changement(resultSet.getString(9));
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;
        
        
    }

    @Override
    public List<Seance> get_spec_ann_sec(int id) throws DAOException {
       
     Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_SPE_AN_SEC,id);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
              
                seance.setCode_section(resultSet.getString(3));
                seance.setCode_specialite(resultSet.getString(1));
                seance.setAnnee_specialite(resultSet.getString(2));
                
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;
    }

    @Override
    public List getAllSeance(Integer id) throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GET_ALL_SEANCE,id);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
                seance.setNom_groupe(resultSet.getString(1));
                seance.setcode_module(resultSet.getString(2));
                seance.setType(resultSet.getString(3));
                seance.setDate_seance(resultSet.getString(4));
                seance.setHeure_seance(resultSet.getString(5));
                seance.setCode_section(resultSet.getString(6));
                seance.setCode_specialite(resultSet.getString(7));
                seance.setAnnee_specialite(resultSet.getString(8));
                seance.setId_Seance(resultSet.getInt(9));
                seance.setDate_changement(resultSet.getString(10));
                seance.setHeure_changement(resultSet.getString(11));
                    seance.setId_Dates(resultSet.getInt(12));
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;
            
    }

    @Override
    public Seance getDatesSeance(Integer idSeance) throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Seance seance = new Seance ();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_DATES_SEANCE,idSeance);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                
                
               
                seance.setDate_seance(resultSet.getString(1));
                seance.setHeure_seance(resultSet.getString(2));
                seance.setDate_changement(resultSet.getString(3));
                seance.setHeure_changement(resultSet.getString(4));
               
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seance;
             
    }

    @Override
    public List<Seance> getGroupeMenu(int idEnseignant, String code_specialite, String annee, String section) throws DAOException {
       
      Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GR,
                    idEnseignant,code_specialite,annee,section);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
              
                seance.setNom_groupe(resultSet.getString(1));
               
                
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;
    }

    @Override
    public List<Seance> getModuleMenu(int idEnseignant, String code_specialite, String annee, String section, String groupe) throws DAOException {
        
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_MOD,
                    idEnseignant,code_specialite,annee,section,groupe);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
              
                seance.setcode_module(resultSet.getString(1));
                seance.setType(resultSet.getString(2));
               
                
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;
    }

    @Override
    public List<Seance> getALlSeanceResp(String Specialite) throws DAOException {
     Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Seance> seances = new ArrayList();
        
         try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion,REQ_GETSEANCE,Specialite);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                
                Seance seance = new Seance ();
                seance.setNom_groupe(resultSet.getString(1));
                seance.setcode_module(resultSet.getString(2));
                seance.setType(resultSet.getString(3));
                seance.setDate_seance(resultSet.getString(4));
                seance.setHeure_seance(resultSet.getString(5));
                seance.setCode_section(resultSet.getString(6));
                seance.setCode_specialite(resultSet.getString(7));
                seance.setAnnee_specialite(resultSet.getString(8));
                seance.setId_Seance(resultSet.getInt(9));
                seance.setDate_changement(resultSet.getString(10));
                seance.setHeure_changement(resultSet.getString(11));
                seance.setId_Dates(resultSet.getInt(12));
                seance.setNomModule(resultSet.getString(13));
                seance.setNomEnseignant(resultSet.getString(14));
                seance.setPrenomEnseignant(resultSet.getString(15));
                
                seances.add( seance);
                
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return seances;   
    
    }

    @Override
    public void ajouterSeance (int idModule, String type, int idEnseignant, String date) throws DAOException {
      
    Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
        PreparedStatement preaperedStatment3 = null;
        PreparedStatement preaperedStatment4 = null;
        int IsExist = 0;
        int idSeance=0 ;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST,
                   type,idEnseignant,idModule);
              resultSet = preaperedStatment2.executeQuery();
              if(!resultSet.next()){
              IsExist = 1 ;
              preaperedStatment3 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ISEXIST, type,idEnseignant,idModule);
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ADDSEANCE,
                   type,idEnseignant,idModule);
                  preaperedStatment.executeUpdate();
               
              ResultSet result = preaperedStatment3.executeQuery();
              if(result.next()){
                   idSeance = result.getInt(1);
              }
            
              
               
               
                   for(int i = 0 ;i<17 ;i++){
                       String req = "INSERT INTO Dates (Seance_idSeance,date_seance) VALUES (?,(DATE_ADD(?, INTERVAL 7*? DAY))) ";
                       preaperedStatment4 =DAOUtilitaire.initialisationRequetePreparee(connexion,req,idSeance,date,i);
                       preaperedStatment4.executeUpdate();
                   }
               
              }
            
           

           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

       
    }

    @Override
    public void modifierSeance(int idSeance, int idModule, String type, int idEnseignant, String date,int idDate) throws DAOException {
    Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        PreparedStatement preaperedStatment2 = null;
        PreparedStatement preaperedStatment3 = null;
        int IsExist = 0;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment2 = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIERSEANCE,
                   type,idEnseignant,idModule,idSeance);
              preaperedStatment2.executeUpdate();
              String getIdDates = "SELECT idDates FROM Dates WHERE Seance_idSeance = ? LIMIT 0,1";
              preaperedStatment3 = DAOUtilitaire.initialisationRequetePreparee(connexion, getIdDates, idSeance);
              ResultSet result = preaperedStatment3.executeQuery();
              if(result.next()){
                  int idDates = result.getInt(1);
             for(int i=0; i<17; i++){
               preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MODIFIERDATES,
                   date,i,idSeance,idDates,i);
               preaperedStatment.executeUpdate(); 
              
            
             }
              }
           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

            
    
    }

    @Override
    public void changerSeance(int idDates, String dateChangement) throws DAOException {
       Connection connexion = null;
        PreparedStatement preaperedStatment = null;
      
       
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();
            
              preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_CHANGER_SEANCE,
                   dateChangement, idDates);
               preaperedStatment.executeUpdate();
              
            
           

           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

   
    
    }
            
    }
    

