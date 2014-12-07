/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Absence;
import com.TLSI.SSSB.Beans.Detail_Absence;
import com.TLSI.SSSB.Beans.Etudiant;
import com.TLSI.SSSB.Beans.Specialite;
import com.TLSI.SSSB.DAO.AbsenceDAO;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.EtudiantDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class Service_Absence {
    AbsenceDAO absenceDao;
    EtudiantDAO etudiantDao ;
    DAOFactory daoFactory;
     Specialite s = new Specialite();
    
    @GET
    @Path("/getAbsenceSemaine/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Absence>  getAbsenceSemaine(@PathParam("id") int id) {
        
        this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
     
        
        return absenceDao.getAbsenceWeek(id);
          
    }
    
     @GET
    @Path("/getAbsencesJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Absence>  getAllAbsencesJSON(@PathParam("id") int id) {
        
        this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
     
        
        return absenceDao.getAbsence(id);
          
    }
    
     @GET
    @Path("/getDetailAbsenceJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Detail_Absence>  getAllEtudiantJSON(@PathParam("id") int id) {
        
        this.daoFactory = DAOFactory.getInstance();
        etudiantDao = daoFactory.getEtudiantDAO();
      // return new ArrayList<Person>(persons.values());
        s.setCode_specialite ("GL");
        
        return etudiantDao.detailAbsenceEtudiant(id);
    }
    
      @GET
    @Path("/getEtatExclusion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Absence>  getEtatExclusion(@PathParam("id") int id) {
        
        this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
      // return new ArrayList<Person>(persons.values());
        s.setCode_specialite ("GL");
        
        return absenceDao.get_etat_exclusion(id);
    }
    
@POST
@Path("/postAbsences")
@Consumes(MediaType.APPLICATION_JSON)

public void  sendAbsences(@QueryParam("idSeance")Integer idSeance ,@QueryParam("dateSeance")String date_absence,@QueryParam("ids")String etudiants,@QueryParam("idDate")Integer idDate ) {
   
    
    
        this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
        
        List <Etudiant> idAbsences = absenceDao.getAbsents(idSeance, idDate);
        
        for(int j=0;j<idAbsences.size();j++){
            absenceDao.supprimer(idAbsences.get(j).getId_Compte());
        }
    
        for (String split : etudiants.split("_")) {
            absenceDao.marquer(idSeance, date_absence, Integer.valueOf(split));
        }
        //absenceDao.marquer(1,"2014-05-01 10:00",1);
        
}

    @GET
    @Path("/getAbsents/{idSeance}/{idDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Etudiant> getAbsents (@PathParam("idSeance") Integer idSeance , @PathParam("idDate")Integer idDate){
     this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
       return absenceDao.getAbsents(idSeance, idDate);
        
}

  @GET
    @Path("/getNbrAbsents/{specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public Absence getNbrAbsents(@PathParam("specialite") String specialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
      // return new ArrayList<Person>(persons.values());
        s.setCode_specialite ("GL");
        
        return absenceDao.getNbrAbsenceBySpec(specialite);
    }
    
    
    @GET
    @Path("/getNbrAbsencesSemaine/{codeSpecialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Absence> getNbrAbsencesSemaine (@PathParam("codeSpecialite") String codeSpecialite ){
     this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
       return absenceDao.getNbrAbsencceSemaineByDate(codeSpecialite);
        
}
 @GET
    @Path("/getExclusModule/{codeSpecialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Absence> getExclusModule (@PathParam("codeSpecialite") String codeSpecialite ){
     this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
       return absenceDao.getExclusModule(codeSpecialite);
        
}

}
