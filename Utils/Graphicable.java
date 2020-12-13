package Utils;

import java.util.ArrayList;

public interface Graphicable {

  public void showIntro();

  public void showSettings();

  public void showGame();

  public void showLoadMenu();

  public void showSaveMenu();

  public void showStatistics();
  
  public void showDialog(String text);

  // usado para elegir jugador o cartas
  public String showSelectionMenu(String inputInfo, ArrayList<String> list);

}
