package rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class LogRegResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register")
	public Response register(@QueryParam("login") String login, @QueryParam("password") String password) {
		System.out.println("register: login=" + login + ", password=" + password);
		return Response.status(Response.Status.OK).entity("Registered").build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
		System.out.println("login: login=" + login + ", password=" + password);
		return Response.status(Response.Status.OK).entity("Logged in").build();
	}
}