/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TLSI.SSSB.DAO;

import com.TLSI.SSSB.Beans.Absence;
import com.TLSI.SSSB.Beans.Etudiant;
import com.TLSI.SSSB.Beans.Specialite;

import static com.TLSI.SSSB.DAO.DAOUtilitaire.fermeturesSilencieuses;
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
public class AbsenceDAOImpl implements AbsenceDAO {

    private static final String REQ_ABSENCE_SEMAINE_DATE = "SELECT DATE(date_absence),COUNT(idAbsence),(SELECT distinct code_specialite FROM Specialite ,Module,Seance,Absence as ab\n"
            + "WHERE idSpecialite = Specialite_idSpecialite and idModule = Module_idModule and idSeance = ab.Seance_idSeance\n"
            + "and ab.idAbsence = idAbsence and code_specialite = ? ) as Specialite\n"
            + "FROM Absence \n"
            + "INNER JOIN Seance as sc ON (Seance_idSeance = sc.idSeance)\n"
            + "INNER JOIN Dates as dt ON (sc.idSeance = dt.Seance_idSeance)\n"
            + "WHERE (DATE(date_absence) = DATE(dt.date_seance) or DATE(date_absence) = DATE(dt.date_seance)) and WEEKOFYEAR(DATE(date_absence)) = WEEKOFYEAR(CURDATE())\n"
            + "Group by DATE(date_absence)";

    private static final String REQ_SUPPRIMER_ABSENCES = "DELETE FROM Absence WHERE idAbsence = ?";

    private static final String REQ_SEND_RECLAMATION = "INSERT INTO Reclamation (raison_reclamation, date_reclamation,Absence_idAbsence) VALUES (?,NOW(),1)";

    private static final String REQ_EXCLUSION = "SELECT code_module, \n"
            + "TYPE , etat_justificatif, COUNT( \n"
            + "TYPE ) , SUM( etat_justificatif ) \n"
            + "FROM Etudiant AS etu\n"
            + "INNER JOIN Absence AS abs ON ( etu.num_etudiant = abs.Etudiant_num_carte ) \n"
            + "INNER JOIN Seance AS sea ON ( abs.Seance_idSeance = sea.idSeance ) \n"
            + "INNER JOIN Module ON ( sea.Module_idModule = idModule ) \n"
            + "WHERE etu.num_etudiant =? \n"
            + "GROUP BY TYPE ,code_module ";
    private static final String REQ_EXCLUS_MODULE = "SELECT num_etudiant, nom, prenom, annee_specialite, code_specialite, code_module, \n"
            + "TYPE , etat_justificatif, COUNT( \n"
            + "TYPE )as NbrAbsence , SUM( etat_justificatif ) as NbrJustifier\n"
            + "FROM Etudiant AS etu\n"
            + "INNER JOIN Absence AS abs ON ( etu.num_etudiant = abs.Etudiant_num_carte ) \n"
            + "INNER JOIN Seance AS sea ON ( abs.Seance_idSeance = sea.idSeance ) \n"
            + "INNER JOIN Module ON ( sea.Module_idModule = idModule ) \n"
            + "INNER JOIN Specialite ON ( Specialite_idSpecialite = idSpecialite ) \n"
            + "WHERE code_specialite = ? \n"
            + "GROUP BY TYPE , code_module, nom";

    private static final String REQ_BILAN_Op = "SELECT COUNT(idAbsence),COUNT(Etat_justifier),nom_module,date_oridinaire,Etudiant_idEtudiant FROM absence\n"
            + "INNER JOIN seance as sea ON (Seance_idSeance = sea.idSeance) \n"
            + "INNER JOIN enseignement as ensg ON (Enseignement_idEnseignement = ensg.idEnseignement)\n"
            + "INNER JOIN module as modu ON (Module_idModule = modu.idModule)\n"
            + "WHERE Etudiant_idEtudiant = ?";

