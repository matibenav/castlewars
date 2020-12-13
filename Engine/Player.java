package Engine;


import Cards.*;
import Utils.CardComparator;
import Utils.CardRarities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
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
 *         Swap Card (Random)
 *             target: Card
 *             amount: { 1, 2, 3 }
 *         Kill Soldier
 *             target: Soldiers
 *             amount: 1
 *         Soldier
 *         Turn:
 *             +1 Turno
 */

public class Player implements Serializable {
  
  private static final long serialVersionUID = -1142568048817574097L;

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
    
    //esto para que se haga mas rapida la prueba inicial
    Random r = new Random();
    int initialPower = r.nextInt(100);
    if(initialPower > 80) {
      soldiers.add(new Card_Soldier());
      soldiers.add(new Card_Soldier());
      soldiers.get(0).setCardRarities(CardRarities.RARE);
      soldiers.get(1).setCardRarities(CardRarities.MEDIUM);
    }else if(initialPower > 50) {
      soldiers.add(new Card_Soldier());
      soldiers.add(new Card_Soldier());
      soldiers.add(new Card_Soldier());
      soldiers.get(0).setCardRarities(CardRarities.COMMON);
      soldiers.get(1).setCardRarities(CardRarities.COMMON);
      soldiers.get(2).setCardRarities(CardRarities.MEDIUM);
    } else {
      soldiers.add(new Card_Soldier());
      soldiers.get(0).setCardRarities(CardRarities.RARE);
    }
  }
  
  /**
   * @param turnDescription what was done in that turn
   * @param pickedCard if player picked card, true
   * @param cardPlayed if player played card, true
   *cardPlayed and pickedCard can't be both true
   */
  public void playTurn(String turnDescription, boolean pickedCard, Card cardPlayed) {
    Game.getInstance().registerTurn(new Turn(this, turnDescription, pickedCard, cardPlayed));
  }

   /**
    * sorts Cards & soldiers
    */
  public void sortCards() {
    cards.sort(new CardComparator());
    soldiers.sort(new CardComparator());
  }

  /**
   * @return sum of soldiers' damage capacity
   */
  public int calculateDamage() {
    int damage = 0;
    for(Card_Soldier cs : soldiers) {
      CardRarities soldRar = cs.getCardRarities();
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
  
  /**
   * picks card from main deck <br>
   * adds it to cards 
   */
  public void pickCard() {
    cards.add(Game.getInstance().getDeck().getTopCard());
  }
  
  /**
   * @param card to remove from deck
   * @return card removed from deck
   */
  public Card takeCard (Card card) {
    return this.cards.remove(cards.indexOf(card));
  }
  
  /**
   * @param  card to give
   * @return swapped card
   **/
  public Card swapRandomCard(Card card) {
    int randomIndex = (int) (Math.random() * this.cards.size());
    Card swappedCard = cards.remove(randomIndex);
    if(card != null) {
      this.cards.add(card);
    }
    return swappedCard;
  }
  /**
   * @param card removed from cards & added to main deck
   */
  public void discardCard(Card card) {
    Game.getInstance().getDeck().addCard(takeCard(card));
  }

  /**
   * @param cs removes soldier from cards & adds it to soldiers
   */
  public void addSoldier(Card_Soldier cs) {
    this.soldiers.add((Card_Soldier) takeCard(cs));
  }

  /**
   * @param cs removes soldier from soldiers & adds it to deck
   */
  public void removeSoldier(int position) {
    Game.getInstance().getDeck().addCard(this.soldiers.remove(position));
  }
  
  /**
   * @param damage points to deduct from caslte/wall
   * @param wallOnly true value for wall only attacks
   */
  public void takeDamage(int damage, boolean wallOnly) {
    int resultingDamage = 0;
    if(wallHP < damage) {
      resultingDamage = damage - wallHP;
    }
    setWallHP(wallHP - damage);
    if(!wallOnly && wallHP < damage) {
      setCastleHP(castleHP - resultingDamage);
    }
  }

  /**
   * @param restoredHP HP to restore to wall
   */
  public void restoreWallHP(int restoredHP) {
    setWallHP(wallHP + restoredHP);
  }

  /**
   * @param restoredHP HP to restore to castel
   */
  public void restoreCastleHP(int restoredHP) {
    setCastleHP(castleHP + restoredHP);
  }
  
  // setters
  
  /**
   * @param castleHP value to set to castleHP
   * <br> if higher than max value, sets it to max
   * <br> if lower than 0, sets it to 0 & player loses
   */
  private void setCastleHP(int castleHP) {
    this.castleHP = castleHP;
    if(this.castleHP <= 0) {
      this.castleHP = 0;
      this.hasLost = true;
    }
    if(this.castleHP > maxCastleHP) {
      this.castleHP = maxCastleHP;
    }
  }
  /**
   * @param wallHP value to set to wallHP
   * <br> if higher than max value, sets it to max
   * <br> if lower than 0, sets it to 0
   */
  private void setWallHP(int wallHP) {
    this.wallHP = wallHP;
    if(this.wallHP < 0) {
      this.wallHP = 0;
    }
    if(this.wallHP > maxWallHP) {
      this.wallHP = maxWallHP;
    }
  }
  
  // getters 
  
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
