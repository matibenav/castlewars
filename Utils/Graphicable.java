package Utils;

import java.util.ArrayList;

import Cards.Card;
import Cards.Card_Soldier;
import Engine.Player;

public interface Graphicable {

	public void showIntro();
	
	public void showSettings();
	
	public void showGame();
	
	public void loadGame();
	
  public void saveGame();
  

  public void showDialog(String text);
  
  public String getInputFromList(String inputInfo, ArrayList<String> list);

  public Player getTargetPlayer();
  
  public Card selectCard();
  
  public Card getTargetCard(Player targetPlayer) ;

  public Card_Soldier getTargetSoldier(Player targetPlayer) ;

  
}
