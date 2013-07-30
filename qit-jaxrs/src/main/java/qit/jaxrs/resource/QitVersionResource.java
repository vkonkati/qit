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

@Path("versions")
public class QitVersionResource {
	
	Logger log = LoggerFactory.getLogger(QitVersionResource.class);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public JsonObject get(@Context UriInfo uriInfo) {
		log.info("GET versions called");
		JsonObjectBuilder json = Json.createObjectBuilder();
		JsonArrayBuilder versions = Json.createArrayBuilder();
		JsonObjectBuilder v1 = Json.createObjectBuilder();
		v1.add("v1", uriInfo.getBaseUriBuilder().path(QitV1Resource.class).build().toASCIIString());
		versions.add(v1);
		json.add("versions", versions);
		json.add("current", uriInfo.getBaseUriBuilder().path(QitV1Resource.class).build().toASCIIString());
		return json.build();
	}
	
	@Path("/current")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public JsonObject current(@Context UriInfo uriInfo) {
		log.info("GET versions/current called");
		JsonObjectBuilder json = Json.createObjectBuilder();
		json.add("current", uriInfo.getBaseUriBuilder().path(QitV1Resource.class).build().toASCIIString());
		return json.build();
	}
}
