package Engine;


import Cards.*;
import Utils.Rarity;

import java.util.ArrayList;
/**
 *     target: { wall, castle, soldier, card }
 *     Player
 *     attr: castle (100)
 *     attr: cards (5)
 *     attr: soldiers
 *     attr: wall (30)
 *
 *
 *         Attack:
 *             Usa | soldiers |
 *             target: { wall || castle }
 *         Heal Castle: { 10 / 20 / 30 }
 *         Heal Wall: { 5 , 10 , 15 }
 *         Pierce Wall:
 *             NO usa | soldiers | (Daño directo)
 *             target: wall
 *             damage: { 5, 10, 30 }
 *         Drop Card
 *             target: Card
 *             amount: { 1, 2, 3 }
 *         Steal Card
 *             target: Card
 *         Exchange Card (Random)
 *             target: Card
 *             amount: { 1, 2, 3 }
 *         Kill Soldier
 *             target: Soldiers
 *             amount: 1
 *         Soldier
 *         Turn:
 *             +1 Turno
 */

public class Player {
  
  private static final int maxWallHP = 30;
  private static final int maxCastleHP = 100;
  
  private String name;
  private int castleHP;
  private int wallHP;
  
  private boolean hasLost = false;
  
  private ArrayList<Card> cards;
  private ArrayList<Card_Soldier> soldiers;

  public Player (String name) {
    this.name = name;
    this.castleHP = maxCastleHP;
    this.wallHP = maxWallHP;
    this.cards = new ArrayList<Card>();
    this.soldiers = new ArrayList<Card_Soldier>();
  }

  // Card Effects
  
  public void pickCard() {
    cards.add(Game.getInstance().getDeck().getTopCard());
  }

  public Card takeCard (int position) {
    return this.cards.remove(position);
  }
  
  public Card exchangeRndCard(Card card) {
    int randomIndex = (int) (Math.random() * this.cards.size());
    Card exchangedCard = this.takeCard(randomIndex);
    this.cards.add(card);
    return exchangedCard;
  }
  
  public void discardCard(Card card) {
    Game.getInstance().getDeck().addCard(card);
  }

  public void addSoldier(Card_Soldier cs) {
    this.soldiers.add((Card_Soldier) takeCard(cards.indexOf(cs)));
  }
  
  public void removeSoldier(int position) {
    discardCard(this.soldiers.remove(position));
  }

  
  public int calculateDamage() {
    int damage = 0;
    for(Card_Soldier cs : soldiers) {
      Rarity soldRar = cs.getRarity();
      switch(soldRar) {
      
        case COMMON:
          damage = damage + 10;
          break;
          
        case MEDIUM:
          damage = damage + 25;
          break;
          
        case RARE:
          damage = damage + 70;
          break;
      }
    }
    return damage;
  }

  public void takeDamage(int damage, boolean wallOnly) {
    System.out.println("damage = "+ damage);
    System.out.println("initial castleHP = "+ castleHP);
    System.out.println("initial wallHP = "+ wallHP);

    int resultingDamage = 0;
    if(wallHP < damage)
      resultingDamage = damage - wallHP;
    
    setWallHP(wallHP - damage);
    if(!wallOnly && wallHP < damage) 
      setCastleHP(castleHP - resultingDamage);
    
    System.out.println("final castleHP = "+ castleHP);
    System.out.println("final wallHP = "+ wallHP);
  }

  public void restoreWallHP(int restoredHP) {
    setWallHP(wallHP + restoredHP);
  }
  
  public void restoreCastleHP(int restoredHP) {
    setCastleHP(castleHP + restoredHP);}
  
  // g & s
  
  private void setCastleHP(int castle) {
    this.castleHP = castle;
    if(castleHP <= 0) {
      castleHP = 0;
      hasLost = true;
      }
    if(castleHP > maxCastleHP)
      castleHP = maxCastleHP;
  }
  
  private void setWallHP(int wall) {
    this.wallHP = wall;
    if(wallHP < 0)
      wallHP = 0;
    if(wallHP > maxWallHP)
      castleHP = maxWallHP;
  }
  
  public String getName() {
    return name;
  }
  public int getCastleHP() {
    return castleHP;
  }
  public int getWallHP() {
    return wallHP;
  }
  public boolean hasLost() {
    return hasLost;
  }
  public ArrayList<Card> getCards() {
    return cards;
  }
  public ArrayList<Card_Soldier> getSoldiers() {
    return soldiers;
  }
 
}
