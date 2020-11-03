package Cards;

import Engine.Game;

public class Card_ExtraCard extends Card 
{
  public void effect() {
    Game.getInstance().getCurrentlyPlaying().pickCard();
  }

  @Override
  public String toString() {
    return "Extra Card";
  }
}
