package Engine;

import Cards.Card;

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
  private ArrayList<Soldier> soldiers;
  private ArrayList<Card> cards;


  public Player (String name, ArrayList<Card> cards) {
    this.name = name;
    this.castle = 100;
    this.wall = 0;
    this.cards = cards;
  }

  // Card Effects
  public Card getCard (int position) {
    return this.cards.remove(position);
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
}
