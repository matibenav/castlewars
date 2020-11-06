package Utils;

import java.util.ArrayList;

import Cards.Card;
import Cards.Card_ExchangeCard;
import Cards.Card_Soldier;
import Engine.Player;

public interface Graphicable {

	public void showIntro();
	
	public void showSettings();
	
	public void showGame();
	
	public void loadGame();
	
  public void saveGame();

  public void showDialog(String text);
  
  //usado para elegir jugador o cartas 
  public String selectFromList(String inputInfo, ArrayList<String> list);

  public Player getTargetPlayer();
  
  public Card selectCard();
  
  public Card makeDiscard(Player targetPlayer) ;
  
  public Card exchangeCard(Player targetPlayer, Card_ExchangeCard triggeringCard) ;
  
  public Card_Soldier getTargetSoldier(Player targetPlayer) ;
}
