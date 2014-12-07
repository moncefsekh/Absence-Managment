/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Seance;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.SeanceDAO;
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
public class Service_Seance {
   SeanceDAO seanceDao ;
    DAOFactory daoFactory;
    
     
     @GET
    @Path("/getseanceJson/{id}") 
    @Produces(MediaType.APPLICATION_JSON)
    public List <Seance> getSeanceJSON(@PathParam("id") int id) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
     
     
        
        return seanceDao.getSeanceJour(id);
    }
    
      @GET
    @Path("/getseanceSemaineJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seance>  getSeanceSemaineJSON(@PathParam("id") int id) {
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.getSeanceSemain(id);
    }
    
      @GET
    @Path("/getAllseanceJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seance>  getAllSeanceJSON(@PathParam("id") int id) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.getAllSeance(id);
    }
    
     @GET
    @Path("/getDatesSeance/{idSeance}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seance  getSeanceById(@PathParam("idSeance") int idSeance) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.getDatesSeance(idSeance);
    }
       @GET
    @Path("/getSpecAnnSec/{idEnseignant}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Seance>  getSpecAnnSec(@PathParam("idEnseignant") int idEnseignant) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.get_spec_ann_sec(idEnseignant);
    }
       @GET
    @Path("/getGroupeMenu/{idEnseignant}/{codeSpecialite}/{annee}/{section}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Seance>  getGroupeMenu(@PathParam("idEnseignant") int idEnseignant,@PathParam("codeSpecialite") String codeSpecialite,@PathParam("annee") String annee,@PathParam("section") String section) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.getGroupeMenu(idEnseignant, codeSpecialite, annee, section);
    }
     @GET
    @Path("/getModuleMenu/{idEnseignant}/{codeSpecialite}/{annee}/{section}/{groupe}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seance>  getModuleMenu(@PathParam("idEnseignant") int idEnseignant,@PathParam("codeSpecialite") String codeSpecialite,@PathParam("annee") String annee,@PathParam("section") String section,@PathParam("groupe") String groupe) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.getModuleMenu(idEnseignant, codeSpecialite, annee, section, groupe);
    }
    
       @GET
    @Path("/getSeancesJson/{specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seance>  getAllSeanceJSON(@PathParam("specialite") String specialite) {
         
        
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
      // return new ArrayList<Person>(persons.values());
     
        
        return seanceDao.getALlSeanceResp(specialite);
    }
    
@POST
@Path("/ajouterSeance")
@Consumes(MediaType.APPLICATION_JSON)

public void ajouterSeance(@QueryParam("idModule")int idModule,@QueryParam("type") String type ,@QueryParam("idEnseignant")int idEnseignant,@QueryParam("dateSeance") String dateSeance,@QueryParam("heureSeance") String heureSeance) {
   
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();   
      //  seanceDao.ajouterSeance(14, "TD", 10, "2014-06-10 10:00:00"); 
         seanceDao.ajouterSeance(idModule, type, idEnseignant, dateSeance+" "+heureSeance+":00");
        
       
}

@PUT
@Path("/modifierSeance")
@Consumes(MediaType.APPLICATION_JSON)

public void modifierSeance(@QueryParam("idSeance")int idSeance,@QueryParam("idModule")int idModule,@QueryParam("type")String type,@QueryParam("idEnseignant")int idEnseignant,@QueryParam("dateSeance")String dateSeance,@QueryParam("heureSeance")String heureSeance) {
   
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
        
         //seanceDao.modifierSeance(13, 14, "TD", 10, "2014-06-12 10:00:00", 1);
        seanceDao.modifierSeance(idSeance, idModule, type, idEnseignant, dateSeance+" "+heureSeance+":00", 1);
           
}

     @PUT
@Path("/changerSeance")
@Consumes(MediaType.APPLICATION_JSON)

public void  changerSeance(@QueryParam("idDates")int idDates,@QueryParam("dateChangement")String dateChangement,@QueryParam("heureChangement")String heureChangement) {
   
        this.daoFactory = DAOFactory.getInstance();
        seanceDao = daoFactory.getSeanceDAO();
        seanceDao.changerSeance(idDates, dateChangement+" "+heureChangement+":00"); 
        // seanceDao.changerSeance(25, "2014-06-10 10:00:00");
        
        //return  sectionDao.ajouterSection("S2", "Section02", 1);
} 
}
