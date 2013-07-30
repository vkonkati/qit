package qit.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QitContextListener implements ServletContextListener {
	
	private Logger log = LoggerFactory.getLogger(QitContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log.info("### context destroyed ###");

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("### context initialized ###");

	}

}
