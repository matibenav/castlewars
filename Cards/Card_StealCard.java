package Cards;

import java.util.Random;

import Engine.Game;
import Engine.Player;

public class Card_StealCard extends Card
{
  @Override
  public void effect() {
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
    if(target.getCards().size()>0) {
      Random rand = new Random();
      Card c = target.takeCard(rand.nextInt(target.getCards().size()));
      if(target != null) {
        Game.getInstance().getCurrentlyPlaying().getCards().add(c);
        Game.getInstance().getCurrentlyPlaying().discardCard(this);
        Game.getInstance().nextPlayer();
        }
      }
    else
      Game.getInstance().getGraphics().showDialog("Selected player does not have any cards");
    }

  @Override
  public String toString() {
    return "Steal Card";
  }
}
