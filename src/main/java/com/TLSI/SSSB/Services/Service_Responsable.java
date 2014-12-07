/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Compte_Utilisateur;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.ResponsableDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Islam
 */
@Path("service")
public class Service_Responsable {

    ResponsableDAO responsableDao;
    HttpSession session = null;
    DAOFactory daoFactory;

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/authResponsable")
    @Consumes(MediaType.APPLICATION_JSON)
    public void authResponsable(@QueryParam("nomUtilisateur") String nomUtilisateur, @QueryParam("motDePasse") String motDePasse) {

        this.daoFactory = DAOFactory.getInstance();
        responsableDao = daoFactory.getResponsableDAO();
        session = request.getSession(true);
        if (responsableDao.authResponsable(nomUtilisateur, motDePasse)) {
            session.setAttribute("Responsable",true);
        }else{
            session.setAttribute("Responsable",false);
        }

    }

    @GET
    @Path("/getInfoResponsable")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte_Utilisateur getInfoResponsable() {
        Compte_Utilisateur cu = new Compte_Utilisateur();
        session = request.getSession(true);

        if (session.getAttribute("Responsable") != null && (boolean)session.getAttribute("Responsable")==true) {
            cu.setId_Compte(1);
        }
        return cu;
    }
    
    
    
    @POST
    @Path("/logoutResponsable")
    public void logoutResponsable() {
        session = request.getSession(true);
        if(session != null)
            session.invalidate();
    }

}
