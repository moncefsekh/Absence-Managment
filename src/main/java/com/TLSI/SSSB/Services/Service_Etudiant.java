/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Compte_Utilisateur;
import com.TLSI.SSSB.Beans.Etudiant;
import com.TLSI.SSSB.Beans.InfoEtudiant;
import com.TLSI.SSSB.Beans.Specialite;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.EtudiantDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author SSSB
 */
@Path("service")
public class Service_Etudiant {

    EtudiantDAO etudiantDao;
    Specialite s = new Specialite();
    DAOFactory daoFactory;
    HttpSession session;

    @GET
    @Path("/getEtudiantsJSON/{specialite}/{annee}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getAllEtudiantJSON(@PathParam("specialite") String specialite, @PathParam("annee") String annee) {

        this.daoFactory = DAOFactory.getInstance();
        etudiantDao = daoFactory.getEtudiantDAO();

        return etudiantDao.getAllEtudiant(specialite, annee);
    }

    @GET
    @Path("/getInfoEtudiant")
    @Produces(MediaType.APPLICATION_JSON)
    public InfoEtudiant getInfoEtudiant() {
        session = request.getSession(true);
        InfoEtudiant infoEtudiant = null;

        if (session.getAttribute("Etudiant") != null) {
            infoEtudiant = (InfoEtudiant) session.getAttribute("Etudiant");
        }
        return infoEtudiant;
    }

    @GET
    @Path("/getNbrEtudiant/{specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant getNbrEtudiant(@PathParam("specialite") String specialite) {

        this.daoFactory = DAOFactory.getInstance();
        etudiantDao = daoFactory.getEtudiantDAO();
        return etudiantDao.getNbrEtudiant(specialite);

    }

    @GET
    @Path("/getEtudiantGroupe/{spec}/{annee}/{sec}/{gr}/{idseance}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getEtudiantGroupe(@PathParam("spec") String spec, @PathParam("annee") String annee, @PathParam("sec") String sec, @PathParam("gr") String gr, @PathParam("idseance") int idseance) {
        this.daoFactory = DAOFactory.getInstance();
        etudiantDao = daoFactory.getEtudiantDAO();

        return etudiantDao.getEtudiantGroupe(spec, annee, sec, gr, idseance);
    }

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/authEtudiant")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public InfoEtudiant authEtudiant(@QueryParam("nomUtilisateur") String nomUtilisateur, @QueryParam("motDePasse") String motDePasse) {
        this.daoFactory = DAOFactory.getInstance();
        etudiantDao = daoFactory.getEtudiantDAO();
        session = request.getSession(true);
        InfoEtudiant infoEtudiant = etudiantDao.authEtudiant(nomUtilisateur, motDePasse);

        if (infoEtudiant != null) {
            session.setAttribute("Etudiant", infoEtudiant);
        } else {
            session.setAttribute("Etudiant", null);
        }

        return infoEtudiant;

    }

    @GET
    @Path("/getSessionEtudiant")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte_Utilisateur getSession() {
        Compte_Utilisateur cu = new Compte_Utilisateur();
        session = request.getSession(true);

        cu.setId_Compte((int) session.getAttribute("Etudiant"));
        return cu;
    }
    
    
    @POST
    @Path("/logoutEtudiant")
    public void logoutEtudiant() {
        session = request.getSession(true);
        if(session != null)
            session.invalidate();
    }

}
