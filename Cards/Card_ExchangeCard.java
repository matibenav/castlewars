package Cards;

import Engine.Player;
import Engine.Game;

public class Card_ExchangeCard extends Card
{
  public void effect() {
    //exchangeRndCard
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
    if(target != null) {
    Card c = Game.getInstance().getGraphics().getTargetCard(Game.getInstance().getCurrentlyPlaying());
    target.exchangeRndCard(c);
    Game.getInstance().nextPlayer();
    }
  }
  
  @Override
  public String toString() {
    return "Exchange Card";
  }
}
