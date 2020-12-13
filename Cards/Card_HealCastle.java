package Cards;

import Engine.Game;
import Engine.Player;
import Utils.CardRarities;

public class Card_HealCastle extends Card {

  private static final long serialVersionUID = -3148578569451442311L;

  public Card_HealCastle() {
    super("Card - Heal Castle");
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
      healPoints = 25;
      break;
    case RARE:
      healPoints = 70;
      break;
    }
    Player ap = Game.getInstance().getActivePlayer();
    if(ap.getCastleHP()<100) {
      ap.restoreCastleHP(healPoints);
      ap.discardCard(this);
      ap.playTurn("restored " + healPoints + " castle HP", false, this);
      Game.getInstance().nextPlayer();
    } else {
      Game.getInstance().showDialog(ap.getName() + " is at full health!");
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
      this.description = this.getDescription() + " (25)";
      break;
    case RARE:
      this.description = this.getDescription() + " (70)";
    }
  }

  @Override
  public String toString() {
    return this.getDescription() + " " + this.getCardRarities();
  }

}