package pokemon.p1;

import pokemon.p1.Pokemon.PType;

/**
 * Class to parse and sort the input parameter arguments into something more
 * usable by the teambuilder rankpokemon method
 */
public class SortParam {
	private String color = null;
	private String stat = null;
	private PType pt = null;
	private boolean legendaries = true;

	private static SortParam instance = new SortParam();

	public static SortParam getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "SortParam [color=" + color + ", stat=" + stat + ", pt=" + pt + "]";
	}

	public void initSortParam(String[] args) {
		// java doesn't support switch case on strings... :(

		for (String s : args) {

			/** Type Argument Parsing */
			s = s.toUpperCase();
			if (s.equals("NORMAL")) {
				pt = PType.NORMAL;
			} else if (s.equals("FIGHTING")) {
				pt = PType.FIGHTING;
			} else if (s.equals("FLYING")) {
				pt = PType.FLYING;
			} else if (s.equals("POISON")) {
				pt = PType.POISON;
			} else if (s.equals("GROUND")) {
				pt = PType.GROUND;
			} else if (s.equals("ROCK")) {
				pt = PType.ROCK;
			} else if (s.equals("BUG")) {
				pt = PType.BUG;
			} else if (s.equals("GHOST")) {
				pt = PType.GHOST;
			} else if (s.equals("STEEL")) {
				pt = PType.STEEL;
			} else if (s.equals("FIRE")) {
				pt = PType.FIRE;
			} else if (s.equals("WATER")) {
				pt = PType.WATER;
			} else if (s.equals("GRASS")) {
				pt = PType.GRASS;
			} else if (s.equals("ELECTRIC")) {
				pt = PType.ELECTRIC;
			} else if (s.equals("PSYCHIC")) {
				pt = PType.PSYCHIC;
			} else if (s.equals("ICE")) {
				pt = PType.ICE;
			} else if (s.equals("DRAGON")) {
				pt = PType.DRAGON;
			} else if (s.equals("DARK")) {
				pt = PType.DARK;
			} else if (s.equals("FAIRY")) {
				pt = PType.FAIRY;
			}

			/** Stat parsing */

			if (s.equals("HP") || s.equals("ATTACK") || s.equals("DEFENSE") || s.equals("SPATTACK") || s.equals("SPDEFENSE") || s.equals("SPEED")) {
				stat = s;
			}

			/** Color Parse */
			s = s.toLowerCase();
			if (s.equals("red")) {
				color = s;
			} else if (s.equals("blue")) {
				color = s;
			} else if (s.equals("black")) {
				color = s;
			} else if (s.equals("brown")) {
				color = s;
			} else if (s.equals("gray")) {
				color = s;
			} else if (s.equals("green")) {
				color = s;
			} else if (s.equals("pink")) {
				color = s;
			} else if (s.equals("purple")) {
				color = s;
			} else if (s.equals("white")) {
				color = s;
			} else if (s.equals("yellow")) {
				color = s;
			}

		}
	}

	public int numParam() {
		int x = 0;
		if (color != null) {
			x++;
		}
		if (stat != null) {
			x++;
		}
		if (pt != null) {
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
