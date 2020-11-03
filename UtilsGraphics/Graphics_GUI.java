package UtilsGraphics;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import Cards.Card;
import Cards.Card_Soldier;
import Engine.Game;
import Engine.Player;
import GUI_Windows.*;
import Utils.Graphicable;

public class Graphics_GUI implements Graphicable {
  
  private GUI_CW_Win gameFrame;
  
	@Override
	public void showGame() {
	  if(gameFrame == null)
	    gameFrame = new GUI_CW_Win();
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
  public String getInputFromList(String inputInfo, ArrayList<String> list) {

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
      if(!p.equals(Game.getInstance().getCurrentlyPlaying()) && !p.hasLost()) 
        players.add(p.getName());
    
    String playerName = getInputFromList("Select target player", players);
    Player target = Game.getInstance().findPlayer(playerName);
    System.out.println(playerName + " selected");;
    return target;
  }
  
  public Card selectCard() {
    ArrayList <String> cards = new ArrayList<String>();
    for(Card c : Game.getInstance().getCurrentlyPlaying().getCards()) 
      cards.add(c.toString());
    String cardName = getInputFromList("Select a card to use it", cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    if(cardIndex != -1)
      selectedCard = Game.getInstance().getCurrentlyPlaying().getCards().get(cardIndex);
    return selectedCard;
  }
  
  public Card getTargetCard(Player targetPlayer) {

    ArrayList <String> cards = new ArrayList<String>();
    for(Card c : Game.getInstance().findPlayer(targetPlayer.getName()).getCards()) 
      cards.add(c.toString());
    String cardName = getInputFromList("Select a card to drop", cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    if(cardIndex != -1)
      selectedCard = Game.getInstance().findPlayer(targetPlayer.getName()).getCards().get(cardIndex);
    if (selectedCard == null)
      return getTargetCard(targetPlayer);
    else
      return selectedCard;
  }

  public Card_Soldier getTargetSoldier(Player targetPlayer) {
  ArrayList <String> cards = new ArrayList<String>();
  for(Card_Soldier c : Game.getInstance().findPlayer(targetPlayer.getName()).getSoldiers()) 
    cards.add(c.toString());
  String cardName = getInputFromList("Select a soldier to kill", cards);
  int cardIndex = cards.indexOf(cardName);
  Card_Soldier selectedCard = null;
  if(cardIndex != -1)
    selectedCard = Game.getInstance().findPlayer(targetPlayer.getName()).getSoldiers().get(cardIndex);
  if (selectedCard == null)
    return getTargetSoldier(targetPlayer);
  else
    return selectedCard;
  }
}