    private static final String REQ_BILAN = "SELECT DATE_FORMAT( date_absence, '%d-%m-%Y' ) AS date_absence, DATE_FORMAT( date_absence, '%H:%i' ) AS heure_absence, code_module,\n"
            + "TYPE , etat_justificatif \n"
            + "FROM Etudiant AS etu\n"
            + "INNER JOIN Absence AS abs ON ( etu.num_etudiant = abs.Etudiant_num_carte )\n"
            + "INNER JOIN Seance AS sea ON ( abs.Seance_idSeance = sea.idSeance )\n"
            + "INNER JOIN Module ON ( sea.Module_idModule = idModule )\n"
            + "WHERE etu.num_etudiant =? \n"
            + "";

    private static final String REQ_ABSENCE_SEMAINE = "SELECT DATE_FORMAT( date_absence, '%d-%m' ) AS date_absence, DATE_FORMAT( date_absence, '%H:%i' ) AS heure_absence, code_module,\n"
            + "TYPE , etat_justificatif\n"
            + "FROM Etudiant AS etu\n"
            + "INNER JOIN Absence AS abs ON ( etu.num_etudiant = abs.Etudiant_num_carte )\n"
            + "INNER JOIN Seance AS sea ON ( abs.Seance_idSeance = sea.idSeance )\n"
            + "INNER JOIN Module ON ( sea.Module_idModule = idModule )\n"
            + "WHERE etu.num_etudiant =? and date_absence and  WEEKOFYEAR(date_absence) = WEEKOFYEAR(NOW())";

    private static final String REQ_ABSENCE_DAY = "SELECT COUNT(idAbsence) FROM absence\n"
            + "INNER JOIN etudiant as et ON (Etudiant_idEtudiant = et.idEtudiant)\n"
            + "INNER JOIN groupe as Gr ON (Groupe_idGroupe = Gr.idGroupe)\n"
            + "INNER JOIN section as sec ON (Section_idSection = sec.idSection)\n"
            + "INNER JOIN specialite as sp ON (Specialite_idSpecialite = sp.idSpecialite)\n"
            + "WHERE code_specialite = ?\n"
            + "GROUP BY DAY(Date_effet)";

    private static final String REQ_ABSENCE_WEEK = "SELECT COUNT(idAbsence) FROM absence\n"
            + "INNER JOIN etudiant as et ON (Etudiant_idEtudiant = et.idEtudiant)\n"
            + "INNER JOIN groupe as Gr ON (Groupe_idGroupe = Gr.idGroupe)\n"
            + "INNER JOIN section as sec ON (Section_idSection = sec.idSection)\n"
            + "INNER JOIN specialite as sp ON (Specialite_idSpecialite = sp.idSpecialite)\n"
            + "WHERE code_specialite = ?\n"
            + "GROUP BY WEEK(Date_effet)";

    private static final String REQ_ABSENCE_MONTH = "SELECT COUNT(idAbsence) FROM absence\n"
            + "INNER JOIN etudiant as et ON (Etudiant_idEtudiant = et.idEtudiant)\n"
            + "INNER JOIN groupe as Gr ON (Groupe_idGroupe = Gr.idGroupe)\n"
            + "INNER JOIN section as sec ON (Section_idSection = sec.idSection)\n"
            + "INNER JOIN specialite as sp ON (Specialite_idSpecialite = sp.idSpecialite)\n"
            + "WHERE code_specialite = ?\n"
            + "GROUP BY MONTH(Date_effet)";

    private static final String REQ_ABSENCE_YEAR = "SELECT COUNT(idAbsence) FROM absence\n"
            + "INNER JOIN etudiant as et ON (Etudiant_idEtudiant = et.idEtudiant)\n"
            + "INNER JOIN groupe as Gr ON (Groupe_idGroupe = Gr.idGroupe)\n"
            + "INNER JOIN section as sec ON (Section_idSection = sec.idSection)\n"
            + "INNER JOIN specialite as sp ON (Specialite_idSpecialite = sp.idSpecialite)\n"
            + "WHERE code_specialite = ?\n"
            + "GROUP BY YEAR(Date_effet)";

