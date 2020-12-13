package UtilsGraphics;

import java.util.ArrayList;
import java.util.Scanner;

import Cards.Card;
import Cards.Card_Soldier;
import Engine.Game;
import Engine.Player;
import Engine.Settings;
import Engine.Turn;
import Utils.Graphicable;

public class UI_Console implements Graphicable {

  private static Scanner inputScanner;

  @Override
  public void showIntro() {
    System.out.println("Castle Wars \n");
    System.out.println(Game.getInstance().getID());
    int selection = scanInt("Select option:\n" + 
        "[1] New Game\n" + 
        "[2] Load game\n" + 
        "[3] Switch graphic interface.", 1, 3);
    switch (selection) {
      case 1:
        Game.getInstance().showSettings();
        break;
      case 2:
        Game.getInstance().showLoadMenu();
        break;
      case 3:
        Game.getInstance().switchGraphicable();
        Game.getInstance().showIntro();
        break;
    }
  }

  @Override
  public void showSettings() {
    System.out.println("\n");
    System.out.println("Game settings \n");
    int selection = scanInt(
        "Select option:\n" 
        + "[0] Return.\n" 
        + "[1] List settings\n" 
        + "[2] Edit settings\n" 
        + "[3] Continue to game",0, 3);
    
    switch (selection) {

    case 1:
      System.out.println("# Players : " + Game.getAmount_players());
      System.out.println("# Starting Cards : " + Game.getStarting_Cards());
      System.out.println("# Cards - Attack : " + Game.getAmount_cards_attack());
      System.out.println("# Cards - Heal Castle : " + Game.getAmount_cards_heal_castle());
      System.out.println("# Cards - Heal Wall : " + Game.getAmount_cards_heal_wall());
      System.out.println("# Cards - Pierce Wall : " + Game.getAmount_cards_pierce_wall());
      System.out.println("# Cards - Drop Card : " + Game.getAmount_cards_drop_cards());
      System.out.println("# Cards - Steal Card : " + Game.getAmount_cards_steal_card());
      System.out.println("# Cards - Swap Card : " + Game.getAmount_cards_swap_card());
      System.out.println("# Cards - Extra Card : " + Game.getAmount_cards_extra_card());
      System.out.println("# Cards - Soldier : " + Game.getAmount_cards_soldier());
      System.out.println("# Cards - Kill Soldier : " + Game.getAmount_cards_kill_soldier());
      Game.getInstance().showSettings();
      break;

    case 2:
      boolean result = true;
      int editingSetting = -1;
      while (editingSetting != 0) {
        int max, min;
        editingSetting = scanInt("Select setting to edit:\n" 
            + "[0]  Return\n" 
            + "[1]  Players\n"
            + "[2]  Starting Cards\n" 
            + "[3]  Cards - Attack\n" 
            + "[4]  Cards - Heal Castle\n"
            + "[5]  Cards - Heal Wall\n" 
            + "[6]  Cards - Pierce Wall\n" 
            + "[7]  Cards - Drop Card\n"
            + "[8]  Cards - Steal Card\n" 
            + "[9]  Cards - Swap Card\n" 
            + "[10] Cards - Extra Card\n"
            + "[11] Cards - Soldier\n" 
            + "[12] Cards - Kill Soldier", 0, 12);

        switch (editingSetting) {
        case 0:
          break;

        case 1:
          max = Settings.PLAYERS.getMax();
          min = Settings.PLAYERS.getMin();
          result = Game.setAmount_players(scanInt("Set amount of [Players] (max " + min + " min " + max + ")", min, max));
          break;

        case 2:
          max = Settings.STARTING_CARDS.getMax();
          min = Settings.STARTING_CARDS.getMin();
          result = Game.setStarting_Cards(scanInt("Set amount of [Starting Cards] (max " + min + " min " + max + ")", min, max));
          break;

        case 3:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_attack(scanInt("Set amount of] [Attack Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 4:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_heal_castle(scanInt("Set amount of [Heal Castle] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 5:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_heal_wall(scanInt("Set amount of [Heal Wall] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 6:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_pierce_wall(scanInt("Set amount of [Pierce Wall] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 7:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_drop_cards(scanInt("Set amount of [Drop Cards] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 8:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_steal_card(scanInt("Set amount of [Steal Card] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 9:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_extra_card(scanInt("Set amount of [Swap Card] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 10:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_heal_wall(scanInt("Set amount of [Extra Card] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 11:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_heal_wall(scanInt("Set amount of [Soldier] Cards (max " + min + " min " + max + ")", min, max));
          break;

        case 12:
          max = Settings.CARDS.getMax();
          min = Settings.CARDS.getMin();
          result = Game.setAmount_cards_heal_wall(scanInt("Set amount of [Kill Soldier] Cards (max " + min + " min " + max + ")", min, max));
          break;
        }
        if (!result) {
          Game.getInstance().showDialog("Forbidden value!");
        } else if (editingSetting != 0) {
          Game.getInstance().showDialog("Setting edited!");
        }
      }
      Game.getInstance().showSettings();
      break;
      
    case 3:
      boolean checked = false, empty = false;      
      ArrayList<String> names = new ArrayList<String>();
      while(!checked) {
        for(int i = 1; i<=Game.getAmount_players(); i++) {
          System.out.println("Type player n°"+i+"'s name");
          inputScanner =  new Scanner(System.in);
          String name = inputScanner.next();
          names.add(name.trim());
        }
        for(String s : names) {
          if(s.equals("")) {
            empty = true;
          }
        }
        if (empty) {
          Game.getInstance().showDialog("Empty player name.");
          names.clear();
        } else if (Game.checkDuplicatePlayers(names)) {
          Game.getInstance().showDialog("Duplicate player name.");
          names.clear();
        } else {
          checked = true;
        }
      }
      Game.getInstance().setupGame(names);
      break;
      
    case 4:
      Game.getInstance().showIntro();
      break;
    }
  }

  @Override
  public void showGame() {

    ArrayList<String> rowDescriptions = new ArrayList<String>();
    rowDescriptions.add("Castle HP         ");
    rowDescriptions.add("Wall HP           ");
    rowDescriptions.add("Attack Power      ");
    rowDescriptions.add("# Cards           ");
    rowDescriptions.add("# Soldiers        ");

    ArrayList<String> rows = new ArrayList<String>();
    for(int i = 0; i<rowDescriptions.size();i++) {
      rows.add("");
    }

    String playerNames = "";
    ArrayList<Player> pl = Game.getInstance().getPlayers();

    for(int i = 0; i<pl.size(); i++) {
      playerNames = playerNames + pl.get(i).getName() + "\t\t";
      //por un tema de formateo
      String tab;
      if(i == 0) {
        tab = "\t";
      } else {
        tab = "\t\t";
      }
      pl.get(i).sortCards();
      rows.set(0, rows.get(0) + pl.get(i).getCastleHP()+ " HP"+tab);
      rows.set(1, rows.get(1) + pl.get(i).getWallHP()+ " HP\t\t");
      rows.set(2, rows.get(2) + pl.get(i).calculateDamage()+ " HP\t\t");
      rows.set(3, rows.get(3) + pl.get(i).getCards().size() + "\t\t");
      rows.set(4, rows.get(4) + pl.get(i).getSoldiers().size() + "\t\t");
    }
    Player ap = Game.getInstance().getActivePlayer();
    System.out.println("\n\n\n");
    ArrayList <Turn> th = Game.getInstance().getTurnHistory();
    if(th.size()>0) {
      Turn t = th.get(th.size()-1);
      System.out.println("\nLast turn played:\n"+t.getDescription());
    }
    System.out.println("\n                  " + playerNames);
    
    for(int i = 0; i<rowDescriptions.size(); i++) {
      System.out.println(rowDescriptions.get(i) + rows.get(i));
    }
    
    if (!Game.getInstance().checkActivePlayerWon()) {
      System.out.println("\nIt's "+ ap.getName() +"'s turn!\n");
      int selection = scanInt("\nSelect option:\n" + 
          "[0] Exit\n" + 
          "[1] Use Card\n" + 
          "[2] Pick Card\n" +
          "[3] List Soldiers\n" + 
          "[4] List Turn history\n" + 
          "[5] Switch graphic interface\n"+
          "[6] Save Game", 0, 6);
  
      switch (selection) {
      
      case 0:        
        Game.getInstance().showDialog("Game exited");
        inputScanner.close();
        System.exit(0);
        break;
        
      case 1:
        Card c = Game.getInstance().showCardSelectionMenu();
        if (c != null) {
          c.effect();
          Game.getInstance().showGame();
        }
        break;
        
      case 2:
        ap.pickCard();
        ap.playTurn("picked a card from the deck", true, null);
        Game.getInstance().nextPlayer();
        break;
        
      case 3:
        for(Player p : Game.getInstance().getPlayers()) {
          String soldiers = "";
          for (Card_Soldier s : p.getSoldiers()) {
            soldiers = soldiers + s + ", " ; 
          }
          System.out.println(p.getName() + ": " + soldiers);
        }
        Game.getInstance().showGame();
        break;
        
      case 4:
        for(Turn t : th) {
          System.out.println(t.getDescription());
        }
        if(th.size() == 0) {
          System.out.println("No turns played yet");
        }
        Game.getInstance().showGame();
        break;
        
      case 5:
        Game.getInstance().switchGraphicable();
        Game.getInstance().showGame();        
        break;
        
      case 6:
        Game.getInstance().showSaveMenu();
        break;
      }
      
    } else {
      for(int i = 0; i<50; i++) {
        System.out.println("\n");
      }
      System.out.println(ap.getName() +" won!");
      Game.getInstance().showStatistics();
      int exit = scanInt("\nPlay again?:\n" + 
          "[0] No\n" + 
          "[1] Yes" , 0, 1); 

      switch (exit) {
      case 0: 
        Game.getInstance().showDialog("Game exited");
        inputScanner.close();
        System.exit(0);
        break;
        
      case 1: 
        Game.getInstance().showIntro();
        break;
      }
    }
  }

  @Override
  public void showLoadMenu() {
    String loadPath;
    System.out.println("Load Menu");
    System.out.println("Type a file path to load game");
    inputScanner =  new Scanner(System.in);
    loadPath = inputScanner.next();
    Game.getInstance().loadGame(loadPath);
  }

  @Override
  public void showSaveMenu() {
    String savePath;

    System.out.println("Save Menu");
    System.out.println("Type a folder path to save game");
    inputScanner =  new Scanner(System.in);
    savePath = inputScanner.next();
    Game.getInstance().saveGame(savePath);
    Game.getInstance().showGame();

  }

  public void showStatistics() {
    ArrayList<String> rowDescriptions = new ArrayList<String>();
    rowDescriptions.add("Cards picked               ");
    rowDescriptions.add("Attack Cards used          ");
    rowDescriptions.add("Drop Cards used            ");
    rowDescriptions.add("Extra Cards used           ");
    rowDescriptions.add("Heal Castle Cards used     ");
    rowDescriptions.add("Heal Wall Cards used       ");
    rowDescriptions.add("Kill Soldier Cards used    ");
    rowDescriptions.add("Pierce Wall Cards used     ");
    rowDescriptions.add("Soldiers activated         ");
    rowDescriptions.add("Steal Cards used           ");
    rowDescriptions.add("Swap Cards used            ");
    
    String playerNames = "";
    ArrayList<Player> pl = Game.getInstance().getPlayers();
    ArrayList<String> rows = new ArrayList<String>();
    
    for(int i = 0; i<rowDescriptions.size();i++) {
      rows.add("");
    }
    for(int i = 0; i<pl.size(); i++) {
      playerNames = playerNames + pl.get(i).getName() + "\t";
      ArrayList<Integer > rawData = Game.calculateStatistics(pl.get(i));
      for(int j = 0; j<rawData.size(); j++) {
        rows.set(j,(rows.get(j) + rawData.get(j) + "\t"));
      }
    }
    
    System.out.println("\nStatistics              " + playerNames);
    for(int i = 0; i<rowDescriptions.size(); i++) {
      System.out.println(rowDescriptions.get(i) + rows.get(i));
    }
  }

  @Override
  public void showDialog(String text) {
    System.out.println(text);

  }

  /**
   * @param inputString display info
   * @param min         int boundary
   * @param max         int boundary
   * @return selected int option
   */
  public static int scanInt(String inputString, int min, int max) {
//  pasando un minimo y un maximo, devuelve int ingresado en ese rango
    boolean inRange = false;
    int outputInt = 0;
    inputScanner = new Scanner(System.in);
    while (!inRange) {
      System.out.println(inputString);
      outputInt = inputScanner.nextInt();
      if (outputInt <= max && outputInt >= min) {
        inRange = true;
      }
      if (!inRange) {
        System.out.println("Forbidden value, try again.");
      }
    }
    return outputInt;
  }

  @Override
  public String showSelectionMenu(String inputInfo, ArrayList<String> list) {
    System.out.println(inputInfo);

    System.out.println("[0] - Return");
    for (int i = 1; i <= list.size(); i++) {
      System.out.println("[" + i + "] - " + list.get(i-1));
    }
    int selection = scanInt("", 0, list.size());
    if (selection == 0) {
      showGame();
      return null;
    } else {
      return list.get(selection - 1);
    }
  }
}
