/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;


import com.TLSI.SSSB.Beans.Section;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.SectionDAO;
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
public class Service_Section {
      SectionDAO sectionDao;
      DAOFactory daoFactory;
      
      @GET
    @Path("/getSectionJSON/{specialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Section>  getSectionsBySpec(@PathParam("specialite")String specialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        sectionDao = daoFactory.getSectionDAO();
        return sectionDao.getSectionBySpec(specialite);
                
    }
    
      @POST
@Path("/ajouterSection")
@Consumes(MediaType.APPLICATION_JSON)

public void  ajouterSections(@QueryParam("codeSection")String codeSection,@QueryParam("nomSection")String nomSection,@QueryParam("idSpecialite")int idSpecialite) {
   
        this.daoFactory = DAOFactory.getInstance();
        sectionDao = daoFactory.getSectionDAO();
       sectionDao.ajouterSection(codeSection, nomSection, idSpecialite);
        
        //return  sectionDao.ajouterSection("S2", "Section02", 1);
}
@PUT
@Path("/modifierSection")
@Consumes(MediaType.APPLICATION_JSON)

public void  modifierSection(@QueryParam("idSection")int idSection,@QueryParam("codeSection")String codeSection,@QueryParam("nomSection")String nomSection,@QueryParam("idSpecialite")int idSpecialite) {
   
        this.daoFactory = DAOFactory.getInstance();
        sectionDao = daoFactory.getSectionDAO();
       
        
         sectionDao.modifierModule(idSection, codeSection, nomSection, idSpecialite);
           //sectionDao.modifierModule(3, "S03", "Section03", 1);
}

}
