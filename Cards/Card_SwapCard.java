package Cards;

import Engine.Player;
import Engine.Game;

public class Card_SwapCard extends Card {

  private static final long serialVersionUID = 9194376554881355703L;

  public Card_SwapCard() {
    super("Card - Swap Cards");
  }

  @Override
  public void effect() {
    // swaps random Card
    Player ap = Game.getInstance().getActivePlayer();
    Player target = Game.getInstance().showTargetPlayerSelectionMenu(" You will swap one card.");
    if (target != null) {
      if (target.getCards().size() > 0) {
        Card c = Game.getInstance().showSwapCardSelectionMenu(Game.getInstance().getActivePlayer(), this);
        target.swapRandomCard(c);
        ap.discardCard(this);
        ap.playTurn("swapped a card with " + target.getName(), false, this);
        Game.getInstance().nextPlayer();
      } else {
        Game.getInstance().showDialog("Selected player does not have any cards");
      }
    }
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

}