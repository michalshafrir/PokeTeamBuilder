package pokemon.p1;

import pokemon.p1.Pokemon.PType;

public class Move {
	//good move score algorithm: power*pp*(accuracy/100)
	//maybe /10 for scale?
	//more emphasis on power than pp... score = 70% power, scaled by most power u
	//could have /100 ?
	//30% pp ... *pp/35 or whatever the max pp is?
	//accuracy /100 multiplied by it all
	//(150/100*.7 + .3*5/35)*.9 gives hyperbeam a .987 which is solid
	//make an exception for self destruct, auto give it 50 ??
	//only if damage id class is physical/special, not defenseive
	//moves have scores, then pokemon are defined by stats and if they have good move score
	private int id;
	private String name;
	private PType type; //just one type per move not array !
	private int power = 0;
	private int pp;
	private int accuracy = 100;
	private DamageClass damageClass; //2 is physical, 1 is status, 3 is special
	public enum DamageClass {
		PHYSICAL, SPECIAL, STATUS
	}

	public int score() {
		double score = 0;
		if(damageClass == DamageClass.PHYSICAL){
			if(id == 120){//self destruct is a stupid move
				score = .5;
			}else if(id == 153 || id == 387){
				score = .8;
			}else{
				score = (((power*1.0/100)*.7)+((pp*1.0/35)*.3))*(accuracy*1.0)/100;
			}
		}else if(damageClass == DamageClass.STATUS){
			score = .4;
		}else{
			score = (((power*1.0/100)*.7)+((pp*1.0/35)*.3))*(accuracy*1.0)/100;
		}
		return (int) (score*10);
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
