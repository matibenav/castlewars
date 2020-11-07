package Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import Utils.*;
import UtilsGraphics.*;

public class Game { 
  private String id;
  private CircularList <Player> players;
  private Deck deck;
//private ArrayList <Turn>  turnHistory;
  private static Game gameInstance;
  private Graphicable graphics;
  private Settings settings;
  private Player activePlayer;
  
  private static int amount_players = 2;
  private static int starting_Cards = 7;
  private static int amount_cards_attack = 15;
  private static int amount_cards_heal_castle = 15;
  private static int amount_cards_heal_wall = 15;
  private static int amount_cards_pierce_wall = 15;
  private static int amount_cards_drop_cards = 15;
  private static int amount_cards_steal_card = 15;
  private static int amount_cards_exchange_card = 15;
  private static int amount_cards_kill_soldier = 15;
  private static int amount_cards_extra_card = 15;
  private static int amount_cards_soldier = 15;

  
  public static void main(String[] args)  {
    Game g = Game.getInstance();
    g.showIntro();
  }

  
  private Game() {
    id = UUID.randomUUID().toString();
    this.graphics = new Graphics_GUI();
    gameInstance = this;
  }
  
  public static Game getInstance() {
    if(gameInstance == null) 
      gameInstance = new Game();
    return gameInstance;
    }

  public void switchGraphicable() {
    if(graphics instanceof Graphics_GUI)
      this.graphics = new Graphics_Console();
    else
      this.graphics = new Graphics_GUI();
    graphics.showIntro();
  }
 
  public void setupGame(ArrayList<String> playerNames){
    players = new CircularList<Player>();
    for (String name : playerNames) 
      players.add(new Player(name));
  // mezclo el orden de los jugadores
    Collections.shuffle(players);
    activePlayer = players.get(0);
    
    deck = new Deck();
  // mezclo el mazo y reparto
    deck.shuffle();
    deck.dealCards();    
    
    showGame();
  }

  public boolean activePlayerWon() {
    boolean allElseLost = true;
    for (Player p : players) {
      if(p != activePlayer && !p.hasLost()) 
          allElseLost = false;
    }
    return allElseLost;
  }
  
  public void nextPlayer() {
    int currentPlayerIndex = players.indexOf(activePlayer);
    activePlayer = players.get(currentPlayerIndex + 1);
    if(activePlayerWon())
      showGameOver();
    if(activePlayer.hasLost())
      nextPlayer();
    showGame();
    }
 
  public static boolean checkDuplicatePlayers(ArrayList<String> playerNames) {
    Set<String> tempSet = new HashSet<String>();
    for (String str : playerNames) {
      if (!tempSet.add(str)) 
        return true;
    }
    return false;
  }

  public void showGameOver() {
    System.out.println(activePlayer.getName() + " has won! ");
  }
  
  
  public void showIntro() {
    graphics.showIntro();
  }

  public void showSettings() {
    graphics.showSettings();
  }

  public void showGame() {
    graphics.showGame();
  } 

  public void loadGame() {
    System.out.println("TO DO: crear menu para buscar DB y leer datos guardados");
    graphics.loadGame();
  }
  
  public void saveGame() {
    System.out.println("TO DO: crear menu para crear DB y gaurdar datos de juego");
    graphics.saveGame();
  }
  
  // setters
  
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

  public static boolean setAmount_cards_drop_cards(int amount_cards_drop_card) {
    if(amount_cards_drop_card <= Settings.CARDS.getMax() &&
        amount_cards_drop_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_drop_cards = amount_cards_drop_card;
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

  // getters
  
  public Settings getSettings() {
    return settings;
  }
  
  public Graphicable getGraphics() {
    return graphics;
  }
  
  public Player getActivePlayer() {
    return activePlayer;
  }

  public String getId() {
    return id;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public Player findPlayer(String name) {
    for(Player p : players)
      if (p.getName().equals(name))
      return p;
    return null;
  }
  
  public Deck getDeck() {
    return deck;
  }

  public static Game getGameInstance() {
    return gameInstance;
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

  public static int getAmount_cards_drop_cards() {
    return amount_cards_drop_cards;
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
