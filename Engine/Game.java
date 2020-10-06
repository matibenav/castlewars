package Engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import Utils.*;
import UtilsGraphics.*;
import Cards.*;
import Engine.*;
import GUI_Windows.*;
import Cards.*;

public class Game { 
  private String id;
  private CircularList <Player> players;
  private Deck deck;
//private Turn turn;
  private static Game gameInstance;
  private Graphicable graphics;
  private Settings settings;

  private Player currentlyPlaying;
  
  private static int amount_players = 2;

  private static int starting_Cards = 7;
  private static int amount_cards_attack = 15;
  private static int amount_cards_heal_castle = 15;
  private static int amount_cards_heal_wall = 15;
  private static int amount_cards_pierce_wall = 15;
  private static int amount_cards_drop_card = 15;
  private static int amount_cards_steal_card = 15;
  private static int amount_cards_exchange_card = 15;
  private static int amount_cards_kill_soldier = 15;
  private static int amount_cards_extra_card = 15;
  private static int amount_cards_soldier = 15;

  @SuppressWarnings("serial")
  //deberia serializar
  public class CircularList <E> extends ArrayList<E> {
    @Override
    public E get(int index) {
      return super.get(index % size());
    }
  }
 
  
  public static void main(String[] args)  {
    Game g = new Game();
  }

  
  private Game() {
    String id = UUID.randomUUID().toString();
    gameInstance = this;
	  this.graphics = new Graphics_GUI();
	  graphics.showIntro();
  }

  public static Game getInstance() {
    if (gameInstance == null)
    	gameInstance = new Game();
      return gameInstance;
    }

  public void switchGraphicable() {
    if(graphics instanceof Graphics_GUI)
      this.graphics = new Graphics_Console();
    else
      this.graphics = new Graphics_GUI();
//    System.out.println("Cambio de interfaz gráfica");
    graphics.showIntro();
  }
 

  public void showIntro() {
    graphics.showIntro();
  }

  public void showGame() {
    graphics.showGame();
  } 

  public void showSettings() {
    graphics.showSettings();
  }

  public void loadGame() {
    System.out.println("TO DO: crear menu para buscar DB y leer datos guardados");
    graphics.loadGame();
  }

  
  public void playCastleWars() {
    
    /*
    boolean gameOver = false;
    int turnCount = 0;
    graphics.showGame();
    while(!gameOver) {
      System.out.println("Turn n°" + turnCount +" - it's " +players.get(turnCount).getName()+ "'s turn");
      turnCount ++;
      //refresh UI enabled/disabled
      if (turnCount== 20)
        gameOver = true;
    }*/
  }
  
  public void createGameObjects(ArrayList<String> playerNames){

    deck = new Deck();
    deck.shuffle();
    players = new CircularList<Player> ();
    
    for (String s : playerNames) {
      
      ArrayList<Card> playerDeck = new ArrayList<Card>();
      
      for(int i = 0; i<starting_Cards;i++) {
        playerDeck.add(deck.getCards().get(0));
        deck.getCards().remove(0);
      }
      
      players.add(new Player(s, playerDeck));
    }
    playCastleWars();
  }

  
  public static ArrayList<String> trimSpaces(ArrayList<String> names){
    for(String s : names)
      s.replaceAll("\\s+$", "");
    return names;
  }

  public static boolean checkDuplicatePlayers(ArrayList<String> playerNames) {
    Set<String> tempSet = new HashSet<String>();
    for (String str : playerNames) {
        if (!tempSet.add(str)) {
            return true;
        }
    }
    return false;
  }
 
  // g & s
  
