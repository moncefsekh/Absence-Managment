/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.Services;

import com.TLSI.SSSB.DAO.AbsenceDAO;
import com.TLSI.SSSB.DAO.DAOFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Islam
 */
@Path("service")
public class Service_reclamation {
     AbsenceDAO absenceDao;
      DAOFactory daoFactory;
    
@POST
@Path("/postReclamation")
@Consumes(MediaType.APPLICATION_JSON)

public void  savePerson(@QueryParam("reclamation")String reclamation) {
   
        this.daoFactory = DAOFactory.getInstance();
        absenceDao = daoFactory.getAbsenceDAO();
       
        
         absenceDao.send_reclamation(reclamation);
}
}
