package pokemon.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import pokemon.p1.Pokemon.PType;

public class Pokemon {
	private String name;
	private int id;
	private int height;
	private int weight;
	private PType[] type = { PType.UNKNOWN };
	private String color;
	private int hp;
	private int attack;
	private int defense;
	private int spAtk;
	private int spDef;
	private int speed;
	private int accuracy;
	private int evasion;
	private int totalStat;
	private boolean legendary;
	private LinkedList<Integer> moveIDs = new LinkedList<Integer>();
	private HashMap<Integer, Move> moves = new HashMap<Integer, Move>();
	private HashMap<PType, Double> weaknesses = new HashMap<PType, Double>();
	private HashMap<PType, Double> strengths = new HashMap<PType, Double>();

	//returns total of all the stats
	public int getTotalStat() {
		totalStat = hp + attack + defense + spAtk + spDef + speed;
		return totalStat;
	}

	//Creates int rank for Pokemon based on 60% stats, 25% sum of top 10 move scores, 15% weaknesses/strengths ratio
	public int pokeRank() {
		totalStat = hp + attack + defense + spAtk + spDef + speed; //sets if hasn't been set yet

		// calculate sum of top 10 move scores
		int moveScore = 0;
		ArrayList<Move> topScoring = new ArrayList<Move>();
		for (Move m : moves.values()) {
			topScoring.add(m);
		}
		Collections.sort(topScoring, new Comparator<Move>() {
			public int compare(Move o1, Move o2) {
				if (o1.score() == o2.score())
					return 0;
				return o1.score() > o2.score() ? -1 : 1;
			}
		});
		int counter = 0;
		for (int i = 0; i < topScoring.size(); i++) {//in case pokemon has less than 10 moves for whatever reason
			if (counter < 10) {
				moveScore += topScoring.get(i).score();
				counter++;
			} else {
				break; //break after 10 moves
			}
		}
		
		// calculate ratio of weaknesses/strengths
		double balance = 0;
		for (PType pt : strengths.keySet()) {
			balance += strengths.get(pt);
		}

		for (PType pt : weaknesses.keySet()) {
			if (weaknesses.get(pt) == 0) {
				balance -= 2;
			} else {
				balance -= Math.pow(weaknesses.get(pt), -1);
			}
		}
		double finalScore = .6 * totalStat + .25 * moveScore + .15 * balance;
		return (int) finalScore;
	}

	public void setMoves(HashMap<Integer, Move> move) {
		for (int i : moveIDs) {
			moves.put(i, move.get(i));
		}
	}

	public HashMap<Integer, Move> getMoves() {
		return moves;
	}

	public enum PType {
		NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK, FAIRY, UNKNOWN, SHADOW
	}

	public void setAllRelations(ArrayList<HashMap<PType, Double>> typeChart) {
		strengths = typeChart.get(0);
		weaknesses = typeChart.get(1);
	}

	public HashMap<PType, Double> getWeaknesses() {
		return weaknesses;
	}

	public HashMap<PType, Double> getStrengths() {
		return strengths;
	}

	public LinkedList<Integer> getMoveIDs() {
		return moveIDs;
	}

	public void addMoveIDs(int moveID) {
		this.moveIDs.add(moveID);
	}

	public void setLegendary(boolean x){
		legendary = x;
	}
	
	public boolean isLegendary(){
		return legendary;
	}
	
	@Override
	public String toString() {
		return "Pokemon [name=" + name + ", id=" + id + ", height=" + height + ", weight=" + weight + ", type="
				+ Arrays.toString(type) + ", color=" + color + ", hp=" + hp + ", attack=" + attack + ", defense="
				+ defense + ", spAtk=" + spAtk + ", spDef=" + spDef + ", speed=" + speed + ", accuracy=" + accuracy
				+ ", evasion=" + evasion + ", totalStat=" + totalStat + ", legendary=" + legendary + ", moveIDs="
				+ moveIDs + ", moves=" + moves + ", weaknesses=" + weaknesses + ", strengths=" + strengths + "]";
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpAtk() {
		return spAtk;
	}

	public void setSpAtk(int spAtk) {
		this.spAtk = spAtk;
	}

	public int getSpDef() {
		return spDef;
	}

	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getEvasion() {
		return evasion;
	}

	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public PType[] getType() {
		return type;
	}

	public void setType(PType type) {
		if (this.type[0].equals(PType.UNKNOWN)) {
			this.type[0] = type;
		} else {
			PType[] newarr = { this.type[0], type };
			this.type = newarr;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
