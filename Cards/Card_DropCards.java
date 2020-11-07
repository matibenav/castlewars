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
    if(target.getCards().size()>0) {
      
      // si tiene menos cartas de las que le voy a hacer descartar
      // igualo las veces que lo hago descartar a la cantidad de cartas
      if(target.getCards().size()< amount)
        amount = target.getCards().size();
      
      if(target != null) {
        for(int i = 0; i<amount; i++) {
          Card card = Game.getInstance().getGraphics().makeDiscard(target);
          target.discardCard(card);
          }
        Game.getInstance().getActivePlayer().discardCard(this);
        Game.getInstance().nextPlayer();
      }
    }
    else
      Game.getInstance().getGraphics().showDialog("Selected player does not have any Cards");
  }
  
  @Override
  public String toString() {
    return "Drop Cards " + this.getRarity();
  }
}
