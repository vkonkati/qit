package qit.jaxrs;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("api")
public class QitApplication extends ResourceConfig {
	
	Logger log = LoggerFactory.getLogger(QitApplication.class);

	public QitApplication() {
		super();
		register(JsonProcessingFeature.class);
		packages("qit.jaxrs");
		property(JsonGenerator.PRETTY_PRINTING, true);
		log.info("JAX-RS application initialized as /api");
	}

}
