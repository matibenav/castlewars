package Cards;

import Engine.Game;
import Engine.Player;

public class Card_StealCard extends Card {

  private static final long serialVersionUID = 8499948871434739344L;

  public Card_StealCard() {
    super("Card - Steal Card");
  }

  @Override
  public void effect() {
    Player ap = Game.getInstance().getActivePlayer();
    Player target = Game.getInstance().showTargetPlayerSelectionMenu(" You will steal one card.");
    if (target != null) {
      if (target.getCards().size() > 0) {
        Card c = target.swapRandomCard(null);
        ap.getCards().add(c);
        ap.discardCard(this);
        ap.playTurn("stole a card from " + target.getName(), false, this);
        Game.getInstance().nextPlayer();
      }else {
        Game.getInstance().showDialog("Selected player does not have any cards");
      }
    }
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

}