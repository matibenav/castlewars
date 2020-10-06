package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.Serializable;
import java.util.ArrayList;

import javax.lang.model.util.Elements;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Engine.Game;
import Engine.Player;

@SuppressWarnings("serial")
public class GUI_CW_Win extends GUI_Frame {

	public  GUI_CW_Win()
	{
  	super(800, 500);
  	ArrayList<JPanel> playersInfo = new ArrayList<JPanel>();
  	
  	for(int i = 0; i<Game.getAmount_players(); i++) {
  	  
  	  JLabel nameJL = new JLabel(""+Game.getInstance().getPlayers().get(i).getName());
      JLabel cardsJL = new JLabel(""+Game.getInstance().getPlayers().get(i).getCards().size());
      JLabel soldiersJL = new JLabel(""+Game.getInstance().getPlayers().get(i).getSoldiers().size());
      JLabel hpJL = new JLabel(""+Game.getInstance().getPlayers().get(i).getCastle());
      JLabel wallJL = new JLabel(""+Game.getInstance().getPlayers().get(i).getWall());

      playersInfo.add(new JPanel());
      
      playersInfo.get(i).add(nameJL);
      playersInfo.get(i).add(cardsJL);
      playersInfo.get(i).add(soldiersJL);
      playersInfo.get(i).add(hpJL);
      playersInfo.get(i).add(wallJL);
      
      if(i%2==0) {
        playersInfo.get(i).setLayout(new GridLayout(1,5));
        setSize(new Dimension(200, 50));
        setMinimumSize(new Dimension(200, 50));
        if(i == 0)
          add(playersInfo.get(i), BorderLayout.SOUTH);
        else
          add(playersInfo.get(i), BorderLayout.NORTH);
      }
      else {
        playersInfo.get(i).setLayout(new GridLayout(5,1));
        setSize(new Dimension(50,200));
        setMinimumSize(new Dimension(50, 200));
        if(i == 1)
          add(playersInfo.get(i), BorderLayout.EAST);
        else
          add(playersInfo.get(i), BorderLayout.WEST);
        
      }
         
  	}

  	JButton useCard = new JButton ("Usar carta");
  	JButton pickCard = new JButton ("Levantar carta");
    add(useCard, BorderLayout.CENTER);
    add(pickCard, BorderLayout.WEST);
	  }
	}
