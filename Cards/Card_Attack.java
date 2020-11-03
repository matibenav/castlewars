package Cards;

import Engine.Player;
import Engine.Game;

public class Card_Attack extends Card
{

  @Override
  public void effect() {
    int damage = Game.getInstance().getCurrentlyPlaying().calculateDamage();
    Player target = Game.getInstance().getGraphics().getTargetPlayer();
    // takeDamage(int damage, boolean wallOnly)
    if(target != null) {
      target.takeDamage(damage, false);
      Game.getInstance().nextPlayer();
    }
  }

  @Override
  public String toString() {
    return "Attack Card";
  }
}
