package Cards;

import Engine.Game;
import Engine.Player;
import Utils.Rarity;

public class Card_PierceWall extends Card
{
  @Override
  public void effect() {
    Rarity healRar = this.getRarity();
    int damage = 0;
    switch(healRar) {
      case COMMON:
        damage = 10;
        break;
      case MEDIUM:
        damage = 20;
        break;
      case RARE:
        damage = 30;
        break;
    }
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
 // takeDamage(int damage, boolean wallOnly)
    if(target != null) {
      target.takeDamage(damage, true);
      Game.getInstance().getCurrentlyPlaying().discardCard(this);
      Game.getInstance().nextPlayer();
    }
  }
  
  @Override
  public String toString() {
    return "Pierce Wall Card " + this.getRarity();
  }
}
