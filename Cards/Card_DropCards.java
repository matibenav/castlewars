package Cards;

import Engine.Game;
import Engine.Player;
import Utils.Rarity;

public class Card_DropCards extends Card
{
  @Override
  public void effect() {
    Rarity dropRar = this.getRarity();
    int amount = 0;
    switch(dropRar) {
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
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
    if(target != null) {
    for(int i = 0; i<amount; i++) {
      Card card = Game.getInstance().getGraphics().getTargetCard(target);
      target.discardCard(target.takeCard(target.getCards().indexOf(card)));
      }
    Game.getInstance().nextPlayer();
    }
  }
  
  @Override
  public String toString() {
    return "Drop Cards " + this.getRarity();
  }
}
