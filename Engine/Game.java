package Engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import Cards.*;
import Utils.*;
import UtilsGraphics.*;

public class Game implements Serializable {

  private static final long serialVersionUID = 4763151449303616891L;

  private String ID;
  private CircularList<Player> players;
  private Deck deck;
  private ArrayList<Turn> turnHistory;
  private static Game gameInstance;
  private transient Graphicable UI;
  private Settings settings;
  private Player activePlayer;

  private static int amount_players = 2;
  private static int starting_Cards = 7;
  private static int amount_cards_attack = 30;
  private static int amount_cards_heal_castle = 15;
  private static int amount_cards_heal_wall = 15;
  private static int amount_cards_pierce_wall = 20;
  private static int amount_cards_drop_cards = 15;
  private static int amount_cards_steal_card = 15;
  private static int amount_cards_swap_card = 15;
  private static int amount_cards_extra_card = 15;
  private static int amount_cards_soldier = 30;
  private static int amount_cards_kill_soldier = 20;

  private Game() {
    ID = UUID.randomUUID().toString();
    UI = new UI_Graphic();
  }

  public static Game getInstance() {
    if (gameInstance == null) {
      gameInstance = new Game();
    }
    return gameInstance;
  }

  /**
   * switches UI mode
   */
  public void switchGraphicable() {
    if (UI instanceof UI_Console || UI == null) {
      this.UI = new UI_Graphic();
    } else {
      this.UI = new UI_Console();
    }
  }

  /**
   * creates players & adds them to players circular list <br>
   * shuffles player list to randomize 1st player <br>
   * creates, shuffles & deals card deck
   * 
   * @param playerNames name list of players to create
   */
  public void setupGame(ArrayList<String> playerNames) {
    turnHistory = new ArrayList<Turn>();
    players = new CircularList<Player>();
    for (String name : playerNames) {
      players.add(new Player(name));
    }
    Collections.shuffle(players);
    activePlayer = players.get(0);
    deck = new Deck();
    deck.shuffle();
    deck.dealCards();
    showGame();
  }

  /**
   * @return true if activePlayer has won
   */
  public boolean checkActivePlayerWon() {
    boolean allElseLost = true;
    for (Player p : players) {
      if (p != activePlayer && !p.hasLost()) {
        allElseLost = false;
      }
    }
    return allElseLost;
  }

  /**
   * If activePlayer won whows gameOver.<br>
   * If not, passes onto the next player.<br>
   * Skips those players who lost <br>
   * Refresh game screen <br>
   */
  public void nextPlayer() {
    int currentPlayerIndex = players.indexOf(activePlayer);
    activePlayer = players.get(currentPlayerIndex + 1);
    if (checkActivePlayerWon()) {
      showStatistics();
    }
    if (activePlayer.hasLost()) {
      nextPlayer();
    }
    showGame();
  }

  /**
   * @param turn is added to turn history 
   */
  public void registerTurn(Turn turn) {
    this.turnHistory.add(turn);
  }

  /**
   * @param path file location in which to save file
   * <br> saves in "CastleWars.txt" file
   */
  public void saveGame(String path) {
    if(!path.endsWith("CastleWars.txt")) {
      path = path + "\\CastleWars.txt";
    }
    try  {
      FileOutputStream f = new FileOutputStream(path); 
      ObjectOutputStream s = new ObjectOutputStream(f);
      s.writeObject(this);
      s.close();
      showDialog("Successfully saved game!\n" + path);
    } catch (IOException e) {
      e.printStackTrace();
      showDialog("Couln't save game!");
    }
  }
  
  /**
   * @param path file location in which to loadfile
   * <br> looks for "CastleWars.txt" file
   */
  public void loadGame(String path) {

    Game loadedGame = null;
    
    try {
      FileInputStream in = new FileInputStream(path); 
      ObjectInputStream s = new ObjectInputStream(in);
      loadedGame = (Game) s.readObject();
      s.close();
      Game.setAmount_players(loadedGame.getPlayers().size());
      Graphicable ui = UI;
      gameInstance = loadedGame;
      gameInstance.UI = ui;
      showDialog("Loaded previous game!");
      showGame();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      showDialog("Couldn't load game!\nData is corrupt");
      showIntro();
    } catch(IOException e) {
      e.printStackTrace();
      showDialog("Couldn't load game!\nError accesing file");
      showIntro();
    }
  }

  /**
   * @param name of player to search
   * @return player if found, else null
   */
  public Player findPlayer(String name) {
    for (Player p : players) {
      if (p.getName().equals(name)) {
        return p;
      }
    }
    return null;
  }

