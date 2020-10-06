package UtilsGraphics;

import GUI_Windows.*;
import Utils.Graphicable;

public class Graphics_GUI implements Graphicable {
  
	@Override
	public void showGame() {
	  // TODO Auto-generated method stub
	  
	  GUI_CW_Win game = new GUI_CW_Win();
	}

	@Override
	public void showIntro() {
	  // TODO Auto-generated method stub
	  GUI_IntroWin intro = new GUI_IntroWin();
	}
	
	@Override
	public void showSettings() {
	  // TODO Auto-generated method stub
	  GUI_SettingsWin settings = new GUI_SettingsWin();
	}
	
	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		
	}

  @Override
  public void saveGame() {
    // TODO Auto-generated method stub
    
  }
  
}
