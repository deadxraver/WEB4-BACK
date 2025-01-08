package rest;

import datamodels.Dot;
import datamodels.DotDTO;
import db.PasswordHasher3000;
import datamodels.User;
import db.UserAdder800;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import littlehelpers.Checker;
import littlehelpers.Validator;

import java.time.LocalDateTime;

@Path("/main")
public class MainResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addDot(@QueryParam("login") String login, @QueryParam("password") String password,
						   @QueryParam("x") Double x, @QueryParam("y") Double y, @QueryParam("r") Double r) {
		long startTime = System.currentTimeMillis();
		User user = UserAdder800.findByUsername(login);
		if (user == null || !PasswordHasher3000.verify(password, user.getHashedPassword())) {
			System.err.println("user " + login + " not found or password is incorrect");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		if (!new Validator(x, y, r).validateAll()) return Response.status(Response.Status.BAD_REQUEST).build();
		Dot dot = new Dot();
		dot.setX(x);
		dot.setY(y);
		dot.setR(r);
		dot.setHit(new Checker(x, y, r).checkAll());
		dot.setExecTime(System.currentTimeMillis() - startTime);
		dot.setCurTime(LocalDateTime.now());
		user.addDot(dot);
		System.out.println("added dot " + dot + " to user " + login);
		return Response.status(Response.Status.OK).entity(new DotDTO(dot)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clear")
	public Response clear(@QueryParam("login") String login, @QueryParam("password") String password) {
		User user = UserAdder800.findByUsername(login);
		if (user == null || !PasswordHasher3000.verify(password, user.getHashedPassword())) {
			System.err.println("user " + login + " not found or password is incorrect");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		user.clearDots();
		System.out.println("cleared dots for " + login);
		return Response.status(Response.Status.OK).build();
	}

}
