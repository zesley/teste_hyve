package bol.mancala.model;

public class Pit {
  private Boolean isBigPit;
  private Integer stones;
  private Player player;

  public Pit (Player player, Integer stones, Boolean isBigPit) {
    this.player = player;
    this.stones = stones;
    this.isBigPit = isBigPit;
  }

  public Boolean getIsBigPit() {
    return isBigPit;
  }
  public void setIsBigPit(Boolean isBigPit) {
    this.isBigPit = isBigPit;
  }
  public Integer getStones() {
    return stones;
  }
  public void setStones(Integer stones) {
    this.stones = stones;
  }
  public Player getPlayer() {
    return player;
  }
  public void setPlayer(Player player) {
    this.player = player;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((player == null) ? 0 : player.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pit other = (Pit) obj;
    if (player == null) {
      if (other.player != null)
        return false;
    } else if (!player.equals(other.player))
      return false;
    return true;
  }
}
