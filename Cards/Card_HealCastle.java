 package Cards;

import Engine.Game;
import Utils.Rarity;

public class Card_HealCastle extends Card
{
  @Override
  public void effect() {
    Rarity healRar = this.getRarity();
    int healPoints = 0;
    switch(healRar) {
      case COMMON:
        healPoints = 10;
        break;
      case MEDIUM:
        healPoints = 25;
        break;
      case RARE:
        healPoints = 70;
        break;
    }
    Game.getInstance().getActivePlayer().restoreCastleHP(healPoints);
    Game.getInstance().getActivePlayer().discardCard(this);    
    Game.getInstance().nextPlayer();
  }
  
  @Override
  public String toString() {
    return "Heal Castle Card " + this.getRarity();
  }
}
