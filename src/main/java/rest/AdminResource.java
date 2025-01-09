package rest;

import datamodels.User;
import datamodels.UserDTO;
import db.UserAdder800;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;

@Path("/admin")
public class AdminResource {

	private static final String ADMIN_USERNAME = "admin";
	private static final String ADMIN_PASSWORD = "admin";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/auth")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
			User[] users = UserAdder800.findAllUsers();
			System.out.println("users in current db: ");
			UserDTO[] usersDTO = new UserDTO[users.length];
			for (int i = 0; i < users.length; i++) {
				usersDTO[i] = new UserDTO(users[i]);
			}
			System.out.println(Arrays.toString(usersDTO));
			return Response.ok(Arrays.toString(usersDTO)).build();
		} else {
			System.err.println("admin username or password incorrect");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remove")
	public Response removeUser(@QueryParam("username") String username, @QueryParam("admin_username") String adminUsername, @QueryParam("admin_password") String adminPassword) {
		System.out.println("admin username: " + adminUsername + " admin password: " + adminPassword);
		if (ADMIN_USERNAME.equals(adminUsername) && ADMIN_PASSWORD.equals(adminPassword)) {
			User user = UserAdder800.findByUsername(username);
			if (user == null) {
				System.err.println("no such user");
				return Response.status(Response.Status.EXPECTATION_FAILED).build();
			}
			UserAdder800.deleteUser(user);
			return Response.ok(user).build();
		} else {
			System.err.println("admin username or password incorrect");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
