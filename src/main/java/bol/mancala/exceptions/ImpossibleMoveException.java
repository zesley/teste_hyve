package bol.mancala.exceptions;

import bol.mancala.model.Player;

public class ImpossibleMoveException extends Exception {
  private static final long serialVersionUID = -3941933699242496334L;

  public ImpossibleMoveException(Player player, Integer house) {
    super(String.format("Impossible move from %s on house %d", player.getName(), house));
  }
}