  /**
   * @param playerNames list of player's names
   * @return true if names match
   */
  public static boolean checkDuplicatePlayers(ArrayList<String> playerNames) {
    Set<String> tempSet = new HashSet<String>();
    for (String str : playerNames) {
      if (!tempSet.add(str)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param playerToCalculate checks turn history and lists cards used and picked
   * @return list of statistics for selected player
   */
  public static ArrayList<Integer> calculateStatistics(Player playerToCalculate) {

    int cardsPicked = 0, attackCardsUsed = 0, dropCardsUsed = 0, extraCardsUsed = 0, healCastleCardsUsed = 0,
        healWallCardsUsed = 0, killSoldierCardsUsed = 0, pierceWallCardsUsed = 0, soldiersUsed = 0,
        stealCardCardsUsed = 0, swapCardCardsUsed = 0;

    for (Turn t : Game.getInstance().getTurnHistory()) {

      if (t.getOwner().equals(playerToCalculate)) {

        if (t.isPickedFromDeck()) {
          cardsPicked++;

        } else {
          if (t.getCardPlayed() instanceof Card_Attack) {
            attackCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_DropCards) {
            dropCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_ExtraCard) {
            extraCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_HealCastle) {
            healCastleCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_HealWall) {
            healWallCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_KillSoldier) {
            killSoldierCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_PierceWall) {
            pierceWallCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_Soldier) {
            soldiersUsed++;
          }
          if (t.getCardPlayed() instanceof Card_StealCard) {
            stealCardCardsUsed++;
          }
          if (t.getCardPlayed() instanceof Card_SwapCard) {
            swapCardCardsUsed++;
          }
        }

      }
    }
    ArrayList<Integer> playerStats = new ArrayList<Integer>();
    playerStats.addAll(Arrays.asList(cardsPicked, attackCardsUsed, dropCardsUsed, extraCardsUsed, healCastleCardsUsed,
        healWallCardsUsed, killSoldierCardsUsed, pierceWallCardsUsed, soldiersUsed, stealCardCardsUsed,
        swapCardCardsUsed));
    return playerStats;

  }

  /**
   * @param info context information about what will be done to the selected player
   * @return player selected from menu
   */
  public Player showTargetPlayerSelectionMenu(String info) {
    ArrayList<String> players = new ArrayList<String>();
    Player ap = Game.getInstance().getActivePlayer();

    for (Player p : Game.getInstance().getPlayers()) {
      if (!p.equals(ap) && !p.hasLost()) {
        players.add(p.getName());
      }
    }
    String playerName = Game.getInstance().showSelectionMenu("Select target player. " + info, players);
    Player target = Game.getInstance().findPlayer(playerName);
    return target;
  }

  /**
   * @return null if none selected / window closed
   * <br> selects a card to use it
   */
  public Card showCardSelectionMenu() {
    ArrayList<String> cards = new ArrayList<String>();
    Player ap = Game.getInstance().getActivePlayer();
    for (Card c : ap.getCards()) {
      cards.add(c.toString());
    }
    String cardName = Game.getInstance().showSelectionMenu("Select a card to use it", cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    // si no selecciona ninguna, vuelve -1
    if (cardIndex >= 0) {
      selectedCard = ap.getCards().get(cardIndex);
    }
    return selectedCard;
  }

  /**
   * @param targetPlayer is made to discard 
   * @return selected card to discard
   */
  public Card showDiscardCardSelectionMenu(Player targetPlayer) {
    ArrayList<String> cards = new ArrayList<String>();
    for (Card c : targetPlayer.getCards()) {
      cards.add(c.toString());
    }
    String cardName = Game.getInstance().showSelectionMenu
        ("Select a card to make " + targetPlayer.getName() + " discard it", cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    if (cardIndex >= 0) {
      selectedCard = targetPlayer.getCards().get(cardIndex);
    }
    if (selectedCard == null) {
      return showDiscardCardSelectionMenu(targetPlayer);
    } else {
      return selectedCard;
    }
  }

  /**
   * @param targetPlayer is made to swap 
   * @return selected card to swap
   */
  public Card showSwapCardSelectionMenu(Player targetPlayer, Card_SwapCard triggeringCard) {

    ArrayList<String> cards = new ArrayList<String>();
    for (Card c : targetPlayer.getCards()) {
      // excludes triggering card
      // so it can't be swapped
      if (c != triggeringCard) {
        cards.add(c.toString());
      }
    }
    int triggeringCardIndex = targetPlayer.getCards().indexOf(triggeringCard);
    String cardName = Game.getInstance().showSelectionMenu("Select a card to give to " + targetPlayer.getName(), cards);
    int cardIndex = cards.indexOf(cardName);
    Card selectedCard = null;
    // to compensate for the omitted card, index is added +1 after that card
    if (cardIndex >= triggeringCardIndex) {
      cardIndex++;
    }
    if (cardIndex >= 0) {
      selectedCard = targetPlayer.getCards().get(cardIndex);
    }
    if (selectedCard == null) {
      return showSwapCardSelectionMenu(targetPlayer, triggeringCard);
    } else {
      return selectedCard;
    }
}
  /**
   * @param targetPlayer player whose soldier will be killed
   * @return selected soldier
   */
  public Card_Soldier showTargetSoldierSelectionMenu(Player targetPlayer) {
    ArrayList<String> cards = new ArrayList<String>();
    for (Card_Soldier c : targetPlayer.getSoldiers()) {
      cards.add(c.toString());
    }
    String cardName = Game.getInstance().showSelectionMenu("Select a soldier to kill from " + targetPlayer.getName(),
        cards);
    int cardIndex = cards.indexOf(cardName);
    Card_Soldier selectedCard = null;
    if (cardIndex >= 0) {
      selectedCard = targetPlayer.getSoldiers().get(cardIndex);
    }
    if (selectedCard == null) {
      return showTargetSoldierSelectionMenu(targetPlayer);
    } else {
      return selectedCard;
    }
  }

  
  // graphicable interface

  public void showIntro() {
    UI.showIntro();
  }

  public void showSettings() {
    UI.showSettings();
  }

  public void showGame() {
    UI.showGame();
  }

  public void showLoadMenu() {
    UI.showLoadMenu();
  }

  public void showSaveMenu() {
    UI.showSaveMenu();
  }

  public void showStatistics() {
    UI.showStatistics();
  }

  public void showDialog(String text) {
    UI.showDialog(text);
  }

  public String showSelectionMenu(String inputInfo, ArrayList<String> list) {
    return UI.showSelectionMenu(inputInfo, list);
  }

  // setters

  public static boolean setAmount_players(int amount_players) {
    if (amount_players <= Settings.PLAYERS.getMax() && amount_players >= Settings.PLAYERS.getMin()) {
      Game.amount_players = amount_players;
      return true;
    } else
      return false;
  }

  public static boolean setStarting_Cards(int startingCards) {
    if (startingCards <= Settings.STARTING_CARDS.getMax() && startingCards >= Settings.STARTING_CARDS.getMin()) {
      Game.starting_Cards = startingCards;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_attack(int amount_cards_attack) {
    if (amount_cards_attack <= Settings.CARDS.getMax() && amount_cards_attack >= Settings.CARDS.getMin()) {
      Game.amount_cards_attack = amount_cards_attack;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_heal_castle(int amount_cards_heal_castle) {
    if (amount_cards_heal_castle <= Settings.CARDS.getMax() && amount_cards_heal_castle >= Settings.CARDS.getMin()) {
      Game.amount_cards_heal_castle = amount_cards_heal_castle;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_heal_wall(int amount_cards_heal_wall) {
    if (amount_cards_heal_wall <= Settings.CARDS.getMax() && amount_cards_heal_wall >= Settings.CARDS.getMin()) {
      Game.amount_cards_heal_wall = amount_cards_heal_wall;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_pierce_wall(int amount_cards_pierce_wall) {
    if (amount_cards_pierce_wall <= Settings.CARDS.getMax() && amount_cards_pierce_wall >= Settings.CARDS.getMin()) {
      Game.amount_cards_pierce_wall = amount_cards_pierce_wall;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_drop_cards(int amount_cards_drop_card) {
    if (amount_cards_drop_card <= Settings.CARDS.getMax() && amount_cards_drop_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_drop_cards = amount_cards_drop_card;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_steal_card(int amount_cards_steal_card) {
    if (amount_cards_steal_card <= Settings.CARDS.getMax() && amount_cards_steal_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_steal_card = amount_cards_steal_card;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_swap_card(int amount_cards_swap_card) {
    if (amount_cards_swap_card <= Settings.CARDS.getMax() && amount_cards_swap_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_swap_card = amount_cards_swap_card;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_kill_soldier(int amount_cards_kill_soldier) {
    if (amount_cards_kill_soldier <= Settings.CARDS.getMax() && amount_cards_kill_soldier >= Settings.CARDS.getMin()) {
      Game.amount_cards_kill_soldier = amount_cards_kill_soldier;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_extra_card(int amount_cards_extra_card) {
    if (amount_cards_extra_card <= Settings.CARDS.getMax() && amount_cards_extra_card >= Settings.CARDS.getMin()) {
      Game.amount_cards_extra_card = amount_cards_extra_card;
      return true;
    } else
      return false;
  }

  public static boolean setAmount_cards_soldier(int amount_cards_soldier) {
    if (amount_cards_soldier <= Settings.CARDS.getMax() && amount_cards_soldier >= Settings.CARDS.getMin()) {
      Game.amount_cards_soldier = amount_cards_soldier;
      return true;
    } else {
      return false;
    }
  }

  // getters

  public Settings getSettings() {
    return settings;
  }

  public Player getActivePlayer() {
    return activePlayer;
  }

  public String getID() {
    return ID;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public Deck getDeck() {
    return deck;
  }

  public ArrayList<Turn> getTurnHistory() {
    return turnHistory;
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

  public static int getAmount_cards_swap_card() {
    return amount_cards_swap_card;
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
