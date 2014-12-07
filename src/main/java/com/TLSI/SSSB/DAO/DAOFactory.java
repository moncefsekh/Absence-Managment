/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.TLSI.SSSB.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Islam
 */
public class DAOFactory {
    
    private static final String FICHIER_PROPERTIES       = "/com/TLSI/SSSB/DAO/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";
    
    
     private String              url;
    private String              username;
    private String              password;
    
    
    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    
    public static DAOFactory getInstance() throws DAOConfigurationException {
        /*
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }
        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }
        */
    
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }
        DAOFactory instance = new DAOFactory( "jdbc:mysql://192.168.1.4:3306/gsa", "PC", ""  );
        return instance;
    }
    /* Méthode chargée de fournir une connexion à la base de données */
     /* package */ 
    
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(  "jdbc:mysql://192.168.1.4:3306/gsa", "PC", ""  );
    }
    /*
     * Méthodes de récupération de l'implémentation des différents DAO 
     */
    
    public AbsenceDAO getAbsenceDAO() {
        return new AbsenceDAOImpl( this );
    }
    public ActualiteDAO getActualiteDAO(){
        return new ActualiteDAOImpl(this); 
    }
    public ModuleDAO getModuleDAO() {
        return new ModuleDAOImpl( this );
    }
    public EtudiantDAO getEtudiantDAO() {
        return new EtudiantDAOImpl( this );
    }
    public SeanceDAO getSeanceDAO() {
        return new SeanceDAOImpl( this );
    }
    
     public EnseignantDAO getEnseignantDAO() {
        return new EnseignantDAOImpl( this );
    }
     
       public GroupeDAO getGroupeDAO() {
        return new GRoupeDAOImpl( this );
    }
        public SectionDAO getSectionDAO() {
        return new SectionDAOImpl( this );
    }
        
          public ParametresDAO getParametresDAO() {
        return new ParametresDAOImpl(this );
    }
          
             public ResponsableDAO getResponsableDAO() {
        return new ResponsableDAOImpl(this );
    }
   public SpecialiteDAO getSpecialiteDAO() {
        return new SpecialiteDAOImpl( this );
    }
    public AgentDAO getAgentDAO() {
        return new AgentDAOImpl( this );
    }
   
}
