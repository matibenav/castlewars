package Cards;

import Engine.Game;
import Utils.Rarity;

public class Card_HealWall extends Card
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
        healPoints = 20;
        break;
      case RARE:
        healPoints = 30;
        break;
    }
    Game.getInstance().getCurrentlyPlaying().restoreWallHP(healPoints);
    Game.getInstance().nextPlayer();
  }

  @Override
  public String toString() {
    return "Heal Wall Card " + this.getRarity();
  }
}