package GUI_Windows;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Engine.Game;
import Engine.Settings;

@SuppressWarnings("serial")
public class GUI_SettingsWin extends GUI_Frame {
  
  public GUI_SettingsWin () {
    super(620,620, "Setup New Game");
    
    JPanel descriptions_jp = new JPanel();
    JPanel amounts_jp = new JPanel();
    JPanel more_jp = new JPanel();
    JPanel less_jp = new JPanel();

    JLabel image_jl= new JLabel();
    BufferedImage imageBufferedReader = null; 
    File imageFile = new File
      ("D:\\Google Drive\\Universidad\\2018 UP\\"
      +"2º cuatrimestre\\06 Programación 3\\proyectos\\"
      +"TrabajoPracticoBallanUnificado\\picSettings.jpg");       
    try {
      imageBufferedReader = ImageIO.read(imageFile);
      image_jl = new JLabel(new ImageIcon(imageBufferedReader));}
    catch (IOException e) {
      image_jl = new JLabel("Error al cargar la imagen" , SwingConstants.CENTER);  
    } 
    
    JPanel next_back_jp = new JPanel();
    JButton next_jb = new JButton("Next");
    JButton back_jb = new JButton("Back");
    next_back_jp.add(back_jb);
    next_back_jp.add(next_jb);
    
    JPanel settings_jp = new JPanel();
    settings_jp.add(descriptions_jp);
    settings_jp.add(amounts_jp);
    settings_jp.add(more_jp);
    settings_jp.add(less_jp);

    settings_jp.setLayout(new GridLayout(1,4));

    add(image_jl, BorderLayout.NORTH);
    add(settings_jp, BorderLayout.CENTER);
    add(next_back_jp, BorderLayout.SOUTH);
    
    descriptions_jp.setLayout(new GridLayout(12,1));
    amounts_jp.setLayout(new GridLayout(12,1));
    more_jp.setLayout(new GridLayout(12,1));
    less_jp.setLayout(new GridLayout(12,1));

    JLabel players_jl = new JLabel("  Players");   
    JLabel starting_cards_jl = new JLabel("  Starting Cards");   
    JLabel cards_attack_jl = new JLabel("  # Cards - Attack");   
    JLabel cards_heal_castle_jl = new JLabel("  # Cards - Heal Castle");
    JLabel cards_heal_wall_jl = new JLabel("  # Cards - Heal Wall");   
    JLabel cards_pierce_wall_jl = new JLabel("  # Cards - Pierce Wall");   
    JLabel cards_drop_card_jl = new JLabel("  # Cards - Drop Card");
    JLabel cards_steal_card_jl = new JLabel("  # Cards - Steal Card");   
    JLabel cards_exchange_card_jl = new JLabel("  # Cards - Exchange Card");
    JLabel cards_extra_card_jl = new JLabel("  # Cards - Extra Card");
    JLabel cards_soldier_jl = new JLabel("  # Cards - Soldier");
    JLabel cards_kill_soldier_jl = new JLabel("  # Cards - Kill Soldier");
    players_jl.setToolTipText(Settings.PLAYERS.getDescription());
    starting_cards_jl.setToolTipText(Settings.STARTING_CARDS.getDescription());
    cards_attack_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_heal_castle_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_heal_wall_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_pierce_wall_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_drop_card_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_drop_card_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_steal_card_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_exchange_card_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_extra_card_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_soldier_jl.setToolTipText(Settings.CARDS.getDescription());
    cards_kill_soldier_jl.setToolTipText(Settings.CARDS.getDescription());
    
    JLabel amount_players_jl = new JLabel("  " + Game.getAmount_players() + "  " , JLabel.CENTER);   
    JLabel amount_starting_cards_jl = new JLabel("  " + Game.getStarting_Cards() + "  " , JLabel.CENTER);   
    JLabel amount_cards_attack_jl = new JLabel("  " + Game.getAmount_cards_attack() + "  " , JLabel.CENTER);  
    JLabel amount_cards_heal_castle_jl = new JLabel("  " + Game.getAmount_cards_heal_castle() + "  " , JLabel.CENTER);  
    JLabel amount_cards_heal_wall_jl = new JLabel("  " + Game.getAmount_cards_heal_wall() + "  " , JLabel.CENTER);  
    JLabel amount_cards_pierce_wall_jl = new JLabel("  " + Game.getAmount_cards_pierce_wall() + "  " , JLabel.CENTER);  
    JLabel amount_cards_drop_card_jl = new JLabel("  " + Game.getAmount_cards_drop_cards() + "  " , JLabel.CENTER); 
    JLabel amount_cards_steal_card_jl = new JLabel("  " + Game.getAmount_cards_steal_card() + "  " , JLabel.CENTER); 
    JLabel amount_cards_exchange_card_jl = new JLabel("  " + Game.getAmount_cards_exchange_card() + "  " , JLabel.CENTER); 
    JLabel amount_cards_extra_card_jl = new JLabel("  " + Game.getAmount_cards_extra_card() + "  " , JLabel.CENTER); 
    JLabel amount_cards_soldier_jl = new JLabel("  " + Game.getAmount_cards_soldier() + "  " , JLabel.CENTER); 
    JLabel amount_cards_kill_soldier_jl = new JLabel("  " + Game.getAmount_cards_kill_soldier() + "  " , JLabel.CENTER); 

    JButton more_players_jb = new JButton("  +  " );   
    JButton more_starting_cards_jb = new JButton("  +  " );
    JButton more_cards_attack_jb = new JButton("  +  " ); 
    JButton more_cards_heal_castle_jb = new JButton("  +  " ); 
    JButton more_cards_heal_wall_jb = new JButton("  +  " );  
    JButton more_cards_pierce_wall_jb = new JButton("  +  " );
    JButton more_cards_drop_card_jb = new JButton("  +  " ); 
    JButton more_cards_steal_card_jb = new JButton("  +  " ); 
    JButton more_cards_exchange_card_jb = new JButton("  +  " ); 
    JButton more_cards_extra_card_jb = new JButton("  +  " ); 
    JButton more_cards_soldier_jb = new JButton("  +  " ); 
    JButton more_cards_kill_soldier_jb = new JButton("  +  " ); 

    JButton less_players_jb = new JButton("  -  " );   
    JButton less_starting_cards_jb = new JButton("  -  " );
    JButton less_cards_attack_jb = new JButton("  -  " ); 
    JButton less_cards_heal_castle_jb = new JButton("  -  " ); 
    JButton less_cards_heal_wall_jb = new JButton("  -  " );  
    JButton less_cards_pierce_wall_jb = new JButton("  -  " );
    JButton less_cards_drop_card_jb = new JButton("  -  " ); 
    JButton less_cards_steal_card_jb = new JButton("  -  " ); 
    JButton less_cards_exchange_card_jb = new JButton("  -  " ); 
    JButton less_cards_extra_card_jb = new JButton("  -  " ); 
    JButton less_cards_soldier_jb = new JButton("  -  " ); 
    JButton less_cards_kill_soldier_jb = new JButton("  -  ");

    descriptions_jp.add(players_jl);
    descriptions_jp.add(starting_cards_jl);
    descriptions_jp.add(cards_attack_jl);
    descriptions_jp.add(cards_heal_castle_jl);
    descriptions_jp.add(cards_heal_wall_jl);
    descriptions_jp.add(cards_pierce_wall_jl);
    descriptions_jp.add(cards_drop_card_jl);
    descriptions_jp.add(cards_steal_card_jl);
    descriptions_jp.add(cards_exchange_card_jl);
    descriptions_jp.add(cards_extra_card_jl);
    descriptions_jp.add(cards_soldier_jl);
    descriptions_jp.add(cards_kill_soldier_jl);
    
    amounts_jp.add(amount_players_jl);
    amounts_jp.add(amount_starting_cards_jl);
    amounts_jp.add(amount_cards_attack_jl);
    amounts_jp.add(amount_cards_heal_castle_jl);
    amounts_jp.add(amount_cards_heal_wall_jl);
    amounts_jp.add(amount_cards_pierce_wall_jl);
    amounts_jp.add(amount_cards_drop_card_jl);
    amounts_jp.add(amount_cards_steal_card_jl);
    amounts_jp.add(amount_cards_exchange_card_jl);
    amounts_jp.add(amount_cards_extra_card_jl);
    amounts_jp.add(amount_cards_soldier_jl);
    amounts_jp.add(amount_cards_kill_soldier_jl);

    more_jp.add(more_players_jb);
    more_jp.add(more_starting_cards_jb);
    more_jp.add(more_cards_attack_jb);
    more_jp.add(more_cards_heal_castle_jb);
    more_jp.add(more_cards_heal_wall_jb);
    more_jp.add(more_cards_pierce_wall_jb);
    more_jp.add(more_cards_drop_card_jb);
    more_jp.add(more_cards_steal_card_jb);
    more_jp.add(more_cards_exchange_card_jb);
    more_jp.add(more_cards_extra_card_jb);
    more_jp.add(more_cards_soldier_jb);
    more_jp.add(more_cards_kill_soldier_jb);
    
    less_jp.add(less_players_jb);
    less_jp.add(less_starting_cards_jb);
    less_jp.add(less_cards_attack_jb);
    less_jp.add(less_cards_heal_castle_jb);
    less_jp.add(less_cards_heal_wall_jb);
    less_jp.add(less_cards_pierce_wall_jb);
    less_jp.add(less_cards_drop_card_jb);
    less_jp.add(less_cards_steal_card_jb);
    less_jp.add(less_cards_exchange_card_jb);
    less_jp.add(less_cards_extra_card_jb);
    less_jp.add(less_cards_soldier_jb);
    less_jp.add(less_cards_kill_soldier_jb);

    
    next_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      dispose();
      setPlayerNames();
      }
    });
    
    back_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
        Game.getInstance().showIntro();
        dispose();
      }
    });

    class moreButton implements ActionListener{  
      public void actionPerformed(ActionEvent e){  
      //  tf.setText("Welcome to Javatpoint.");  
      
        
      }  
    }

    
    more_players_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(!Game.setAmount_players(Game.getAmount_players() + 1))
        maxWarning();
      else
        amount_players_jl.setText("  " +Game.getAmount_players()+"  ");
      }
    });

    more_starting_cards_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(!Game.setStarting_Cards(Game.getStarting_Cards() + 1))
        maxWarning();
      else
        amount_starting_cards_jl.setText("  " +Game.getStarting_Cards()+"  ");
      }
    });

    more_cards_attack_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_attack(Game.getAmount_cards_attack() + 1))
        maxWarning();
      else
        amount_cards_attack_jl.setText("  " +Game.getAmount_cards_attack()+"  ");
       }
    });

    more_cards_heal_wall_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_heal_wall(Game.getAmount_cards_heal_wall() + 1))
        maxWarning();
      else
        amount_cards_heal_wall_jl.setText("  " +Game.getAmount_cards_heal_wall()+"  ");
      }
    });

    more_cards_heal_castle_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_heal_castle(Game.getAmount_cards_heal_castle() + 1))
        maxWarning();
      else
        amount_cards_heal_castle_jl.setText("  " +Game.getAmount_cards_heal_castle()+"  ");
      }
    });

    more_cards_pierce_wall_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_pierce_wall(Game.getAmount_cards_pierce_wall() + 1))
        maxWarning();
      else
        amount_cards_pierce_wall_jl.setText("  " +Game.getAmount_cards_pierce_wall()+"  ");
      }
    });

    more_cards_drop_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_drop_cards(Game.getAmount_cards_drop_cards() + 1))
        maxWarning();
      else
        amount_cards_drop_card_jl.setText("  " +Game.getAmount_cards_drop_cards()+"  ");
      }
    });

    more_cards_steal_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_steal_card(Game.getAmount_cards_steal_card() + 1))
        maxWarning();
      else
        amount_cards_steal_card_jl.setText("  " +Game.getAmount_cards_steal_card()+"  ");
      }
    });

    more_cards_exchange_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_exchange_card(Game.getAmount_cards_exchange_card() + 1))
        maxWarning();
      else
        amount_cards_exchange_card_jl.setText("  " +Game.getAmount_cards_exchange_card()+"  ");
      }
    });

    more_cards_extra_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_extra_card(Game.getAmount_cards_extra_card() + 1))
        maxWarning();
      else
        amount_cards_extra_card_jl.setText("  " +Game.getAmount_cards_extra_card()+"  ");
      }
    });

    more_cards_soldier_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_soldier(Game.getAmount_cards_soldier() + 1))
        maxWarning();
      else
        amount_cards_soldier_jl.setText("  " +Game.getAmount_cards_soldier()+"  ");
      }
    });

    more_cards_kill_soldier_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_kill_soldier(Game.getAmount_cards_kill_soldier() + 1))
        maxWarning();
      else
        amount_cards_kill_soldier_jl.setText("  " +Game.getAmount_cards_kill_soldier()+"  ");
      }
    });

    
    less_players_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(!Game.setAmount_players(Game.getAmount_players() - 1))
        minWarning();
      else
        amount_players_jl.setText("  " +Game.getAmount_players()+"  ");
      }
    });

    less_starting_cards_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(!Game.setStarting_Cards(Game.getStarting_Cards() - 1))
        minWarning();
      else
        amount_starting_cards_jl.setText("  " +Game.getStarting_Cards()+"  ");
      }
    });

    less_cards_attack_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_attack(Game.getAmount_cards_attack() - 1))
        minWarning();
      else
        amount_cards_attack_jl.setText("  " +Game.getAmount_cards_attack()+"  ");
       }
    });

    less_cards_heal_castle_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_heal_castle(Game.getAmount_cards_heal_castle() - 1))
        minWarning();
      else
        amount_cards_heal_castle_jl.setText("  " +Game.getAmount_cards_heal_castle()+"  ");
      }
    });
 
    less_cards_heal_wall_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_heal_wall(Game.getAmount_cards_heal_wall() - 1))
        minWarning();
      else
        amount_cards_heal_wall_jl.setText("  " +Game.getAmount_cards_heal_wall()+"  ");
      }
    });

    less_cards_pierce_wall_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_pierce_wall(Game.getAmount_cards_pierce_wall() - 1))
        minWarning();
      else
        amount_cards_pierce_wall_jl.setText("  " +Game.getAmount_cards_pierce_wall()+"  ");
      }
    });

    less_cards_drop_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_drop_cards(Game.getAmount_cards_drop_cards() - 1))
        minWarning();
      else
        amount_cards_drop_card_jl.setText("  " +Game.getAmount_cards_drop_cards()+"  ");
      }
    });

    less_cards_steal_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_steal_card(Game.getAmount_cards_steal_card() - 1))
        minWarning();
      else
        amount_cards_steal_card_jl.setText("  " +Game.getAmount_cards_steal_card()+"  ");
      }
    });

    less_cards_exchange_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_exchange_card(Game.getAmount_cards_exchange_card() - 1))
        minWarning();
      else
        amount_cards_exchange_card_jl.setText("  " +Game.getAmount_cards_exchange_card()+"  ");
      }
    });

    less_cards_extra_card_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_extra_card(Game.getAmount_cards_extra_card() - 1))
        minWarning();
      else
        amount_cards_extra_card_jl.setText("  " +Game.getAmount_cards_extra_card()+"  ");
      }
    });

    less_cards_soldier_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_soldier(Game.getAmount_cards_soldier() - 1))
        minWarning();
      else
        amount_cards_soldier_jl.setText("  " +Game.getAmount_cards_soldier()+"  ");
      }
    });

    less_cards_kill_soldier_jb.addActionListener(new ActionListener() 
    {
    @Override
    public void actionPerformed(ActionEvent arg0) 
      {
      if(! Game.setAmount_cards_kill_soldier(Game.getAmount_cards_kill_soldier() - 1))
        minWarning();
      else
        amount_cards_kill_soldier_jl.setText("  " +Game.getAmount_cards_kill_soldier()+"  ");
      }
    }); 
  }
  
  public void maxWarning() {
    JOptionPane.showMessageDialog(null,
        "Valor máximo alcanzado",
        "Advertencia",
        JOptionPane.WARNING_MESSAGE);
  }  

  public void minWarning() {
    JOptionPane.showMessageDialog(null,
        "Valor mínimo alcanzado",
        "Advertencia",
        JOptionPane.WARNING_MESSAGE);
  }
  
  public void duplicateWarning() {
    JOptionPane.showMessageDialog(null,
        "Nombre de jugador repetido",
        "Advertencia",
        JOptionPane.WARNING_MESSAGE);
  }
  
  public void emptyWarning() {
    JOptionPane.showMessageDialog(null,
        "Nombre de jugador vacío",
        "Advertencia",
        JOptionPane.WARNING_MESSAGE);
  }
  
  public void setPlayerNames() {
    JFrame playerNames_jf = new JFrame();
    playerNames_jf.setVisible(true);
    playerNames_jf.setSize(300, 300);
    playerNames_jf.setLocationRelativeTo(null);
    playerNames_jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    playerNames_jf.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        Game.getInstance().showSettings();
      }
    });
    
    ArrayList<JPanel> panels = new ArrayList<JPanel>();
    ArrayList<JTextField> playerTextfields = new ArrayList<JTextField>();
    JButton play_jb = new JButton ("PLAY!");

    for (int i  = 0; i<Game.getAmount_players();i++) {
      JLabel player_name_jl = new JLabel("Player Name:");
      JTextField player_name_input_jt = new JTextField();
      panels.add(new JPanel());
      panels.get(i).add(player_name_jl);
      panels.get(i).add(player_name_input_jt);
      playerTextfields.add(player_name_input_jt);
      player_name_input_jt.setMinimumSize(new Dimension(80, 33));
      player_name_input_jt.setPreferredSize(new Dimension(80, 33));
      playerNames_jf.add(panels.get(i));
    }
    playerNames_jf.setLayout(new GridLayout(playerTextfields.size()+1,1));
    playerNames_jf.add(play_jb, BorderLayout.SOUTH);
    playerNames_jf.pack();
    
    play_jb.addActionListener(new ActionListener() 
    {
    @Override
      public void actionPerformed(ActionEvent arg0) 
        {
        // paso los nombres a un array y le saco los espacios
        ArrayList<String> names = new ArrayList<String>();
        for(JTextField aux : playerTextfields) 
          names.add(aux.getText().trim());

        // reviso que ninguno esté vacio
        boolean empty = false;
        for(String aux : names) 
          if(aux.equals(""))
            empty = true;
        
        if(empty || Game.checkDuplicatePlayers(names)) {
          if(empty)
            emptyWarning();
          else
            duplicateWarning();
        } else {  
          Game.getInstance().setupGame(names);
          playerNames_jf.dispose();
          dispose();
        }
      }
    });
  }
}