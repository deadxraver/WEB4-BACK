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
		if (user == null || !user.getHashedPassword().equals(PasswordHasher3000.hash(login, password))) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		Dot dot = new Dot();
		dot.setX(x);
		dot.setY(y);
		dot.setR(r);
		dot.setExecTime(System.currentTimeMillis() - startTime);
		dot.setCurTime(LocalDateTime.now());
		user.addDot(dot);
		System.out.println("added dot " + dot + " to user " + login);
		return Response.status(Response.Status.OK).entity(new DotDTO(dot)).build();
	}

}