    private static final String REQ_GET_ABSENT = "SElect distinct num_Etudiant,idAbsence from Etudiant \n"
            + "INNER JOIN Absence as ab ON ( num_Etudiant = ab.Etudiant_num_carte )\n"
            + "INNER JOIN Seance ON ( ab.Seance_idSeance = idSeance )\n"
            + "INNER JOIN Dates as dt ON ( idSeance = dt.Seance_idSeance)\n"
            + "WHERE idSeance =? and idDates = ?";

    private static final String REQ_MARQUER
            = "INSERT INTO Absence(  Seance_idSeance, date_absence,Etudiant_num_carte ) \n"
            + "VALUES ( ?, ?,  ?) ";

    private static final String REQ_JUSTIFIER = "UPDATE absence SET Etat_justifier = ? where Etudiant_idEtudiant = ?";

    private static final String REQ_GET_NBR_ABSENT_JOUR = "SELECT COUNT(idAbsence),(SELECT distinct code_specialite FROM Specialite ,Module,Seance,Absence as ab\n"
            + "WHERE idSpecialite = Specialite_idSpecialite and idModule = Module_idModule and idSeance = ab.Seance_idSeance\n"
            + "and ab.idAbsence = idAbsence and code_specialite = ? ) as Specialite\n"
            + "FROM Absence \n"
            + "INNER JOIN Seance as sc ON (Seance_idSeance = sc.idSeance)\n"
            + "INNER JOIN Dates as dt ON (sc.idSeance = dt.Seance_idSeance)\n"
            + "WHERE (DATE(date_absence) = DATE(dt.date_seance) or DATE(date_absence) = DATE(dt.date_seance)) and DATE(date_absence) = '2014-06-08'";

    private DAOFactory daoFactory;

    AbsenceDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    /*
     Methode pour marquer l'absence d'un etudiant par un enseignant 
     dans une seance d'un enseignement 
     */