  public Settings getSettings() {
    return settings;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPlayers(CircularList<Player> players) {
    this.players = players;
  }

  public static void setGameInstance(Game gameInstance) {
    Game.gameInstance = gameInstance;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public void setGraphics(Graphicable graphics) {
    this.graphics = graphics;
  }

  public void setSettings(Settings settings) {
    this.settings = settings;
  }
  
  public void setCurrentlyPlaying(Player currentlyPlaying) {
    this.currentlyPlaying = currentlyPlaying;
  }

  public static boolean setAmount_players(int amount_players) {
    if(amount_players <= Settings.PLAYERS.getMax() &&
       amount_players >= Settings.PLAYERS.getMin()) {
       Game.amount_players = amount_players;
       return true;
     }
     else
       return false;
  }

  public static boolean setStarting_Cards(int startingCards) {
    if(startingCards <= Settings.STARTING_CARDS.getMax() &&
       startingCards >= Settings.STARTING_CARDS.getMin()) {
       Game.starting_Cards = startingCards;
       return true;
     }
     else
       return false;
  }

  public static boolean setAmount_cards_attack(int amount_cards_attack) {
    if(amount_cards_attack <= Settings.CARDS.getMax() &&
       amount_cards_attack >= Settings.CARDS.getMin()) {
       Game.amount_cards_attack = amount_cards_attack;
       return true;
      }
      else
        return false;
  }

  public static boolean setAmount_cards_heal_castle(int amount_cards_heal_castle) {
    if(amount_cards_heal_castle <= Settings.CARDS.getMax() &&
        amount_cards_heal_castle >= Settings.CARDS.getMin()) {
      Game.amount_cards_heal_castle = amount_cards_heal_castle;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_heal_wall(int amount_cards_heal_wall) {
    if(amount_cards_heal_wall <= Settings.CARDS.getMax() &&
        amount_cards_heal_wall >= Settings.CARDS.getMin()) {
      Game.amount_cards_heal_wall = amount_cards_heal_wall;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_pierce_wall(int amount_cards_pierce_wall) {
    if(amount_cards_pierce_wall <= Settings.CARDS.getMax() &&
        amount_cards_pierce_wall >= Settings.CARDS.getMin()) {
      Game.amount_cards_pierce_wall = amount_cards_pierce_wall;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_drop_card(int amount_cards_drop_card) {
    if(amount_cards_drop_card <= Settings.CARDS.getMax() &&
        amount_cards_drop_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_drop_card = amount_cards_drop_card;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_steal_card(int amount_cards_steal_card) {
    if(amount_cards_steal_card <= Settings.CARDS.getMax() &&
        amount_cards_steal_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_steal_card = amount_cards_steal_card;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_exchange_card(int amount_cards_exchange_card) {
    if(amount_cards_exchange_card <= Settings.CARDS.getMax() &&
        amount_cards_exchange_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_exchange_card = amount_cards_exchange_card;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_kill_soldier(int amount_cards_kill_soldier) {
    if(amount_cards_kill_soldier <= Settings.CARDS.getMax() &&
        amount_cards_kill_soldier >= Settings.CARDS.getMin()) {
        Game.amount_cards_kill_soldier = amount_cards_kill_soldier;
         return true;
       }
       else
         return false;
  }

  public static boolean setAmount_cards_extra_card(int amount_cards_extra_card) {
    if(amount_cards_extra_card <= Settings.CARDS.getMax() &&
        amount_cards_extra_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_extra_card = amount_cards_extra_card;
          return true;
        }
        else
          return false;
  }

  public static boolean setAmount_cards_soldier(int amount_cards_soldier) {    
    if(amount_cards_soldier <= Settings.CARDS.getMax() &&
        amount_cards_soldier >= Settings.CARDS.getMin()) {
      Game.amount_cards_soldier = amount_cards_soldier;
        return true;
      }
      else
        return false;
  }


  
  public Player getCurrentlyPlaying() {
    return currentlyPlaying;
  }

  public String getId() {
    return id;
  }

  public CircularList<Player> getPlayers() {
    return players;
  }

  public Deck getDeck() {
    return deck;
  }

  public static Game getGameInstance() {
    return gameInstance;
  }

  public Graphicable getGraphics() {
    return graphics;
  }
 
  public static int getAmount_players() {
    return amount_players;
  }

  public static int getStarting_Cards() {
    return starting_Cards;
  }

  public static int getAmount_cards_attack() {
    return amount_cards_attack;
  }

  public static int getAmount_cards_heal_castle() {
    return amount_cards_heal_castle;
  }

  public static int getAmount_cards_heal_wall() {
    return amount_cards_heal_wall;
  }

  public static int getAmount_cards_pierce_wall() {
    return amount_cards_pierce_wall;
  }

  public static int getAmount_cards_drop_card() {
    return amount_cards_drop_card;
  }

  public static int getAmount_cards_steal_card() {
    return amount_cards_steal_card;
  }

  public static int getAmount_cards_exchange_card() {
    return amount_cards_exchange_card;
  }

  public static int getAmount_cards_kill_soldier() {
    return amount_cards_kill_soldier;
  }

  public static int getAmount_cards_extra_card() {
    return amount_cards_extra_card;
  }

  public static int getAmount_cards_soldier() {
    return amount_cards_soldier;
  }
}
