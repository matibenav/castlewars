package Cards;

import Engine.Game;

public class Card_ExtraCard extends Card 
{
  public void effect() {
    Game.getInstance().getCurrentlyPlaying().pickCard();
    Game.getInstance().getCurrentlyPlaying().discardCard(this);
  }

  @Override
  public String toString() {
    return "Extra Card";
  }
}
