package bol.mancala.model;

public class Game {
  private Pit[] pits = new Pit[14];
  private Player p1 = null;
  private Player p2 = null;
  private Integer id;
  private Player current = null;
  private Boolean plaing = false;
  private Boolean finished = false;

  public Pit[] getPits() {
    return pits;
  }

  public void setPits(Pit[] pits) {
    this.pits = pits;
  }

  public Player getP1() {
    return p1;
  }

  public void setP1(Player p1) {
    this.p1 = p1;
  }

  public Player getP2() {
    return p2;
  }

  public void setP2(Player p2) {
    this.p2 = p2;
  }

  public Player getCurrent() {
    return current;
  }

  public void setCurrent(Player current) {
    this.current = current;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Game(Integer id, Player player) {
    this.id = id;
    this.p1 = player;
  }

  public Integer getId() {
    return id;
  }

  public synchronized Boolean addPlayer(Player player) {
    if (p2 == null) {
      p2 = player;
      return true;
    } else {
      return false;
    }
  }

  public Boolean checkReady() {
    return (p1 != null && p2 != null);
  }

  public synchronized void startGame () {
    for (int i = 0; i < 14; i++) {
      Player p = i < 7 ? p1 : p2;
      Boolean isBigPit = (i == 6 || i == 13);
      Integer stones = isBigPit ? 0 : 6;
      pits[i] = new Pit(p, stones, isBigPit);
    }
    current = Math.random() % 2 == 0 ? p1 : p2;
    finished = false;
    plaing = true;
  }

  public synchronized Boolean moveStones (Player player, Integer position) {
    if (position == null || position < 0 || position > 13 || pits[position].getIsBigPit() || !pits[position].getPlayer().equals(player))
      return false;

    Integer stones = pits[position].getStones();
    Integer nextPosition = position + 1;
    while (stones > 0) {
      stones--;
      if (nextPosition > 13)
        nextPosition = 0;

      Integer currentStones = pits[nextPosition].getStones();
      pits[nextPosition].setStones(currentStones + 1);

      if (stones == 0) {
        if (!pits[nextPosition].getIsBigPit()) {
          if (pits[nextPosition].getStones() == 1 && pits[nextPosition].getPlayer().equals(player)) {
            Integer counterPosition = Math.abs(nextPosition - 12);
            pits[nextPosition].setStones(pits[counterPosition].getStones() + 1);
            pits[counterPosition].setStones(0);
          }
          current = player.equals(p1) ? p2: p1;
        }
      }
      nextPosition++;
    }
    checkFinish();
    return true;
  }

  public void checkFinish() {
    for (int i = 0; i < 13; i++) {
      if (i <= 5 || i >= 7) {
        if (pits[i].getStones() != 0) {
          finished = false;
          return;
        }
      }
    }
    current = null;
    finished = true;
  }

  public Boolean getPlaing() {
    return plaing;
  }

  public void setPlaing(Boolean plaing) {
    this.plaing = plaing;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }
}
