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

@SuppressWarnings("serial")
public class GUI_IntroWin extends GUI_Frame 
{
  public GUI_IntroWin() {
	  
	super(600, 600);
    
	BufferedImage imageBufferedReader = null; 
	JLabel imageJLabel = new JLabel();
    File imageFile = new File
	  ( "D:\\Google Drive\\Universidad\\2018 UP\\"
	  + "2º cuatrimestre\\06 Programación 3\\proyectos\\"
	  + "TrabajoPracticoBallanUnificado\\picIntro.jpg" );       
  try {
	  imageBufferedReader = ImageIO.read(imageFile);
	  imageJLabel = new JLabel(new ImageIcon(imageBufferedReader));}
  catch (IOException e) {
      imageJLabel = new JLabel("Error al cargar la imagen" , SwingConstants.CENTER);	
  }	
    
	JPanel optionsJPanel = new JPanel();
	JButton createGameJButton = new JButton("Crear partida");
	JButton loadGameJButton = new JButton("Cargar a partida");
	JButton consoleModeJButton = new JButton("Modo Consola");
	
	optionsJPanel.setLayout(new GridLayout());
  optionsJPanel.add(createGameJButton);
  optionsJPanel.add(loadGameJButton);
  optionsJPanel.add(consoleModeJButton);
  
  add(imageJLabel, BorderLayout.NORTH);
  add(optionsJPanel, BorderLayout.SOUTH);
  pack();
  
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

    Game.getInstance().loadGame();
	  dispose();
	
  }
});

  consoleModeJButton.addActionListener(new ActionListener() { 
      @Override
	  public void actionPerformed(ActionEvent arg0) {	

      dispose();
      Game.getInstance().switchGraphicable();
    //  Game.getInstance().showIntro();
		
	  }
	});

  }

}