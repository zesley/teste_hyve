package bol.mancala.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bol.mancala.manager.GameManager;
import bol.mancala.manager.PlayerManager;
import bol.mancala.model.Game;
import bol.mancala.model.Player;

@Path("/games")
public class GameEndpoint {
  @GET
  @Path("/player/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response entryGame(@PathParam(value = "id") Integer id) {
    Player temp = PlayerManager.getPlayer(id);
    if (temp == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    Game game = GameManager.addPlayer(temp);
    return Response.status(Response.Status.OK).entity(new Gson().toJson(game)).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getGame(@PathParam(value = "id") Integer id) {
    Game game = GameManager.getGame(id);
    if (game == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    return Response.status(Response.Status.OK).entity(new Gson().toJson(game)).build();
  }

  @GET
  @Path("/{id}/player/{player}/pit/{pit}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPath(@PathParam(value = "id") Integer gid, @PathParam(value = "player") Integer pid, @PathParam(value = "pit") Integer pit) {
    Game game = GameManager.getGame(gid);
    if (game == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    Player player = PlayerManager.getPlayer(pid);
    if (player == null)
      return Response.status(Response.Status.NOT_FOUND).build();
    Boolean goodMove = game.moveStones(player, pit);
    if (!goodMove)
      return Response.status(Response.Status.FORBIDDEN).build();
    //Everything was good, send game to be drawed
    return Response.status(Response.Status.OK).entity(new Gson().toJson(game)).build();
  }
}
