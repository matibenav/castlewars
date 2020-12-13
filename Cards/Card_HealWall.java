package Cards;

import Engine.Game;
import Engine.Player;
import Utils.CardRarities;

public class Card_HealWall extends Card {

  private static final long serialVersionUID = -3510340883942295955L;

  public Card_HealWall() {
    super("Card - Heal Wall");
  }
  
  @Override
  public void effect() {
    CardRarities healRar = this.getCardRarities();
    int healPoints = 0;
    switch (healRar) {
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
    Player ap = Game.getInstance().getActivePlayer();
    if(ap.getCastleHP()<100) {
      ap.restoreWallHP(healPoints);
      ap.discardCard(this);
      ap.playTurn("restored " + healPoints + " wall HP", false, this); 
      Game.getInstance().nextPlayer();
    } else {
      Game.getInstance().showDialog(ap.getName() + " is at full health!");
    }
  }

  @Override
  public void setCardRarities(CardRarities cardRarities) {
    super.setCardRarities(cardRarities);
    switch(cardRarities) {
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