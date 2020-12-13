package Cards;

import Engine.Game;
import Engine.Player;
import Utils.CardRarities;

public class Card_DropCards extends Card {

  private static final long serialVersionUID = 3433035760519299222L;

  public Card_DropCards() {
    super("Card - Drop Cards");
  }

  @Override
  public void effect() {
    CardRarities dropRar = this.getCardRarities();
    int amount = 0;
    switch (dropRar) {
    case COMMON:
      amount = 1;
      break;
    case MEDIUM:
      amount = 2;
      break;
    case RARE:
      amount = 3;
      break;
    }
    Player ap = Game.getInstance().getActivePlayer();
    Player target = Game.getInstance().showTargetPlayerSelectionMenu(" They will discard " + amount + " card(s).");
    if (target != null) {
      if (target.getCards().size() > 0) {

        // si tiene menos cartas de las que le voy a hacer descartar
        // igualo las veces que lo hago descartar a la cantidad de cartas
        if (target.getCards().size() < amount) {
          amount = target.getCards().size();
        }

        for (int i = 0; i < amount; i++) {
          Card card = Game.getInstance().showDiscardCardSelectionMenu(target);
          target.discardCard(card);
        }
        ap.discardCard(this);
        ap.playTurn("made " + target.getName() + " discard " + amount + " cards", false, this);
        Game.getInstance().nextPlayer();
      }
    } else {
      Game.getInstance().showDialog("Selected player does not have any cards");
    }
  }

  @Override
  public void setCardRarities(CardRarities cardRarities) {
    super.setCardRarities(cardRarities);
    switch (cardRarities) {
    case COMMON:
      this.description = this.getDescription() + " (1)";
      break;
    case MEDIUM:
      this.description = this.getDescription() + " (2)";
      break;
    case RARE:
      this.description = this.getDescription() + " (3)";
    }
  }

  @Override
  public String toString() {
    return this.getDescription() + " " + this.getCardRarities();
  }
}
