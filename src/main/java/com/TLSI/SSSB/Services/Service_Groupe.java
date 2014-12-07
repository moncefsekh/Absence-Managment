/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Groupe;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.GroupeDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Islam
 */

@Path("service")
public class Service_Groupe {
    GroupeDAO groupeDao;
   DAOFactory daoFactory;
   
   
    @GET
    @Path("/getGroupesJSON/{specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Groupe>  getGroupesBySpec(@PathParam("specialite")String specialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        groupeDao = daoFactory.getGroupeDAO();
        return groupeDao.getGroupeByAnnee(specialite);
                
    }
    
    @POST
@Path("/ajouterGroupe")
@Consumes(MediaType.APPLICATION_JSON)

public void  ajouterGroupe(@QueryParam("codeGroupe")String codeGroupe,@QueryParam("nomGroupe")String nomGroupe,@QueryParam("idSection")int idSection) {
   
        this.daoFactory = DAOFactory.getInstance();
        groupeDao = daoFactory.getGroupeDAO();
       
      groupeDao.ajouterGroupe(codeGroupe, nomGroupe, idSection);
        //return  groupeDao.ajouterGroupe("G04", "Groupe04", 2);
}

@PUT
@Path("/modifierGroupe")
@Consumes(MediaType.APPLICATION_JSON)

public void  modifierGroupe(@QueryParam("id_Groupe")int id_Groupe,@QueryParam("codeGroupe")String codeGroupe,@QueryParam("nomGroupe")String nomGroupe,@QueryParam("idSection")int idSection) {
   
        this.daoFactory = DAOFactory.getInstance();
        groupeDao = daoFactory.getGroupeDAO();
       
      //  groupeDao.modfifierGroupe(2, "G03", "Groupe03", 2);
          groupeDao.modfifierGroupe(id_Groupe, codeGroupe, nomGroupe, idSection);
          
}
}
