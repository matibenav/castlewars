package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Cards.Card;
import Cards.Card_Soldier;
import Engine.Game;
import Utils.PartialDisableComboBox;

@SuppressWarnings("serial")
public class GUI_CW_Win extends GUI_Frame {

  private ArrayList<JPanel> playersInfo;
  
  private JPanel playersJP;
  private JPanel gameJP ;
  private JPanel buttonsJP;
  
  private JLabel titleJL;
  
  private JTextArea gameLogJTA;
  
  private JScrollPane scrollJSC;
  
  private JButton useCard;
  private JButton pickCard;
  
  private ArrayList<Color> colors = new ArrayList<Color>()
  {{
    add(new Color(255,207,43));
    add(new Color(0,162,250));
    add(new Color(181,230,29));
    add(new Color(245,65,119));
  }};

  public GUI_CW_Win() {
    super(600, 600);
    paintFrame();
  }
  
  public void paintFrame() {
  
    getContentPane().removeAll();
    
    playersInfo = new ArrayList<JPanel>();
    playersJP = new JPanel();
    gameJP = new JPanel();
    
    add(playersJP, BorderLayout.WEST);
    add(gameJP);

  // game panel elements
    
    titleJL = new JLabel("Castle Wars");
    titleJL.setHorizontalAlignment(SwingConstants.CENTER);
    gameLogJTA = new JTextArea();
    scrollJSC = new JScrollPane(gameLogJTA);
    gameLogJTA.setEditable(false);
    gameLogJTA.setLineWrap(true);   
    
    buttonsJP = new JPanel();
    useCard = new JButton("Usar carta");
    pickCard = new JButton("Levantar carta");
    JButton suicide = new JButton("harakiri");
    JButton pass = new JButton("pass turn");
    buttonsJP.add(useCard);
    buttonsJP.add(pickCard);
    buttonsJP.add(suicide);
    buttonsJP.add(pass);
    buttonsJP.setLayout(new GridLayout(2,2));

    gameJP.setLayout(new BorderLayout());
    gameJP.add(titleJL, BorderLayout.NORTH);
    gameJP.add(scrollJSC, BorderLayout.CENTER);
    gameJP.add(buttonsJP, BorderLayout.SOUTH);

    //players panels
    
    for (int i = 0; i < Game.getAmount_players(); i++) {
      
      String name = Game.getInstance().getPlayers().get(i).getName();
      int castleHP = Game.getInstance().getPlayers().get(i).getCastleHP();
      int wallHP = Game.getInstance().getPlayers().get(i).getWallHP();
      int qCards = Game.getInstance().getPlayers().get(i).getCards().size();
      int qSoldiers = Game.getInstance().getPlayers().get(i).getSoldiers().size();
      
      JLabel hpJL = new JLabel("Castle HP: " + castleHP);
      JLabel wallJL = new JLabel("Wall HP: " + wallHP);
      JLabel cardsJL = new JLabel("# Cards: " + qCards);
      PartialDisableComboBox soldiersPDCB = new PartialDisableComboBox();
      
      if(hpJL.getText().equals("Castle HP: 0"))
        hpJL.setForeground(Color.red);
      
      playersInfo.add(new JPanel());
      playersInfo.get(i).add(hpJL);
      playersInfo.get(i).add(wallJL);
      playersInfo.get(i).add(cardsJL);
      playersInfo.get(i).add(soldiersPDCB);
      playersInfo.get(i).setLayout(new GridLayout(4, 1));
      playersInfo.get(i).setOpaque(true);
      playersInfo.get(i).setBackground(colors.get(i));
      soldiersPDCB.setOpaque(true);
      soldiersPDCB.setBackground(colors.get(i));
      
    //agrego la info del jugador al panel de jugadores
      playersJP.add(playersInfo.get(i));
      
    // agrego la cantidad de soldados que tenga el jugador al combobox
    // expandible (pero no seleccionable) para poder revisar cuantos soldados hay
      soldiersPDCB.addItem("# Soldiers: " + qSoldiers, false);
      for (Card_Soldier cs : Game.getInstance().getPlayers().get(i).getSoldiers())
        soldiersPDCB.addItem(cs, false);
      
    // cada panel de jugador tiene como titulo su nombre
    // si le toca a ese jugador, tiene borde negro, sino borde blanco
      TitledBorder playerNameTitle;
      Border border;
      Border soldierBorder;
      if (Game.getInstance().getPlayers().get(i) == Game.getInstance().getCurrentlyPlaying()) {
        border = BorderFactory.createLineBorder(Color.black,2);
        soldierBorder = BorderFactory.createLineBorder(Color.BLACK,1);
      } else {
        border = BorderFactory.createLineBorder(Color.white,2);
        soldierBorder = BorderFactory.createLineBorder(Color.WHITE,1);
      }
      soldiersPDCB.setBorder(soldierBorder);
    // si perdió, nombre y HP en rojo
      if(hpJL.getText().equals("Castle HP: 0")) {
        hpJL.setForeground(Color.red);
        playerNameTitle = BorderFactory.createTitledBorder
          (border ,"Player: " + name, 0,0, new Font("tahoma",Font.PLAIN,18), Color.red);
        }
      else
        playerNameTitle = BorderFactory.createTitledBorder
          (border ,"Player: " + name, 0,0, new Font("tahoma",Font.PLAIN,18));
      playersInfo.get(i).setBorder(playerNameTitle);
    }

    if (Game.getAmount_players() != 4) 
      playersJP.setLayout(new GridLayout(Game.getAmount_players(), 1));
    else {
      playersJP.setLayout(new GridLayout(2, 2));
    //cambio el 2 con el 3 asi parece circular el movimiento de turnos
      Component auxJP = playersJP.getComponent(3);
      playersJP.add(playersJP.getComponent(2),3);
      playersJP.add(auxJP,2);
      }
    
  // action listeners de los botones
    
    pickCard.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      Game.getInstance().getCurrentlyPlaying().pickCard();
      Game.getInstance().nextPlayer();
      }
    });
   
    useCard.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      ArrayList <String> cards = new ArrayList<String>();
      for(Card c : Game.getInstance().getCurrentlyPlaying().getCards()) 
        cards.add(c.toString());
      Card c = Game.getInstance().getGraphics().selectCard();
      if (c != null) {
        c.effect();
        paintFrame();
        }
      }
    });

    suicide.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      Game.getInstance().getCurrentlyPlaying().takeDamage(1500, false);
      }
    });
    
    pass.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      //Game.getInstance().getCurrentlyPlaying().setHasLost(true);
     // Game.getInstance().getCurrentlyPlaying().setCastleHP(0);
      Game.getInstance().nextPlayer();
      }
    });
 
  }

}
