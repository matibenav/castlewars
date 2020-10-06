package Engine;

import java.util.ArrayList;
import java.util.Collections;

import Cards.*;

public class Deck {
	
  protected ArrayList<Card> cards = new ArrayList<Card>();


  public Deck() {
    for(int i = 0; i<Game.getAmount_cards_attack();i++) {
      Card_Attack c = new Card_Attack();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_heal_castle();i++) {
      Card_HealCastle c = new Card_HealCastle();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_heal_wall();i++) {
      Card_HealWall c = new Card_HealWall();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_pierce_wall();i++) {
      Card_HealWall c = new Card_HealWall();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_drop_card();i++) {
      Card_DropCards c = new Card_DropCards();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_steal_card();i++) {
      Card_StealCard c = new Card_StealCard();
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_exchange_card();i++) {
      Card_ExchangeCards c = new Card_ExchangeCards();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_extra_card();i++) {
      Card_ExtraCard c = new Card_ExtraCard();
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_soldier();i++) {
      Card_Soldier c = new Card_Soldier();
      c.setRarity(c.generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_kill_soldier();i++) {
      Card_Kill_Soldier c = new Card_Kill_Soldier();
      cards.add(c);
    }
  }

  
  public void shuffle() {
    Collections.shuffle(cards);
  }

  
  
  public ArrayList<Card> getCards() {
    return cards;
  }

  public void setCards(ArrayList<Card> cards) {
    this.cards = cards;
  }

  
  
  @Override
  public String toString() {
    return "Deck [cards=" + cards.toString() + "]";
  }
  
}
