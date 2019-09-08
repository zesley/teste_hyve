package bol.mancala.exceptions;

import bol.mancala.model.Player;

public class PlayerNotFoundException extends Exception {
  private static final long serialVersionUID = 8663298575647681209L;

  public PlayerNotFoundException(Integer id) {
    super(String.format("Player with id %d not found", id));
  }

  public PlayerNotFoundException(Player player) {
    super(String.format("Player %s with id %d not found", player.getName(), player.getId()));
  }
}
