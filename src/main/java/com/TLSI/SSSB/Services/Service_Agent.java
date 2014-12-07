/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Agent;
import com.TLSI.SSSB.Beans.Etudiant;
import com.TLSI.SSSB.DAO.AgentDAO;
import com.TLSI.SSSB.DAO.DAOFactory;
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
public class Service_Agent {
     AgentDAO agentDao ;
     DAOFactory daoFactory;
     
     @GET
    @Path("/getAgents")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Agent>  getAgents() {
        
        this.daoFactory = DAOFactory.getInstance();
        agentDao = daoFactory.getAgentDAO();
    
        
        return agentDao.getAgent();
    }
    
      
@POST
@Path("/ajouterAgent")
@Consumes(MediaType.APPLICATION_JSON)

public void  ajouterAgent(@QueryParam("nom")String nom,@QueryParam("prenom")String prenom) {
   
       this.daoFactory = DAOFactory.getInstance();
       agentDao = daoFactory.getAgentDAO();
       
       agentDao.ajouterAgent(nom, prenom);
}


@PUT
@Path("/modifierAgent")
@Consumes(MediaType.APPLICATION_JSON)

public void  modifierAgent(@QueryParam("idAgent")int idAgent,@QueryParam("nom")String nom,@QueryParam("prenom")String prenom) {
   
        this.daoFactory = DAOFactory.getInstance();
        agentDao = daoFactory.getAgentDAO();
        agentDao.modifierAgent(idAgent, nom, prenom);
          
}

}
