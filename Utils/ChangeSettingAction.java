package Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Engine.Game;

public class ChangeSettingAction implements ActionListener {

  private int index;
  private JLabel settingJLabel;

  public ChangeSettingAction(int index, JLabel settingJLabel) {
    this.index = index;
    this.settingJLabel = settingJLabel;
  }

  public void actionPerformed(ActionEvent ae) {
    JButton button = (JButton) ae.getSource();
    boolean result = true, isSum = true;
    int auxSum = 1, currentValue = 0;
    if (button.getText().equals("  -  ")) {
      auxSum = -1;
      isSum = false;
    }
    switch (index) {
    case 0:
      currentValue = Game.getAmount_players();
      result = Game.setAmount_players(currentValue + auxSum);
      break;
    case 1:
      currentValue = Game.getStarting_Cards();
      result = Game.setStarting_Cards(currentValue + auxSum);
      break;
    case 2:
      currentValue = Game.getAmount_cards_attack();
      result = Game.setAmount_cards_attack(currentValue + auxSum);
      break;
    case 3:
      currentValue = Game.getAmount_cards_heal_castle();
      result = Game.setAmount_cards_heal_castle(currentValue + auxSum);
      break;
    case 4:
      currentValue = Game.getAmount_cards_heal_wall();
      result = Game.setAmount_cards_heal_wall(currentValue + auxSum);
      break;
    case 5:
      currentValue = Game.getAmount_cards_pierce_wall();
      result = Game.setAmount_cards_pierce_wall(currentValue + auxSum);
      break;
    case 6:
      currentValue = Game.getAmount_cards_drop_cards();
      result = Game.setAmount_cards_drop_cards(currentValue + auxSum);
      break;
    case 7:
      currentValue = Game.getAmount_cards_steal_card();
      result = Game.setAmount_cards_steal_card(currentValue + auxSum);
      break;
    case 8:
      currentValue = Game.getAmount_cards_swap_card();
      result = Game.setAmount_cards_swap_card(currentValue + auxSum);
      break;
    case 9:
      currentValue = Game.getAmount_cards_extra_card();
      result = Game.setAmount_cards_extra_card(currentValue + auxSum);
      break;
    case 10:
      currentValue = Game.getAmount_cards_soldier();
      result = Game.setAmount_cards_soldier(currentValue + auxSum);
      break;
    case 11:
      currentValue = Game.getAmount_cards_kill_soldier();
      result = Game.setAmount_cards_kill_soldier(currentValue + auxSum);
      break;
    }
    if (result) {
      currentValue = currentValue + auxSum;
      settingJLabel.setText("  " + (currentValue) + "  ");
    } else {
      if (isSum) {
        Game.getInstance().showDialog("Reached maximum value");
      } else {
        Game.getInstance().showDialog("Reached minimum value");
      }
    }

  }

}
