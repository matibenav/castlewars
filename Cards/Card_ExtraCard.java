package Cards;

import Engine.Game;

public class Card_ExtraCard extends Card 
{
  public void effect() {
    Game.getInstance().getActivePlayer().pickCard();
    Game.getInstance().getActivePlayer().discardCard(this);
  }

  @Override
  public String toString() {
    return "Extra Card";
  }
}