    @Override
    public void marquer(Integer idSeance, String date_absence, Integer etudiants) throws DAOException {
        Connection connexion = null;
        PreparedStatement preaperedStatment = null;

        try {
            connexion = daoFactory.getConnection();
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_MARQUER,
                    idSeance, date_absence, etudiants);

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

    /*
     Methode pour les justifications des absences par l'agent 
     */
    @Override
    public void justifier(Etudiant etudiant) throws DAOException {
        Connection connexion = null;
        PreparedStatement preaperedStatment = null;

        try {
            connexion = daoFactory.getConnection();
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_JUSTIFIER,
                    true, etudiant.getIdEtudiant());

            int statu = preaperedStatment.executeUpdate();

            if (statu == 0) {
                throw new DAOException("Échec de la justification de l'absence, aucun champ modifié dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    }

    /*
     Methode de recuperation du bilan complet des absences 
     d'un etudiant 
     */
    @Override
    public List<Absence> getAbsence(int id) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Absence> absences = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_BILAN, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setDate_absence(resultSet.getString(1));
                absence.setHeure_absence(resultSet.getString(2));
                absence.setCode_midule(resultSet.getString(3));
                absence.setType_seance(resultSet.getString(4));
                absence.setStatu_justifier(resultSet.getBoolean(5));

                absences.add(absence);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absences;
    }

    @Override
    public int getAbsenceDay(Specialite specialite) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int co = 0;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ABSENCE_DAY, specialite.getCode_specialite());
            resultSet = preparedStatement.executeQuery();
            co = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return co;
    }

    @Override
    public int getAbsenceWeek(Specialite specialte) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int co = 0;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ABSENCE_WEEK, specialte.getCode_specialite());
            resultSet = preparedStatement.executeQuery();
            co = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return co;

    }

    @Override
    public int getAbsenceMonth(Specialite specialite) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int co = 0;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ABSENCE_MONTH, specialite.getCode_specialite());
            resultSet = preparedStatement.executeQuery();
            co = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return co;
    }

    @Override
    public int getAbsenceYear(Specialite specialite) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int co = 0;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ABSENCE_YEAR, specialite.getCode_specialite());
            resultSet = preparedStatement.executeQuery();
            co = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return co;
    }

    @Override
    public List<Absence> getAbsenceWeek(int id) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Absence> absences = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ABSENCE_SEMAINE, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setDate_absence(resultSet.getString(1));
                absence.setHeure_absence(resultSet.getString(2));
                absence.setCode_midule(resultSet.getString(3));
                absence.setType_seance(resultSet.getString(4));
                absence.setStatu_justifier(resultSet.getBoolean(5));

                absences.add(absence);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absences;

    }

    @Override
    public int send_reclamation(String raison) throws DAOException {

        Connection connexion = null;
        PreparedStatement preaperedStatment = null;
        int statu = 0;
        try {
            connexion = daoFactory.getConnection();
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_SEND_RECLAMATION,
                    raison);

            statu = preaperedStatment.executeUpdate();

            if (statu == 0) {
                throw new DAOException("Échec de l'envoi de la reclamation.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

        return statu;

    }

    @Override
    public List<Absence> get_etat_exclusion(int id) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Absence> absences = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_EXCLUSION, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setCode_midule(resultSet.getString(1));
                absence.setType_seance(resultSet.getString(2));
                absence.setStatu_justifier(resultSet.getBoolean(3));
                absence.setNbr_absence(resultSet.getInt(4));
                absence.setNbr_absence_just(resultSet.getInt(5));

                absences.add(absence);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absences;

    }

    @Override
    public List<Etudiant> getAbsents(Integer idSeance, Integer idDate) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Etudiant> absents = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_GET_ABSENT, idSeance, idDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Etudiant absent = new Etudiant();
                absent.setIdEtudiant(resultSet.getInt(1));
                absent.setId_Compte(resultSet.getInt(2));// idCOmcpte ici represente idAbsence

                absents.add(absent);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absents;
    }

    @Override
    public void supprimer(Integer idAbsence) {
        Connection connexion = null;
        PreparedStatement preaperedStatment = null;

        try {
            connexion = daoFactory.getConnection();
            preaperedStatment = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_SUPPRIMER_ABSENCES,
                    idAbsence);

            int statu = preaperedStatment.executeUpdate();

            if (statu == 0) {
                throw new DAOException("Échec de la justification de l'absence, aucun champ modifié dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preaperedStatment, connexion);
        }

    }

    @Override
    public Absence getNbrAbsenceBySpec(String specialite) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int nbrAbsents = 0;
        Absence absence = new Absence();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_GET_NBR_ABSENT_JOUR, specialite);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                absence.setNbr_absence(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absence;
    }

    @Override
    public List getNbrAbsencceSemaineByDate(String specialite) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Absence> absences = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_ABSENCE_SEMAINE_DATE, specialite);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setDate_absence(resultSet.getString(1));
                absence.setNbr_absence(resultSet.getInt(2));
                absence.setCodeSpecialite(resultSet.getString(3));

                absences.add(absence);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absences;
    }

    @Override
    public List getExclusModule(String specialite) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Absence> absences = new ArrayList();
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, REQ_EXCLUS_MODULE, specialite);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setNum_carte(resultSet.getString(1));
                absence.setNom(resultSet.getString(2));
                absence.setPrenom(resultSet.getString(3));
                absence.setAnneeSpecilaite(resultSet.getString(4));
                absence.setCodeSpecialite(resultSet.getString(5));
                absence.setCode_midule(resultSet.getString(6));
                absence.setType_seance(resultSet.getString(7));
                absence.setNbr_absence(resultSet.getInt(9));
                absence.setNbr_absence_just(resultSet.getInt(10));

                absences.add(absence);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return absences;
    }

}
