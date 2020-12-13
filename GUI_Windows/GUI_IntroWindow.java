package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Engine.Game;

import javax.swing.JFrame;

@SuppressWarnings("serial")

public class GUI_IntroWindow extends JFrame {

  JLabel imageJLabel;
  JPanel optionsJPanel ;
  JButton createGameJButton; 
  JButton loadGameJButton;
  JButton consoleModeJButton;
  
  public GUI_IntroWindow() {
    File imageFile = new File("D:\\Google Drive\\Universidad\\2018 UP\\"
        + "2º cuatrimestre\\06 Programación 3\\proyectos\\" + "TrabajoPracticoBallanUnificado\\picIntro.jpg");
    try {
      BufferedImage imageBufferedReader;
      imageBufferedReader = ImageIO.read(imageFile);
      imageJLabel = new JLabel(new ImageIcon(imageBufferedReader));
    } catch (IOException e) {
      imageJLabel = new JLabel("Error loading image", SwingConstants.CENTER);
    }

    optionsJPanel = new JPanel();
    createGameJButton = new JButton("New Game");
    loadGameJButton = new JButton("Load Game");
    consoleModeJButton = new JButton("Console Mode");
    optionsJPanel.setLayout(new GridLayout());
    optionsJPanel.add(createGameJButton);
    optionsJPanel.add(loadGameJButton);
    optionsJPanel.add(consoleModeJButton);

    add(imageJLabel, BorderLayout.NORTH);
    add(optionsJPanel, BorderLayout.SOUTH);

    pack();
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Castle Wars - Intro");

    createGameJButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Game.getInstance().showSettings();
        dispose();
      }
    });

    loadGameJButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        /*
         * try { Game.getInstance().loadGame(); } catch (IOException e) {
         * e.printStackTrace(); Game.getInstance().showDialog("Could not load game."); }
         * Game.getInstance().showGame();
         */
        Game.getInstance().showLoadMenu();
        dispose();
      }
    });

    consoleModeJButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        dispose();
        Game.getInstance().switchGraphicable();
        Game.getInstance().showIntro();
      }
    });

  }

}