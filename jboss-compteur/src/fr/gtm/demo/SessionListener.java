package fr.gtm.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {
	public static final Logger tchikita = Logger.getLogger("Demo");
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
    	session.setMaxInactiveInterval(1*60);
    	tchikita.info(">>>> Session demarée : "+se.getSession().getId());
			try {
				//InitialContext => contexte JNDI du serveur
				InitialContext ctx = new InitialContext();
				//recherche de l'EJB par le nom donné par le serveur
				//=> déclenche la construction de l'objet
				Compteur compteur = (Compteur) ctx.lookup("java:app/jboss-compteur/Compteur");
				session.setAttribute("compteur", compteur);
			} catch (NamingException e) {
				tchikita.log(Level.SEVERE, "Erreur de création de session",e);
			}
		}

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
        Compteur compteur = (Compteur) se.getSession().getAttribute("compteur");
        		if(compteur != null) {
        			compteur.remove();
        			tchikita.info(">>>> Session terminée : "+se.getSession().getId());
        		}
    }
	
}
