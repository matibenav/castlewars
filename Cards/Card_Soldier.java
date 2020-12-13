package Cards;

import Engine.Game;
import Engine.Player;
import Utils.CardRarities;

public class Card_Soldier extends Card {

  private static final long serialVersionUID = -1993083444177857064L;

  public Card_Soldier() {
    super("Card - Soldier");
  }

  @Override
  public void effect() {
    Player ap = Game.getInstance().getActivePlayer();
    ap.addSoldier(this);
    ap.playTurn("activated " + this.toString(), false, this);
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
      this.description = this.getDescription() + " (75)";
    }
  }

  @Override
  public String toString() {
    return this.getDescription() + " " + this.getCardRarities();
  }
}
