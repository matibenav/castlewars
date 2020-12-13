package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Engine.Game;

@SuppressWarnings("serial")
public class GUI_LoadWindow extends JFrame {

  public GUI_LoadWindow() {

    JButton loadGame = new JButton("Load");
    JButton exploreFiles = new JButton("Select file");
    JTextField pathJTx = new JTextField("C:\\CastleWars.txt");
    pathJTx.setPreferredSize(new Dimension(320, 40));

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(exploreFiles);
    buttonsPanel.add(loadGame);
    buttonsPanel.setLayout(new GridLayout(1, 2));

    add(pathJTx, BorderLayout.NORTH);
    add(buttonsPanel, BorderLayout.SOUTH);

    pack();
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("Castle Wars - Load menu");

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        Game.getInstance().showIntro();
      }
    });

    exploreFiles.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {

        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Load file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CastleWars.txt", "txt");
        f.setFileFilter(filter);
        f.showOpenDialog(null);
        if(f.getSelectedFile() != null) {
          pathJTx.setText("\\" + f.getSelectedFile());
        }
      }
    });

    loadGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        Game.getInstance().loadGame(pathJTx.getText());
        dispose();
      }
    });

  }

}