package bol.mancala.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bol.mancala.model.Game;
import bol.mancala.model.Player;

public class GameManager {
  private static final List<Game> games = Collections.synchronizedList(new ArrayList<Game>());

  public synchronized static Game addPlayer(Player player) {
    Game availableGame = null;
    for (Game game : games)
      if (game.addPlayer(player))
        availableGame = game;
    if (availableGame == null) {
      availableGame = new Game(games.hashCode(), player);
      games.add(availableGame);
    }
    return availableGame;
  }

  public static Game getGame(Integer id) {
    if (id != null)
      for (Game game : games)
        if (game.getId().equals(id))
          return game;
    return null;
  }
}
