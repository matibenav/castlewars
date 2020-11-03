package Cards;

import java.util.Random;

import Engine.Game;
import Engine.Player;

public class Card_StealCard extends Card
{
  @Override
  public void effect() {
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
    Random rand = new Random();
    Card c = target.takeCard(rand.nextInt(target.getCards().size()));
    if(target != null) {
      Game.getInstance().getCurrentlyPlaying().getCards().add(c);
      Game.getInstance().nextPlayer();
      }
    }

  @Override
  public String toString() {
    return "Steal Card";
  }
}
