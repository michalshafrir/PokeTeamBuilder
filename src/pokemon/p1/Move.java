package pokemon.p1;

import pokemon.p1.Pokemon.PType;

public class Move {
/**	 Overarching move score algorithm: power*pp*(accuracy/100)
 * 	 Actual algorithm with normalizing factors: 
	 score = (70% power, scaled by 100 + 30% pp/35) * accuracy rate
	 
	 Example: Hyperbeam's score would be:
	 (150/100*.7 + .3*5/35)*.9 =.987
	 Which is a strong score for a strong move
	 
	 Some moves, like self destruct, automatically have a score of 50
	 because their other effects are too negative
	 
	 This algorithm is used only if damage id class is physical/special
	 Status id moves are calculated with a flat rate at this point
	 since their effectiveness relies on player strategy
	 
	 Each move has a score, then Pokemon are defined by stats and the sum of 
	 the top 10 move scores (within Pokemon class)
	 */
	
	private int id;
	private String name;
	private PType type; 
	private int power = 0;
	private int pp;
	private int accuracy = 100;
	private DamageClass damageClass;

	public enum DamageClass {
		PHYSICAL, SPECIAL, STATUS
	}

	//Calculates move's score
	public int score() {
		double score = 0;
		if (damageClass == DamageClass.PHYSICAL || damageClass == DamageClass.SPECIAL) {
			if (id == 120) {// self destruct is a stupid move
				score = .5;
			} else if (id == 153 || id == 387) { //other move exceptions
				score = .8;
			} else {
				score = balancedScore(power, pp, accuracy);
			}
		} else if (damageClass == DamageClass.STATUS) {
			score = .4;
		}
		
		return (int) (score * 10);
	}
	
	//Main move score formula
	public double balancedScore(int power, int pp, int accuracy){
		return (((power * 1.0 / 100) * .7) + ((pp * 1.0 / 35) * .3)) * (accuracy * 1.0) / 100;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PType getType() {
		return type;
	}

	public void setType(PType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Move [id=" + id + ", name=" + name + ", type=" + type + ", power=" + power + ", pp=" + pp
				+ ", accuracy=" + accuracy + ", damageClass=" + damageClass + "]";
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public DamageClass getDamageClass() {
		return damageClass;
	}

	public void setDamageClass(DamageClass damageClass) {
		this.damageClass = damageClass;
	}

}
