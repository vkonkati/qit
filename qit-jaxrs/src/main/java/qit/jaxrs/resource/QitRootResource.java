package qit.jaxrs.resource;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("")
public class QitRootResource {
	
	Logger log = LoggerFactory.getLogger(QitRootResource.class);
			
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public JsonObject get(@Context UriInfo uriInfo) {
		log.info("GET resources called");
		JsonObjectBuilder json = Json.createObjectBuilder();
		JsonArrayBuilder resources = Json.createArrayBuilder();
		resources.add(uriInfo.getBaseUriBuilder().path(QitVersionResource.class).build().toASCIIString());
		json.add("resources", resources);
		return json.build();
	}
}
