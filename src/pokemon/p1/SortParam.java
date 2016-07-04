package pokemon.p1;

import pokemon.p1.Pokemon.PType;

/**Class to parse and sort the input parameter arguments into
 * something more usable by the teambuilder rankpokemon method
*/
public class SortParam {
	private String color = null;
	private String stat = null;
	private PType pt = null;
	private boolean legendaries = true;
	
	private static SortParam instance = new SortParam();
	public static SortParam getInstance(){
		return instance;
	}
	
	@Override
	public String toString() {
		return "SortParam [color=" + color + ", stat=" + stat + ", pt=" + pt + "]";
	}
	
	public void initSortParam(String[] args){
		//java doesn't support switch case on strings... :(
		
		for (String s: args){
			
			/**Type Argument Parsing*/	
			
			if (s.toUpperCase().equals("NORMAL")) {
				pt = PType.NORMAL;
			} else if (s.toUpperCase().equals("FIGHTING")){
				pt = PType.FIGHTING;
			} else if (s.toUpperCase().equals("FLYING")){
				pt = PType.FLYING;
			} else if (s.toUpperCase().equals("POISON")){
				pt = PType.POISON;
			} else if (s.toUpperCase().equals("GROUND")){
				pt = PType.GROUND;
			} else if (s.toUpperCase().equals("ROCK")){
				pt = PType.ROCK;
			} else if (s.toUpperCase().equals("BUG")){
				pt = PType.BUG;
			} else if (s.toUpperCase().equals("GHOST")){
				pt = PType.GHOST;
			} else if (s.toUpperCase().equals("STEEL")){
				pt = PType.STEEL;
			} else if (s.toUpperCase().equals("FIRE")){
				pt = PType.FIRE;
			} else if (s.toUpperCase().equals("WATER")){
				pt = PType.WATER;
			} else if (s.toUpperCase().equals("GRASS")){
				pt = PType.GRASS;
			} else if (s.toUpperCase().equals("ELECTRIC")){
				pt = PType.ELECTRIC;
			} else if (s.toUpperCase().equals("PSYCHIC")){
				pt = PType.PSYCHIC;
			} else if (s.toUpperCase().equals("ICE")){
				pt = PType.ICE;
			} else if (s.toUpperCase().equals("DRAGON")){
				pt = PType.DRAGON;
			} else if (s.toUpperCase().equals("DARK")){
				pt = PType.DARK;
			} else if (s.toUpperCase().equals("FAIRY")){
				pt = PType.FAIRY;
			}
			
			/**Stat parsing*/
			
			if (s.toUpperCase().equals("HP")){
				stat = s.toUpperCase();
			} else if (s.toUpperCase().equals("ATTACK")){
				stat = s.toUpperCase();
			} else if (s.toUpperCase().equals("DEFENSE")){
				stat = s.toUpperCase();
			} else if (s.toUpperCase().equals("SPATTACK")){
				stat = s.toUpperCase();
			} else if (s.toUpperCase().equals("SPDEFENSE")){
				stat = s.toUpperCase();
			} else if (s.toUpperCase().equals("SPEED")){
				stat = s.toUpperCase();
			}
			
			/**Color Parse*/
			if (s.toLowerCase().equals("red")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("blue")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("black")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("brown")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("gray")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("green")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("pink")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("purple")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("white")){
				color = s.toLowerCase();
			} else if (s.toLowerCase().equals("yellow")){
				color = s.toLowerCase();
			}
			
			
		}
	}
	
	public int numParam(){
		int x = 0;
		if(color != null){
			x++;
		}
		if(stat != null){
			x ++;		
		} 
		if(pt != null){
			x++;
		}
		return x;
	}


	public String getColor() {
		return color;
	}

	public String getStat() {
		return stat;
	}

	public PType getPt() {
		return pt;
	}

	public boolean isLegendaries() {
		return legendaries;
	}

	public void setLegendaries(boolean legendaries) {
		this.legendaries = legendaries;
	}



}
