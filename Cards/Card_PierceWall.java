package Cards;

import Engine.Game;
import Engine.Player;
import Utils.CardRarities;

public class Card_PierceWall extends Card {

  private static final long serialVersionUID = -7914251954536877993L;

  public Card_PierceWall() {
    super("Card - Pierce Wall");
  }

  @Override
  public void effect() {
    CardRarities healRar = this.getCardRarities();
    int damage = 0;
    switch (healRar) {
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
    Player ap = Game.getInstance().getActivePlayer();
    Player target = Game.getInstance().showTargetPlayerSelectionMenu(" You will damage their wall by " + damage + "HP");
    // takeDamage(int damage, boolean wallOnly)
    if (target != null) {
      target.takeDamage(damage, true);
      ap.discardCard(this);
      ap.playTurn("dealt " + damage + " wall damage to " + target.getName(), false, this);
      Game.getInstance().nextPlayer();
    }
  }

  @Override
  public void setCardRarities(CardRarities cardRarities) {
    super.setCardRarities(cardRarities);
    switch (cardRarities) {
    case COMMON:
      this.description = this.getDescription() + " (10)";
      break;
    case MEDIUM:
      this.description = this.getDescription() + " (20)";
      break;
    case RARE:
      this.description = this.getDescription() + " (30)";
    }
  }

  @Override
  public String toString() {
    return this.getDescription() + " " + this.getCardRarities();
  }

}