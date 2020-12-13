package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Engine.Game;
import Engine.Player;

@SuppressWarnings("serial")
public class GUI_StatisticsWindow extends JFrame {

  public GUI_StatisticsWindow() {
    paintFrame();
  }


  public void paintFrame() {

    JPanel labelList = new JPanel();
    
    labelList.setLayout(new GridLayout(12,1));
    
    JLabel topRowLabel = new JLabel("Statistics", JLabel.CENTER);
    Font topRowFont = new Font("tahoma", Font.BOLD,14);
    topRowLabel.setFont(topRowFont);
    labelList.add(topRowLabel);
    labelList.add(new JLabel("Cards picked", JLabel.CENTER));
    labelList.add(new JLabel("Attack Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Drop Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Extra Cards used", JLabel.CENTER));;
    labelList.add(new JLabel("Heal Castle Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Heal Wall Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Kill Soldier Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Pierce Wall Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Soldiers activated", JLabel.CENTER));
    labelList.add(new JLabel("Steal Cards used", JLabel.CENTER));
    labelList.add(new JLabel("Swap Cards used", JLabel.CENTER));
    
    //este tiene 3/5, players +1
    ArrayList<JPanel> columnsList = new ArrayList<JPanel>();
    ArrayList<Player> pl = Game.getInstance().getPlayers();
    
    for(int i = 0; i<pl.size(); i++) {
      
      Player p = pl.get(i);
      ArrayList<Integer> stats = Game.calculateStatistics(p);
      JPanel rows = new JPanel();
      
      columnsList.add(new JPanel());     
      columnsList.get(i).add(rows);
      rows.setLayout(new GridLayout(12,1));

      JLabel nameLabel = new JLabel(p.getName(), JLabel.CENTER);
      nameLabel.setFont(topRowFont);
      rows.add(nameLabel);
      
      for(int j = 0; j<stats.size(); j++) {
        rows.add(new JLabel(""+stats.get(j), JLabel.CENTER));
      }
    }

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout());
    mainPanel.add(labelList);
    for(JPanel jp : columnsList) {
      mainPanel.add(jp);
    }   
    
    JLabel winningPlayerPanel = new JLabel("Player " + Game.getInstance().getActivePlayer().getName() + " won!", JLabel.CENTER);
    winningPlayerPanel.setFont(new Font("tahoma", Font.BOLD, 14));
    winningPlayerPanel.setBackground(Color.DARK_GRAY);
    winningPlayerPanel.setForeground(Color.WHITE);
    winningPlayerPanel.setOpaque(true);
    
    add(mainPanel);
    add(winningPlayerPanel, BorderLayout.NORTH);
    
    pack();
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("Castle Wars - Setup New Game");

  }
  
}
