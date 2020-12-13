package Cards;

import Engine.Player;
import Engine.Game;

public class Card_Attack extends Card {

  private static final long serialVersionUID = -2667739582126383418L;

  public Card_Attack() {
    super("Card - Attack");
  }

  @Override
  public void effect() {

    Player ap = Game.getInstance().getActivePlayer();
    int damage = ap.calculateDamage();
    if(damage > 0) {
      Player target = Game.getInstance().showTargetPlayerSelectionMenu(" You will do "+ damage +" HP of damage.");
      // takeDamage(int damage, boolean wallOnly)
      if (target != null) {
        target.takeDamage(damage, false);
        String hasLostText = "";
        if (target.hasLost())
          hasLostText = "\n" + target.getName() + " has lost!";
        ap.playTurn("dealt " + damage + " damage to " + target.getName() + hasLostText, false, this);
        Game.getInstance().nextPlayer();
      }
    } else {
      Game.getInstance().showDialog("Soldiers must be active for attack!");
    }
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

}