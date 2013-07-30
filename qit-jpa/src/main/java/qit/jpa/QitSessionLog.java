package qit.jpa;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QitSessionLog extends AbstractSessionLog implements SessionLog {

	private Logger log = LoggerFactory.getLogger(QitSessionLog.class);

	@Override
	public void log(SessionLogEntry sessionLogEntry) {
		//sessionLogEntry
		String msg = sessionLogEntry.getMessage();
		switch(sessionLogEntry.getLevel()) {
		case SessionLog.ALL:
			log.trace(msg);
			break;
		case SessionLog.OFF:
			//log.info(msg);
			break;
		case SessionLog.FINE:
		case SessionLog.FINER:
		case SessionLog.FINEST:
			log.debug(msg);
			break;
		case SessionLog.INFO:
		case SessionLog.CONFIG:
			log.info(msg);
			break;
		case SessionLog.SEVERE:
			log.error(msg);
			break;
		case SessionLog.WARNING:
			log.warn(msg);
			break;
		}
	}

}
