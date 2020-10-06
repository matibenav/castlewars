package GUI_Windows;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class GUI_Frame extends JFrame
{ 
  public GUI_Frame(int x, int y) {
    setSize(x, y);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}