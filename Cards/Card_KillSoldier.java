package Cards;

import Engine.Game;
import Engine.Player;

public class Card_KillSoldier extends Card {

  private static final long serialVersionUID = -2158509920792921143L;

  public Card_KillSoldier() {
    super("Card - Kill Soldier");
  }

  @Override
  public void effect() {
    Player ap = Game.getInstance().getActivePlayer();
    Player target = Game.getInstance().showTargetPlayerSelectionMenu(" You will kill one of their soldiers.");
    if (target != null) {
      if (target.getSoldiers().size() > 0) {
        Card_Soldier card = Game.getInstance().showTargetSoldierSelectionMenu(target);
        target.removeSoldier(target.getSoldiers().indexOf(card));
        ap.discardCard(this);
        ap.playTurn("killed " + target.getName() + "'s " + card.toString(), false, this);
        Game.getInstance().nextPlayer();
      }
    } else {
      Game.getInstance().showDialog("Selected player does not have any soldiers");
    }
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

}