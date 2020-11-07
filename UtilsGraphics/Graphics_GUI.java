package UtilsGraphics;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import Cards.Card;
import Cards.Card_ExchangeCard;
import Cards.Card_Soldier;
import Engine.Game;
import Engine.Player;
import GUI_Windows.*;
import Utils.Graphicable;

public class Graphics_GUI implements Graphicable {
  
  private GUI_GameWin gameFrame;
  
	@Override
	public void showGame() {
	  if(gameFrame == null)
	    gameFrame = new GUI_GameWin();
	  else 
	    gameFrame.paintFrame();
	}

	@Override
	public void showIntro() {
	  GUI_IntroWin intro = new GUI_IntroWin();
	}
	
	@Override
	public void showSettings() {
	  GUI_SettingsWin settings = new GUI_SettingsWin();
	}
	
	@Override
	public void loadGame() {
		
	}

  @Override
  public void saveGame() {
    
  }
  

  public void showDialog(String text){
      JOptionPane.showMessageDialog(null,
          text,
          "",
          JOptionPane.WARNING_MESSAGE);
  }
  
  public String selectFromList(String inputInfo, ArrayList<String> list) {

    String[] options = new String[list.size()];
    for (int i = 0; i < list.size(); i++) 
        options[i] = list.get(i);
    
    String selection = (String)JOptionPane.showInputDialog(
                      null,
                      inputInfo,
                      "",
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      options,
                      null);
    
    if ((selection != null) && selection.length() > 0)
      return selection;
    
    else
      return null;
  }

  public Player getTargetPlayer () {
    
    ArrayList <String> players = new ArrayList<String>();
    
    for(Player p : Game.getInstance().getPlayers()) 
      if(!p.equals(Game.getInstance().getActivePlayer()) && !p.hasLost()) 
        players.add(p.getName());
    
    String playerName = selectFromList("Select target player", players);
    Player target = Game.getInstance().findPlayer(playerName);
    System.out.println(playerName + " selected");;
    return target;
  }
  
  public Card selectCard() {
    ArrayList <String> cards = new ArrayList<String>();
    for(Card c : Game.getInstance().getActivePlayer().getCards()) 
      cards.add(c.toString());
    String cardName = selectFromList("Select a card to use it", cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    if(cardIndex != -1)
      selectedCard = Game.getInstance().getActivePlayer().getCards().get(cardIndex);
    return selectedCard;
  }
  
  public Card makeDiscard(Player targetPlayer) {

    ArrayList <String> cards = new ArrayList<String>();
    for(Card c : targetPlayer.getCards()) 
      cards.add(c.toString());
        
    String cardName = selectFromList("Select a card to make " + targetPlayer.getName() +" discard it", cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;

    if(cardIndex != -1)
      selectedCard = targetPlayer.getCards().get(cardIndex);
    if (selectedCard == null)
      return makeDiscard(targetPlayer);
    else
      return selectedCard;
  }
  
  public Card exchangeCard(Player targetPlayer, Card_ExchangeCard triggeringCard) {

    ArrayList <String> cards = new ArrayList<String>();
    for(Card c : targetPlayer.getCards()) {
      // no incluyo la carta que disparó esta acción
      // para que no se la pueda dar a cambio de otra carta
      if(c!=triggeringCard)
        cards.add(c.toString());
    }
    int triggeringCardIndex = targetPlayer.getCards().indexOf(triggeringCard);
    String cardName = selectFromList("Select a card to give to " + targetPlayer.getName(), cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    
    // para suplir el lugar salteado por la carta que omití
    // a todas las que estén despues les sumo 1 al indice asi coincide
    if(cardIndex>=triggeringCardIndex)
      cardIndex ++;
    if(cardIndex != -1) 
      selectedCard = targetPlayer.getCards().get(cardIndex);
    if (selectedCard == null)
      return exchangeCard(targetPlayer, triggeringCard);
    else
      return selectedCard;
  }

  public Card_Soldier getTargetSoldier(Player targetPlayer) {
    ArrayList <String> cards = new ArrayList<String>();
    for(Card_Soldier c : targetPlayer.getSoldiers()) 
      cards.add(c.toString());
    String cardName = selectFromList("Select a soldier to kill from " + targetPlayer.getName() , cards);
    int cardIndex = cards.indexOf(cardName);
    Card_Soldier selectedCard = null;
    if(cardIndex != -1)
      selectedCard = targetPlayer.getSoldiers().get(cardIndex);
    if (selectedCard == null)
      return getTargetSoldier(targetPlayer);
    else
      return selectedCard;
  }
}
