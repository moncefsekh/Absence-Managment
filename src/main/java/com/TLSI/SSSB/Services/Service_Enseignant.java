/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Compte_Utilisateur;
import com.TLSI.SSSB.Beans.Enseignant;
import com.TLSI.SSSB.Beans.Module;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.EnseignantDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Islam
 */
@Path("service")
public class Service_Enseignant {
     
   EnseignantDAO enseignantDao ;
   HttpSession session ;
   DAOFactory daoFactory;
   
   
    @GET
    @Path("/getEnseignantActuel")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Enseignant> getEnseignantActuel (){
     this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
        
        
       return enseignantDao.getEnseignantActuel();
        
}
     @GET
    @Path("/getModulesEnseignant/{idEnseignant}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Module> getMoodulesEnseignant (@PathParam("idEnseignant")Integer idEnseignant){
     this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
        
        
       return enseignantDao.getModuleEnseignant(idEnseignant);
        
}
   
   @GET
    @Path("/getAllEnseignantJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Enseignant>  getAllEnseignantJSON() {
        
        this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
    
        
        return enseignantDao.getAllEnseignant();
    }
    
    @GET
    @Path("/getEnseignantJSON/{specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Enseignant>  getAllEnseignantJSON(@PathParam("specialite") String specialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
    
        
        return enseignantDao.getEnseignant(specialite);
    }
    
@POST
@Path("/ajouterEnseignant")
@Consumes(MediaType.APPLICATION_JSON)

public void  ajouterEnseignant(@QueryParam("nom")String nom,@QueryParam("prenom") String prenom,@QueryParam("dateNaissance") String dateNaissance,@QueryParam("grade") String grade) {
   
    
    
        this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
       enseignantDao.ajouterEnseignant(nom, prenom, dateNaissance, grade);
     // return enseignantDao.ajouterEnseignant("SAIDO", "Kheiro", "2014-05-16", "Docteur");
        //absenceDao.marquer(1,"2014-05-01 10:00",1);
        
}

@PUT
@Path("/modifierEnseignant")
@Consumes(MediaType.APPLICATION_JSON)

public void  modifierEnseignant(@QueryParam("idEnseignant")int idEnseignant,@QueryParam("nom")String nom,@QueryParam("prenom")String prenom,@QueryParam("dateNaissance")String dateNaissance,@QueryParam("grade")String grade) {
   
        this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
       
        
         enseignantDao.modifierEnseignant(idEnseignant, nom, prenom, dateNaissance, grade);
          
}
@POST
@Path("/envoyerEtatAvancement")
@Consumes(MediaType.APPLICATION_JSON)

public int  envoyerEtatAvancement(@QueryParam("etatAvancement")String etatAvancement,@QueryParam("idSeance") int idSeance,@QueryParam("idDates") int idDates) {
   
    
    
        this.daoFactory = DAOFactory.getInstance();   
        enseignantDao = daoFactory.getEnseignantDAO();
        //enseignantDao.ajouterEnseignant(nom, prenom, dateNaissance, grade, idCompte);
      return enseignantDao.envoyerEtatAvancement(etatAvancement,idSeance,idDates);
        //absenceDao.marquer(1,"2014-05-01 10:00",1);
        
}
  @Context private HttpServletRequest request;
@POST
@Path("/authEnseignant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Enseignant  authEnseignant(@QueryParam("nomUtilisateur")String nomUtilisateur,@QueryParam("motDePasse") String motDePasse) {
   
        this.daoFactory = DAOFactory.getInstance();
        enseignantDao = daoFactory.getEnseignantDAO();
        session = request.getSession(true);
        Enseignant enseignant = enseignantDao.authEnseignant(nomUtilisateur, motDePasse);
        if(enseignant != null){
           session.setAttribute("Enseignant", enseignant);
        }
        else{
            session.setAttribute("Enseignant", null);
        }
       return enseignant;
    
        
}

    @GET
    @Path("/getInfoEnseignant")
    @Produces(MediaType.APPLICATION_JSON)
    public Enseignant  getInfoEnseignant() {
        session =  request.getSession(true);
        Enseignant enseignant = null;
        
        if(session.getAttribute("Enseignant")!= null){
            enseignant = (Enseignant) session.getAttribute("Enseignant"); 
        }
        return enseignant; 
    }

    
    @GET
    @Path("/getSession")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte_Utilisateur getSession(){
        Compte_Utilisateur cu = new Compte_Utilisateur();
         session = request.getSession(true);
        
        cu.setId_Compte((int)session.getAttribute("Enseignant"));
        return cu;
    }
    
    @POST
    @Path("/logoutEnseignant")
    public void logoutEnseignant() {
        session = request.getSession(true);
        if(session != null)
            session.invalidate();
    }
}
