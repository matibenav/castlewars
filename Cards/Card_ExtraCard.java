package Cards;

import Engine.Game;
import Engine.Player;

public class Card_ExtraCard extends Card {

  private static final long serialVersionUID = 1632449239597631225L;

  public Card_ExtraCard() {
    super("Card - Extra Card");
  }

  @Override
  public void effect() {
    Player ap = Game.getInstance().getActivePlayer();
    ap.pickCard();
    ap.discardCard(this);
    ap.playTurn("picked an extra card", true, this);
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

}