package bol.mancala.exceptions;

public class GameNotFoundException  extends Exception {
  private static final long serialVersionUID = 8029268579827208716L;

  public GameNotFoundException(Integer id) {
    super(String.format("Impossible find game %d", id));
  }
}
