package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Cards.Card;
import Cards.Card_Soldier;
import Engine.Game;
import Engine.Player;
import Engine.Turn;
import Utils.PartiallyDisabledComboBox;

@SuppressWarnings("serial")
public class GUI_GameWindow extends JFrame {

  private final ArrayList<Color> colors = new ArrayList<Color>() {
    {
      add(new Color(255, 207, 43));
      add(new Color(0, 80, 150));
      add(new Color(181, 230, 29));
      add(new Color(245, 65, 119));
    }
  };
  
  public GUI_GameWindow() {
    paintFrame();
  }

  public void paintFrame() {

    getContentPane().removeAll();

    ArrayList<JPanel> playersInfo = new ArrayList<JPanel>();
    JPanel playersJP = new JPanel();
    JPanel gameJP = new JPanel();

    add(playersJP, BorderLayout.WEST);
    add(gameJP);

    // game panel components

    JLabel titleJL = new JLabel("It's "+Game.getInstance().getActivePlayer().getName()+"'s turn!", SwingConstants.CENTER);
    titleJL.setFont(new Font("tahoma", Font.PLAIN, 18));
    titleJL.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea gameLogJTA = new JTextArea();
    JScrollPane scrollJSC = new JScrollPane(gameLogJTA);
    gameLogJTA.setEditable(false);
    gameLogJTA.setLineWrap(true);
    gameLogJTA.setFont(new Font("tahoma", Font.PLAIN, 14));
    scrollJSC.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollJSC.setPreferredSize(new Dimension(450, 350));
    for (Turn t : Game.getInstance().getTurnHistory()) {
      gameLogJTA.append(t.getDescription() + "\n");
    }
    JPanel buttonsJP = new JPanel();
    JButton stats = new JButton("Statistics");
    JButton newGame = new JButton("New Game");
    JButton useCard = new JButton("Use card");
    JButton pickCard = new JButton("Pick card");
    JButton saveGame = new JButton("Save Game");
    JButton console  = new JButton ("Console mode");
    if (!Game.getInstance().checkActivePlayerWon()) {
      buttonsJP.add(useCard);
      buttonsJP.add(pickCard);
      buttonsJP.add(saveGame);
      buttonsJP.add(console);
    } else {
      buttonsJP.add(stats);
      buttonsJP.add(newGame);
    }
    buttonsJP.setLayout(new GridLayout(1, 3));

    gameJP.setLayout(new BorderLayout());
    gameJP.add(titleJL, BorderLayout.NORTH);
    gameJP.add(scrollJSC, BorderLayout.CENTER);
    gameJP.add(buttonsJP, BorderLayout.SOUTH);

    // player panels

    ArrayList<Player> pl = Game.getInstance().getPlayers();
    for (int i = 0; i < pl.size(); i++) {

      pl.get(i).sortCards();
      String name = pl.get(i).getName();
      int castleHP = pl.get(i).getCastleHP();
      int wallHP = pl.get(i).getWallHP();
      int qCards = pl.get(i).getCards().size();
      int attackPower = pl.get(i).calculateDamage();
      int qSoldiers = pl.get(i).getSoldiers().size();
      JLabel hpJL = new JLabel("Castle HP: " + castleHP + " HP");
      JLabel wallJL = new JLabel("Wall HP: " + wallHP + " HP");
      JLabel powerJL = new JLabel("Attack power: " + attackPower + " HP");
      JLabel cardsJL = new JLabel("# Cards: " + qCards);
      PartiallyDisabledComboBox soldiersPDCB = new PartiallyDisabledComboBox();
      soldiersPDCB.setPreferredSize(new Dimension(160, 25));
      if (hpJL.getText().equals("Castle HP: 0")) {
        hpJL.setForeground(Color.red);
      }
      playersInfo.add(new JPanel());
      playersInfo.get(i).add(hpJL);
      playersInfo.get(i).add(wallJL);
      playersInfo.get(i).add(powerJL);
      playersInfo.get(i).add(cardsJL);
      playersInfo.get(i).add(soldiersPDCB);
      playersInfo.get(i).setLayout(new GridLayout(5, 1));
      playersInfo.get(i).setOpaque(true);
      playersInfo.get(i).setBackground(colors.get(i));

      Color oppositeColor;

      if (i % 2 == 0) {
        oppositeColor = colors.get(i + 1);
      } else {
        oppositeColor = colors.get(i - 1);
      }
      hpJL.setForeground(oppositeColor);
      wallJL.setForeground(oppositeColor);
      cardsJL.setForeground(oppositeColor);
      powerJL.setForeground(oppositeColor);
      soldiersPDCB.setForeground(oppositeColor);
      soldiersPDCB.setOpaque(true);
      soldiersPDCB.setBackground(colors.get(i));
      // add info to containing panel
      playersJP.add(playersInfo.get(i));
      

      // agrego la cantidad de soldados que tenga el jugador al combobox
      // expandible (pero no seleccionable) para poder revisar cuantos soldados hay
      soldiersPDCB.addItem("# Soldiers: " + qSoldiers, false);
      for (Card_Soldier cs : pl.get(i).getSoldiers()) {
        soldiersPDCB.addItem(cs, false);
      }
      // cada panel de jugador tiene como titulo su nombre
      // si le toca a ese jugador, tiene borde negro, sino borde blanco
      TitledBorder playerNameTitle;
      Border border;
      Border soldierBorder;
      
      if (pl.get(i) == Game.getInstance().getActivePlayer()) {
        
        titleJL.setBackground(colors.get(i));
        titleJL.setOpaque(true);
        titleJL.setForeground(oppositeColor);
        
        border = BorderFactory.createLineBorder(Color.black, 2);
        soldierBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        playerNameTitle = BorderFactory.createTitledBorder(border, "Player: " + name, 0, 0,
            new Font("tahoma", Font.PLAIN, 18), Color.BLACK);
      } else {
        border = BorderFactory.createLineBorder(Color.white, 2);
        soldierBorder = BorderFactory.createLineBorder(Color.WHITE, 1);
        playerNameTitle = BorderFactory.createTitledBorder(border, "Player: " + name, 0, 0,
            new Font("tahoma", Font.PLAIN, 18), Color.WHITE);
        
      }
      soldiersPDCB.setBorder(soldierBorder);
      // si perdió, nombre y HP en rojo
      if (hpJL.getText().equals("Castle HP: 0")) {
        hpJL.setForeground(Color.red);
        playerNameTitle = BorderFactory.createTitledBorder(border, "Player: " + name, 0, 0,
            new Font("tahoma", Font.PLAIN, 18), Color.red);
      } 
      playersInfo.get(i).setBorder(playerNameTitle);
    }

    if (Game.getAmount_players() != 4) {
      playersJP.setLayout(new GridLayout(Game.getAmount_players(), 1));
    } else {
      playersJP.setLayout(new GridLayout(2, 2));
      // cambio el 2 con el 3 asi parece circular el movimiento de turnos
      Component auxJP = playersJP.getComponent(3);
      playersJP.add(playersJP.getComponent(2), 3);
      playersJP.add(auxJP, 2);
    }

    pack();
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("Castle Wars");

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (Game.getInstance().checkActivePlayerWon()) {
          System.exit(0);
        }
        int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save the game before closing?",
            "Save before closing?", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
          Game.getInstance().showSaveMenu();
        } else {
          System.exit(0);
        }
      }
    });

    // action listeners de los botones

    Player ap = Game.getInstance().getActivePlayer();
    
    pickCard.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        ap.pickCard();
        ap.playTurn("picked a card from the deck", true, null);
        Game.getInstance().nextPlayer();
      }
    });

    useCard.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        ap.sortCards();
        ArrayList<String> cards = new ArrayList<String>();
        for (Card c : ap.getCards())
          cards.add(c.toString());
        Card c = Game.getInstance().showCardSelectionMenu();
        if (c != null) {
          c.effect();
          Game.getInstance().showGame();
        }
      }
    });

    saveGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        Game.getInstance().showSaveMenu();
      }
    });

    newGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        dispose();
        Game.getInstance().showIntro();
      }
    });

    console.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        dispose();
        Game.getInstance().switchGraphicable();
        Game.getInstance().showGame();
      }
    });

    stats.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        Game.getInstance().showStatistics();
      }
    });

  }

}
