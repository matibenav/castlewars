package Engine;

import Cards.*;

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
 *             Attack:
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
  private String name;
  private int castle;
  private int wall;
  private ArrayList<Card> cards;
  private ArrayList<Soldier> soldiers;
  private ArrayList<Card_Soldier> soldierCards;

  public Player (String name, ArrayList<Card> cards) {
    this.name = name;
    this.castle = 100;
    this.wall = 0;
    this.cards = cards;
this.soldiers = new ArrayList<Soldier>();
this.soldierCards = new ArrayList<Card_Soldier>();
  }

  // Card Effects
  public Card getCard (int position) {
    return this.cards.remove(position);
  }

  @Override
  public String toString() {
    return "Player [name=" + name + ", castle=" + castle + ", wall=" + wall + ", cards=" + cards + ", soldiers="
        + soldiers + "]";
  }

  public void removeSolder() {
    this.soldiers.remove(this.soldiers.size());
  }

  public void addSoldier() {
    this.soldiers.add(new Soldier());
  }

  public Card ExchangeCard(Card card) {
    int randomIndex = (int) (Math.random() * this.cards.size());
    Card exchangedCard = this.getCard(randomIndex);
    this.cards.add(card);
    return exchangedCard;
  }

  // g & s
  


  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the castle
   */
  public int getCastle() {
    return castle;
  }

  /**
   * @param castle the castle to set
   */
  public void setCastle(int castle) {
    this.castle = castle;
  }

  /**
   * @return the wall
   */
  public int getWall() {
    return wall;
  }

  /**
   * @param wall the wall to set
   */
  public void setWall(int wall) {
    this.wall = wall;
  }

  /**
   * @return the cards
   */
  public ArrayList<Card> getCards() {
    return cards;
  }

  /**
   * @param cards the cards to set
   */
  public void setCards(ArrayList<Card> cards) {
    this.cards = cards;
  }

  /**
   * @return the soldiers
   */
  public ArrayList<Soldier> getSoldiers() {
    return soldiers;
  }

  /**
   * @param soldiers the soldiers to set
   */
  public void setSoldiers(ArrayList<Soldier> soldiers) {
    this.soldiers = soldiers;
  }

  /**
   * @return the soldierCards
   */
  public ArrayList<Card_Soldier> getSoldierCards() {
    return soldierCards;
  }

  /**
   * @param soldierCards the soldierCards to set
   */
  public void setSoldierCards(ArrayList<Card_Soldier> soldierCards) {
    this.soldierCards = soldierCards;
  }

}
