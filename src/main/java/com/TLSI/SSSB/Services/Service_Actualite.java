/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Actualite;
import com.TLSI.SSSB.DAO.ActualiteDAO;
import com.TLSI.SSSB.DAO.DAOFactory;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Islam
 */
@Path("service")
public class Service_Actualite {
       ActualiteDAO actualiteDao;
   DAOFactory daoFactory;
   
    @GET
    @Path("/getActualiteJour/{code_specialite}/{annee_specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Actualite>  getActualiteJour(@PathParam("code_specialite") String code_specialite,@PathParam("annee_specialite") String annee_specialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        actualiteDao = daoFactory.getActualiteDAO();
     
        
        return actualiteDao.getActualiteJour(code_specialite, annee_specialite);
          
    }
    
    @GET
    @Path("/getActualiteSemaine/{code_specialite}/{annee_specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Actualite>  getActualiteSemaine(@PathParam("code_specialite") String code_specialite,@PathParam("annee_specialite") String anneeSpecialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        actualiteDao = daoFactory.getActualiteDAO();
     
        
        return actualiteDao.getActualiteSemaine(code_specialite, anneeSpecialite);
          
    }
}
