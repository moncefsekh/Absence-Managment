/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Seance;
import com.TLSI.SSSB.Beans.Specialite;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.SpecialiteDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class Service_Specialite {
    SpecialiteDAO specialiteDao;
   DAOFactory daoFactory;
    
    
     @POST
@Path("/ajouterSpecialite")
@Consumes(MediaType.APPLICATION_JSON)

public void  ajouterSpecialite(@QueryParam("codeSpecialite")String codeSpecialite,@QueryParam("nomSpecialite")String nomSpecialite) {
   
        this.daoFactory = DAOFactory.getInstance();
        specialiteDao = daoFactory.getSpecialiteDAO();
       
        
       specialiteDao.ajouterSpecialite(codeSpecialite, nomSpecialite);
       // return  specialiteDao.ajouterSpecialite("SITW", "System information", "L2");
}

@PUT
@Path("/modifierSpecialite")
@Consumes(MediaType.APPLICATION_JSON)

public void  modifierSpecialite(@QueryParam("id_Specialite")Integer id_Specialite,@QueryParam("codeSpecialite")String codeSpecialite,@QueryParam("nomSpecialite")String nomSpecialite,@QueryParam("anneeSpecialite")String anneeSpecialite) {
   
        this.daoFactory = DAOFactory.getInstance();
        specialiteDao = daoFactory.getSpecialiteDAO();
       
        
        specialiteDao.modifierSpecialite(id_Specialite, codeSpecialite, nomSpecialite, nomSpecialite);
           //specialiteDao.modifierSpecialite(3, "SITW", "Systeme d'information", "L3");
          
}


       @GET
    @Path("/getSpecialiteJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Specialite> getSpecialite() {
         
        
        this.daoFactory = DAOFactory.getInstance();
        specialiteDao = daoFactory.getSpecialiteDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return specialiteDao.getSpecialite();
    }
    
     @GET
    @Path("/getAnneeJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Specialite>  getAnnee()  {
         
        
        this.daoFactory = DAOFactory.getInstance();
        specialiteDao = daoFactory.getSpecialiteDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return specialiteDao.getAnnee()
                ;
    }
}
