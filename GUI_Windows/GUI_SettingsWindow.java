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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Engine.Game;
import Engine.Settings;
import Utils.ChangeSettingAction;

@SuppressWarnings("serial")
public class GUI_SettingsWindow extends JFrame {

  public GUI_SettingsWindow() {

    JPanel descriptions_jp = new JPanel();
    JPanel amounts_jp = new JPanel();
    JPanel less_jp = new JPanel();
    JPanel more_jp = new JPanel();
    JLabel image_jl = new JLabel();
    BufferedImage imageBufferedReader = null;
    File imageFile = new File("D:\\Google Drive\\Universidad\\2018 UP\\"
        + "2º cuatrimestre\\06 Programación 3\\proyectos\\" + "TrabajoPracticoBallanUnificado\\picSettings.jpg");
    try {
      imageBufferedReader = ImageIO.read(imageFile);
      image_jl = new JLabel(new ImageIcon(imageBufferedReader));
    } catch (IOException e) {
      image_jl = new JLabel("Error al cargar la imagen", SwingConstants.CENTER);
    }

    JPanel next_back_jp = new JPanel();
    JButton next_jb = new JButton("Next");
    JButton back_jb = new JButton("Back");
    next_back_jp.add(back_jb);
    next_back_jp.add(next_jb);

    JPanel settings_jp = new JPanel();
    settings_jp.add(descriptions_jp);
    settings_jp.add(amounts_jp);
    settings_jp.add(less_jp);
    settings_jp.add(more_jp);
    settings_jp.setLayout(new GridLayout(1, 4));

    add(image_jl, BorderLayout.NORTH);
    add(settings_jp, BorderLayout.CENTER);
    add(next_back_jp, BorderLayout.SOUTH);

    ArrayList<JLabel> descriptions = new ArrayList<JLabel>();
    ArrayList<JLabel> amounts = new ArrayList<JLabel>();

    descriptions.add(new JLabel("  # Players"));
    descriptions.add(new JLabel("  # Starting Cards"));
    descriptions.add(new JLabel("  # Cards - Attack"));
    descriptions.add(new JLabel("  # Cards - Heal Castle"));
    descriptions.add(new JLabel("  # Cards - Heal Wall"));
    descriptions.add(new JLabel("  # Cards - Pierce Wall"));
    descriptions.add(new JLabel("  # Cards - Drop Card"));
    descriptions.add(new JLabel("  # Cards - Steal Card"));
    descriptions.add(new JLabel("  # Cards - Swap Card"));
    descriptions.add(new JLabel("  # Cards - Extra Card"));
    descriptions.add(new JLabel("  # Cards - Soldier"));
    descriptions.add(new JLabel("  # Cards - Kill Soldier"));

    amounts.add(new JLabel("  " + Game.getAmount_players() + "  "));
    amounts.add(new JLabel("  " + Game.getStarting_Cards() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_attack() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_heal_castle() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_heal_wall() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_pierce_wall() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_drop_cards() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_steal_card() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_swap_card() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_extra_card() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_soldier() + "  "));
    amounts.add(new JLabel("  " + Game.getAmount_cards_kill_soldier() + "  "));

    ArrayList<JButton> less = new ArrayList<JButton>();
    ArrayList<JButton> more = new ArrayList<JButton>();
    
    for (int i = 0; i < amounts.size(); i++) {
      less.add(new JButton("  -  "));
      more.add(new JButton("  +  "));
      more.get(i).setSize(20, 20);
      more.get(i).setPreferredSize(getSize());
      less.get(i).setSize(20, 20);
      less.get(i).setPreferredSize(getSize());
      
      less.get(i).addActionListener(new ChangeSettingAction(i, amounts.get(i)));
      more.get(i).addActionListener(new ChangeSettingAction(i, amounts.get(i)));
      amounts.get(i).setHorizontalAlignment(JLabel.CENTER);
      descriptions.get(i).setToolTipText(Settings.CARDS.getDescription());
      descriptions_jp.add(descriptions.get(i));
      amounts_jp.add(amounts.get(i));
      less_jp.add(less.get(i));
      more_jp.add(more.get(i));
    }

    descriptions.get(0).setToolTipText(Settings.PLAYERS.getDescription());
    descriptions.get(1).setToolTipText(Settings.STARTING_CARDS.getDescription());
    descriptions_jp.setLayout(new GridLayout(descriptions.size(), 1));
    amounts_jp.setLayout(new GridLayout(amounts.size(), 1));
    less_jp.setLayout(new GridLayout(less.size(), 1));
    more_jp.setLayout(new GridLayout(more.size(), 1));

    pack();
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Castle Wars - Setup New Game");

    next_jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        dispose();
        inputPlayerNames();
      }
    });

    back_jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        Game.getInstance().showIntro();
        dispose();
      }
    });

  }

  private void inputPlayerNames() {
    JFrame playerNames_jf = new JFrame();
    playerNames_jf.setVisible(true);
    playerNames_jf.setSize(300, 400);
    playerNames_jf.setResizable(false);
    playerNames_jf.setLocationRelativeTo(null);
    playerNames_jf.setTitle("Player Name Menu");
    playerNames_jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    playerNames_jf.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        Game.getInstance().showSettings();
      }
    });

    ArrayList<JPanel> panels = new ArrayList<JPanel>();
    ArrayList<JTextField> playerTextfields = new ArrayList<JTextField>();
    JButton play_jb = new JButton("PLAY!");

    for (int i = 0; i < Game.getAmount_players(); i++) {

      JLabel player_name_jl = new JLabel("Player Name:");

      JTextField player_name_input_jt = new JTextField();
      player_name_jl.setHorizontalAlignment(JTextField.CENTER);
      player_name_input_jt.setHorizontalAlignment(JTextField.CENTER);
      panels.add(new JPanel());
      panels.get(i).add(player_name_jl);
      panels.get(i).add(player_name_input_jt);
      playerTextfields.add(player_name_input_jt);
      player_name_input_jt.setMinimumSize(new Dimension(150, 33));
      player_name_input_jt.setPreferredSize(new Dimension(150, 33));
      playerNames_jf.add(panels.get(i));
      if (i == 0) {
        player_name_input_jt.setText("You");
      }
      if (i == 1) {
        player_name_input_jt.setText("I");
      }
    }
    playerNames_jf.setLayout(new GridLayout(playerTextfields.size() + 1, 1));
    playerNames_jf.add(play_jb, BorderLayout.SOUTH);
    playerNames_jf.pack();

    play_jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        /**
         * Passes names to list and trims spaces. Sets up game with player's names.
         * Displays warning if empty or duplicate player name.
         */
        ArrayList<String> names = new ArrayList<String>();
        for (JTextField aux : playerTextfields) {
          names.add(aux.getText().trim());
        }
        boolean empty = false;
        for (String aux : names) {
          if (aux.equals("")) {
            empty = true;
          }
        }
        if (empty) {
          Game.getInstance().showDialog("Empty player name.");
        } else if (Game.checkDuplicatePlayers(names)) {
          Game.getInstance().showDialog("Duplicate player name.");
        } else {
          Game.getInstance().setupGame(names);
          playerNames_jf.dispose();
          dispose();
        }
      }
    });
  }

}