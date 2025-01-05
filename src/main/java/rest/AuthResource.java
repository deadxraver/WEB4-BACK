package rest;

import db.PasswordHasher3000;
import db.User;
import db.UserAdder800;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

	UserAdder800 userAdder800 = new UserAdder800();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register")
	public Response register(@QueryParam("login") String login, @QueryParam("password") String password) {
		System.out.println("adding new user with login: " + login);
		User user = new User();
		user.setLogin(login);
		user.setHashedPassword(PasswordHasher3000.hash(login, password));
		if (userAdder800.findByUsername(login) != null) {
			System.err.println("user " + login + " already exists");
			return Response.status(Response.Status.OK).entity("Username already exists").build();
		}
		userAdder800.saveUser(user);
		System.out.println("saved user: " + user);
		return Response.status(Response.Status.OK).entity("Registered").build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@QueryParam("login") String login, @QueryParam("password") String password) {
		System.out.println("login request from user " + login);
		User user = userAdder800.findByUsername(login);
		if (user == null) {
			System.err.println("user " + login + " not found");
			return Response.status(Response.Status.OK).entity("User not found").build();
		} else if (!user.getHashedPassword().equals(PasswordHasher3000.hash(login, password))) {
			System.err.println("wrong password for user " + login);
			return Response.status(Response.Status.OK).entity("Wrong password").build();
		}
		System.out.println("user " + login + " logged in");
		return Response.status(Response.Status.OK).entity("Logged in").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remove")
	public Response remove(@QueryParam("login") String login, @QueryParam("password") String password) {
		User user = userAdder800.findByUsername(login);
		if (user == null) {
			System.err.println("user " + login + " not found");
			return Response.status(Response.Status.OK).entity("User not found").build();
		} else if (!user.getHashedPassword().equals(PasswordHasher3000.hash(login, password))) {
			System.err.println("wrong password for user " + login);
			return Response.status(Response.Status.OK).entity("Wrong password").build();
		}
		userAdder800.deleteUser(user);
		System.out.println("user " + login + " deleted");
		return Response.status(Response.Status.OK).entity("Deleted").build();
	}
}