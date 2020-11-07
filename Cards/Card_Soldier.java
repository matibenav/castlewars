package Cards;

import Engine.Game;

public class Card_Soldier extends Card
{ 
  @Override
  public void effect() {
    Game.getInstance().getActivePlayer().addSoldier(this);
  }

  @Override
  public String toString() {
    return "Soldier "+  this.getRarity();
  }

}
