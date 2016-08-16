package elias.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import elias.rest.annotations.DBQ;
import elias.rest.annotations.DBQJTA;
import elias.rest.annotations.FileQ;

@Path("/")
public class UrlShortenerRest {

	@Inject
	@DBQJTA
	ResourceManager rm;

	@GET
	@Path("/save/{url}")
	public Response addUrl(@PathParam("url") String url) {
		String hash = String.valueOf(url.hashCode());
		try{
		rm.addURL(hash, url);
		}catch(Exception e){
			Throwable cause=e;
			while(cause.getCause()!=null){
				cause=cause.getCause();
			}
			return Response.status(200).entity(cause.getMessage()).header("Access-Control-Allow-Origin", "*").build();
		}
		return Response.status(200).entity("Saved. Your Hash: " + hash).header("Access-Control-Allow-Origin", "*")
				.build();

	}

	@GET
	@Path("/get/{hash}")
	public Response getUrl(@PathParam("hash") String hash)
			throws JsonGenerationException, JsonMappingException, IOException {

		ShortURL u = rm.getUrl(hash);
		if (u != null) {
			return Response.status(200).entity(u.toString()).header("Access-Control-Allow-Origin", "*").build();

		} else {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Unknown hash").build();
		}

	}

	@GET
	@Path("/get")
	public Response getAll() throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper m = new ObjectMapper();
		// ShortURL r=new ShortURL("www.seznam.cz", "1564654", new Date(2016,
		// 11, 22,5,22,35));
		String json = m.writerWithDefaultPrettyPrinter().writeValueAsString(rm.getAll());
		return Response.status(200).entity(json).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/redirect/{hash}")
	public Response redirect(@PathParam("hash") String hash) throws URISyntaxException {
		if (rm.getUrl(hash) != null) {
			String url = rm.getUrl(hash).getUrl();
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				url = "http://" + url;
			}
			return Response.seeOther(new URI(url)).build();
		} else {
			return Response.status(404).entity("Hash not found").build();
		}
	}

}