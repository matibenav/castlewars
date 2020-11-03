package Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Cards.*;
import Utils.Rarity;

public class Deck {
	
  private ArrayList<Card> cards = new ArrayList<Card>();

  public Deck() {
    
    for(int i = 0; i<Game.getAmount_cards_attack();i++) {
      Card_Attack c = new Card_Attack();
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_heal_castle();i++) {
      Card_HealCastle c = new Card_HealCastle();
      c.setRarity(generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_heal_wall();i++) {
      Card_HealWall c = new Card_HealWall();
      c.setRarity(generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_pierce_wall();i++) {
      Card_PierceWall c = new Card_PierceWall();
      c.setRarity(generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_drop_cards();i++) {
      Card_DropCards c = new Card_DropCards();
      c.setRarity(generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_steal_card();i++) {
      Card_StealCard c = new Card_StealCard();
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_exchange_card();i++) {
      Card_ExchangeCard c = new Card_ExchangeCard();
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_extra_card();i++) {
      Card_ExtraCard c = new Card_ExtraCard();
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_soldier();i++) {
      Card_Soldier c = new Card_Soldier();
      c.setRarity(generateRarity());
      cards.add(c);
    }
    
    for(int i = 0; i<Game.getAmount_cards_kill_soldier();i++) {
      Card_Kill_Soldier c = new Card_Kill_Soldier();
      cards.add(c);
    }
  }
  
  //cuando cargo juego
  public Deck(ArrayList<Card> cards){
    this.cards = cards;
  }
  
  public Rarity generateRarity() {
    Random rand = new Random();
    int i = rand.nextInt(100);
    Rarity r;
    if(i < 70)
      r = Rarity.COMMON;
    else if(i>90)
      r = Rarity.RARE;
    else
      r = Rarity.MEDIUM;
    return r;
  }

  public void dealCards() {
    for(Player p : Game.getInstance().getPlayers()) {
    for(int i = 0; i<Game.getStarting_Cards();i++) 
      p.pickCard();
    }
  }
  
  public void addCard(Card card) {
    cards.add(card);
  }
  
  public Card getTopCard () {
    return this.cards.remove(0);
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }
  
}
