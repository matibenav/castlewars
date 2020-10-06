package Cards;

import java.util.Random;

import Engine.Player;
import Utils.Targeteable;

/**
 * 	   Card
 *     attr: rarity (70 / 20 / 10)
 *     target: { wall, castle, soldier, card }
 *     effect:
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

enum Rarity {
    COMMON,
    MEDIUM,
    RARE
}

public abstract class Card {
    public Rarity rarity;
  //  public Utils.Targeteable target;
 
    public abstract void effect(Player player);
    
    public Rarity generateRarity() {
      Random rand = new Random();
      Rarity r;
      int i = rand.nextInt(100);
      if(i < 70)
        r = Rarity.COMMON;
      else if(i>90)
        r = Rarity.RARE;
      else
        r = Rarity.MEDIUM;
      return r;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
      return "Card [rarity=" + rarity + "]";
    }


 /*   public void setTarget(Targeteable target) {
        this.target = target;
    }

*/


    
}

