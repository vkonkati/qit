package qit.jpa;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.JNDIConnector;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.server.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QitSessionCustomizer implements SessionCustomizer {

	Logger log = LoggerFactory.getLogger(QitSessionCustomizer.class);

	@Override
	public void customize(Session session) throws Exception {
		try {
			// connector
	    	Context ctx = new InitialContext();
	    	if (ctx != null) {
	    		// JTA connector customization
	    		// only non-jta connector for QIT
	    		//JNDIConnector jta = (JNDIConnector)session.getLogin().getConnector();
	    		//jta.setLookupType(JNDIConnector.STRING_LOOKUP);
	    		
	    		// Non JTA connector customization
	    		JNDIConnector nonJTA = (JNDIConnector)((DatabaseLogin)((ServerSession)session).getReadConnectionPool().getLogin()).getConnector();
	    		nonJTA.setLookupType(JNDIConnector.STRING_LOOKUP);
	    		log.debug("Non JTA Connector custom session configured:{}", nonJTA.getName());
		    }
	    	// logging
	    	SessionLog sessionLog = new QitSessionLog();
	    	if(log.isDebugEnabled()) {
	    		sessionLog.setLevel(SessionLog.FINE);
	    	} else if(log.isErrorEnabled()) {
	    		sessionLog.setLevel(SessionLog.SEVERE);
	    	} else if(log.isWarnEnabled()) {
	    		sessionLog.setLevel(SessionLog.WARNING);
	    	} else if(log.isInfoEnabled()) {
	    		sessionLog.setLevel(SessionLog.INFO);
	    	} else if(log.isTraceEnabled()) {
	    		sessionLog.setLevel(SessionLog.FINEST);
	    	} else {
	    		sessionLog.setLevel(SessionLog.INFO);
	    	}
	    	session.setSessionLog(sessionLog);
	    } catch (Exception ex) {
	    	log.error("Caught Exception in EclipseLink Session Customizer", ex);
	    	throw ex;
	    }
	}

}
