package GUI_Windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Engine.Game;

@SuppressWarnings("serial")
public class GUI_SaveWindow extends JFrame {

  public GUI_SaveWindow() {

    JButton saveGame = new JButton("Save");
    JButton exploreFiles = new JButton("Select save folder");
    JTextField pathJTx = new JTextField("C:\\CastleWars.txt");
    pathJTx.setPreferredSize(new Dimension(320, 40));

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(exploreFiles);
    buttonsPanel.add(saveGame);
    buttonsPanel.setLayout(new GridLayout(1, 2));
    add(buttonsPanel, BorderLayout.SOUTH);
    add(pathJTx, BorderLayout.NORTH);

    pack();
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("Castle Wars - Save menu");

    exploreFiles.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Save folder");
        f.showOpenDialog(null);

        if (f.getSelectedFile() != null) {
          pathJTx.setText("" + f.getSelectedFile());
        }
      }
    });

    saveGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        Game.getInstance().saveGame(pathJTx.getText());
        dispose();
      }
    });
  }

}
