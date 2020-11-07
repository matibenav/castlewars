package Cards;

import Engine.Player;
import Engine.Game;

public class Card_ExchangeCard extends Card
{
  public void effect() {
    //exchangeRndCard
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
    if(target != null) {
      if(target.getCards().size()>0) {
        Card c = Game.getInstance().getGraphics().exchangeCard(Game.getInstance().getActivePlayer(), this);
        target.exchangeRndCard(c);
        Game.getInstance().getActivePlayer().discardCard(this);
        Game.getInstance().nextPlayer();
      }
      else
        Game.getInstance().getGraphics().showDialog("Selected player does not have any cards");
    }
  }
  @Override
  public String toString() {
    return "Exchange Card";
  }
}
