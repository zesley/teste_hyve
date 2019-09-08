package bol.mancala.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bol.mancala.model.Player;

public class PlayerManager {
  private static final List<Player> players = Collections.synchronizedList(new ArrayList<Player>());

  public synchronized static Player save(Player player) {
    player.setId(player.hashCode());
    players.add(player);
    return player;
  }


  public synchronized static Player update(Player player) {
    Player temp = getPlayer(player.getId());
    if (temp != null)
      temp.setName(player.getName());
    return temp;
  }

  public static Player getPlayer(Integer id) {
    if (id != null)
      for (Player player : players)
        if (player.getId().equals(id))
          return player;
    return null;
  }
}
