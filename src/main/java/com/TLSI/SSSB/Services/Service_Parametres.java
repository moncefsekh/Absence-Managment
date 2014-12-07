/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Parametres;

import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.ParametresDAO;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Islam
 */

@Path("service")
public class Service_Parametres {
    
     ParametresDAO parametresDao ;
    DAOFactory daoFactory;
    
     
     @GET
    @Path("/getParametres") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Parametres getParametres() {
         
        
        this.daoFactory = DAOFactory.getInstance();
        parametresDao = daoFactory.getParametresDAO();
        
        
     
     
        
        return parametresDao.getParametres();
    }
    
    @PUT
@Path("/modifierParametres")
@Consumes(MediaType.APPLICATION_JSON)

public void modifierParametres(@QueryParam("nbrAbsence")int nbrAbsence,@QueryParam("nbrJustifier")int nbrJustifier,@QueryParam("dateDebutSemestre1")String dateDebutSemestre1,@QueryParam("dateDebutSemestre2")String dateDebutSemestre2,@QueryParam("dateFinSemestre1")String dateFinSemestre1,@QueryParam("dateFinSemestre2")String dateFinSemestre2) {
   
        this.daoFactory = DAOFactory.getInstance();
        parametresDao = daoFactory.getParametresDAO();
        
         //seanceDao.modifierSeance(13, 14, "TD", 10, "2014-06-11 10:00:00", 1);
         parametresDao.modifierParametres(nbrAbsence, nbrJustifier, dateDebutSemestre1, dateDebutSemestre2, dateFinSemestre1, dateFinSemestre2);
           
}
    
    
}
