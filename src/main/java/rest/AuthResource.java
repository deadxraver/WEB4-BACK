package rest;

import db.PasswordHasher3000;
import datamodels.User;
import db.UserAdder800;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/auth")
public class AuthResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register")
	public Response register(@QueryParam("login") String login, @QueryParam("password") String password) {
		System.out.println("adding new user with login: " + login);
		User user = new User();
		user.setLogin(login);
		user.setHashedPassword(PasswordHasher3000.hash(password));
		user.setDots(new ArrayList<>());
		if (UserAdder800.findByUsername(login) != null) {
			System.err.println("user " + login + " already exists");
			return Response.status(Response.Status.CONFLICT).entity("Username already exists").build();
		}
		UserAdder800.saveUser(user);
		System.out.println("saved user: " + user);
		return Response.status(Response.Status.OK).entity("Registered").build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
		System.out.println("login request from user " + login);
		User user = UserAdder800.findByUsername(login);
		if (user == null) {
			System.err.println("user " + login + " not found");
			return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").build();
		} else if (!PasswordHasher3000.verify(password, user.getHashedPassword())) {
			System.err.println("wrong password for user " + login);
			return Response.status(Response.Status.FORBIDDEN).entity("Wrong password").build();
		}
		System.out.println("user " + login + " logged in");
		return Response.status(Response.Status.OK).entity(user.getDotsAsJSON()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remove")
	public Response remove(@QueryParam("login") String login, @QueryParam("password") String password) {
		User user = UserAdder800.findByUsername(login);
		if (user == null || !PasswordHasher3000.verify(password, user.getHashedPassword())) {
			System.err.println("user " + login + " not found or password is incorrect");
			return Response.status(Response.Status.FORBIDDEN).entity("User not found").build();
		}
		UserAdder800.deleteUser(user);
		System.out.println("user " + login + " deleted");
		return Response.status(Response.Status.OK).build();
	}
}