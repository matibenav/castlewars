package GUI_Windows;

import javax.swing.JFrame;
import Engine.Game;

@SuppressWarnings("serial")
public abstract class GUI_Frame extends JFrame
{ 
  public GUI_Frame(int x, int y, String title) {
    setSize(x, y);
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Castle Wars - " + title);
  }
}