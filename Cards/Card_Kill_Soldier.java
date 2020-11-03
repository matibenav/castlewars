package Cards;

import Engine.Game;
import Engine.Player;

public class Card_Kill_Soldier extends Card
{
  public void effect() {
    Player target =  Game.getInstance().getGraphics().getTargetPlayer();
    if(target != null && target.getSoldiers().size() > 0) {
      Card card = Game.getInstance().getGraphics().getTargetSoldier(target);
      target.removeSoldier(target.getSoldiers().indexOf(card));
      Game.getInstance().nextPlayer();
      }
    else if(target.getSoldiers().size() == 0)
      Game.getInstance().getGraphics().showDialog("Selected player does not have any soldiers");
    }

  @Override
  public String toString() {
    return "Kill Soldier Card";
  }
}
