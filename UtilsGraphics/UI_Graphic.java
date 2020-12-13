package UtilsGraphics;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import GUI_Windows.*;
import Utils.Graphicable;

public class UI_Graphic implements Graphicable {

  private GUI_GameWindow gameFrame;

  private GUI_StatisticsWindow statsFrame;

  @Override
  public void showGame() {
    if (gameFrame == null) {
      gameFrame = new GUI_GameWindow();
    } else {
      gameFrame.paintFrame();
    }
  }

  @Override
  public void showIntro() {
    @SuppressWarnings("unused")
    GUI_IntroWindow intro = new GUI_IntroWindow();
  }

  @Override
  public void showSettings() {
    @SuppressWarnings("unused")
    GUI_SettingsWindow settings = new GUI_SettingsWindow();
  }

  @Override
  public void showLoadMenu() {
    @SuppressWarnings("unused")
    GUI_LoadWindow load = new GUI_LoadWindow();

  }

  @Override
  public void showSaveMenu() {
    @SuppressWarnings("unused")
    GUI_SaveWindow save = new GUI_SaveWindow();

  }

  public void showStatistics() {
    if (statsFrame == null) {
      statsFrame = new GUI_StatisticsWindow();
    } else {
      statsFrame.paintFrame();
    }
  }

  public void showDialog(String text) {
    JOptionPane.showMessageDialog(null, text, "", JOptionPane.WARNING_MESSAGE);
  }

  public String showSelectionMenu(String inputInfo, ArrayList<String> list) {
    /**
     * dado un arraylist construye un vector de strings del mismo tamaño (por
     * requisito del JOptionPane) si el usuario cierra la ventana se obtiene null
     */
    String[] options = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      options[i] = list.get(i);
    }
    String selection = (String) JOptionPane.showInputDialog(null, inputInfo, "", JOptionPane.PLAIN_MESSAGE, null,
        options, null);
    if ((selection != null) && selection.length() > 0) {
      return selection;
    } else {
      return null;
    }
  }

}