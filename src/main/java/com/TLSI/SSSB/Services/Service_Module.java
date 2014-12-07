/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.Beans.Module;
import com.TLSI.SSSB.DAO.DAOFactory;
import com.TLSI.SSSB.DAO.ModuleDAO;
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
public class Service_Module {
      ModuleDAO moduleDao;
   DAOFactory daoFactory;
   
    @GET
    @Path("/getModulesJSON/{codeSpecialite}/{anneeSpecialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getModulesJSON(@PathParam("codeSpecialite")String codeSpecialite,@PathParam("anneeSpecialite")String anneeSpecialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        moduleDao = daoFactory.getModuleDAO();
    
        
        return moduleDao.get_Modules(codeSpecialite,anneeSpecialite);
    }
    
    @GET
    @Path("/getModulesJSON/{codeSpecialite}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getModulesBySpec(@PathParam("codeSpecialite")String codeSpecialite) {
        
        this.daoFactory = DAOFactory.getInstance();
        moduleDao = daoFactory.getModuleDAO();
    
        
        return moduleDao.getModuleBysSpecialite(codeSpecialite);
    }
    
      @POST
@Path("/ajouterModule")
@Consumes(MediaType.APPLICATION_JSON) 
      
public void  ajouterModule(@QueryParam("code_module")String code_module,@QueryParam("nom_module")String nom_module,@QueryParam("nbrCredit")Integer nbrCredit,
@QueryParam("semestre")String semestre,@QueryParam("idSpecialite")Integer idSpecialite) {
   
        this.daoFactory = DAOFactory.getInstance();
        moduleDao = daoFactory.getModuleDAO();
       
        //return  moduleDao.ajouterModule("GPL", "Gestion Projet Logiciel", 5, "S1", 1);
        //return 
        moduleDao.ajouterModule(code_module, nom_module, nbrCredit, semestre, idSpecialite);

}

@PUT
@Path("/modifierModule")
@Consumes(MediaType.APPLICATION_JSON)

public void  modifierModule(@QueryParam("id_Module")Integer id_Module,@QueryParam("code_module")String code_module,@QueryParam("nom_module")String nom_module,@QueryParam("nbrCredit")Integer nbrCredit,
@QueryParam("semestre")String semestre,@QueryParam("idSpecialite")Integer idSpecialite) {
   
        this.daoFactory = DAOFactory.getInstance();
        moduleDao = daoFactory.getModuleDAO();
       
        // moduleDao.modifierModule(4, "TEXP", "Technique d'expression", 2, "S1", 1);
        moduleDao.modifierModule(id_Module, code_module, nom_module, nbrCredit, semestre, idSpecialite);
          
}
}