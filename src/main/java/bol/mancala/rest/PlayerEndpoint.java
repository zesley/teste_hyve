package bol.mancala.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bol.mancala.manager.PlayerManager;
import bol.mancala.model.Player;

@Path("/players")
public class PlayerEndpoint {
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPlayer(@PathParam(value = "id") Integer id) {
    Player player = PlayerManager.getPlayer(id);
    if (player == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    else
      return Response.status(Response.Status.OK).entity(new Gson().toJson(player)).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response savePlayer(Player player) {
    player = PlayerManager.save(player);
    if (player != null)
      return Response.status(Response.Status.OK).entity(new Gson().toJson(player)).build();
    else
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updatePlayer(Player player) {
    player = PlayerManager.update(player);
    if (player != null)
      return Response.status(Response.Status.OK).entity(new Gson().toJson(player)).build();
    else
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
  }
}
