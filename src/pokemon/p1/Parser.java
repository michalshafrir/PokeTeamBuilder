package pokemon.p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pokemon.p1.Move.DamageClass;
import pokemon.p1.Pokemon.PType;

public class Parser {
	/**Parser class to get data from CSV files and store into Pokemon and Move objects in the Dex & Moves hashMaps
	 * Also parses type relations into hashmaps for referencing later
	 * */
	private static Parser instance = new Parser();

	public static Parser getInstance() {
		return instance;
	}

	private HashMap<Integer, Pokemon> DEX = new HashMap<Integer, Pokemon>();
	private HashMap<Integer, Move> MOVES = new HashMap<Integer, Move>();
	// Type check: key is the attack move type -> map of defense type -> damage
	// factor (percent)
	private HashMap<PType, HashMap<PType, Double>> TYPECHECK = new HashMap<PType, HashMap<PType, Double>>();
	private HashMap<PType, HashMap<PType, Double>> RELATION = new HashMap<PType, HashMap<PType, Double>>();

	private FileReader fileReader;
	private BufferedReader bufferedReader;

	//Parse for creating initial Pokemon Objects and placing in the Dex
	public void mainParse(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {
					String[] arr = line.split(",");

					Pokemon p = new Pokemon();
					p.setId(Integer.parseInt(arr[0]));
					p.setName(arr[1]);
					p.setHeight(Integer.parseInt(arr[3]));
					p.setWeight(Integer.parseInt(arr[4]));
					if (Integer.parseInt(arr[0]) <= 721) {
						DEX.put(Integer.parseInt(arr[0]), p);
					}
					p.setLegendary(Integer.parseInt(arr[8]) == 1);
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	//Parser for Pokemon Types 
	public void parseType(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {
					String[] arr = line.split(",");
					if (Integer.parseInt(arr[0]) <= 721) {
						Pokemon p = DEX.get(Integer.parseInt(arr[0]));
						PType type = null;
						switch (Integer.parseInt(arr[1])) {
						case 1:
							type = PType.NORMAL;
							break;
						case 2:
							type = PType.FIGHTING;
							break;
						case 3:
							type = PType.FLYING;
							break;
						case 4:
							type = PType.POISON;
							break;
						case 5:
							type = PType.GROUND;
							break;
						case 6:
							type = PType.ROCK;
							break;
						case 7:
							type = PType.BUG;
							break;
						case 8:
							type = PType.GHOST;
							break;
						case 9:
							type = PType.STEEL;
							break;
						case 10:
							type = PType.FIRE;
							break;
						case 11:
							type = PType.WATER;
							break;
						case 12:
							type = PType.GRASS;
							break;
						case 13:
							type = PType.ELECTRIC;
							break;
						case 14:
							type = PType.PSYCHIC;
							break;
						case 15:
							type = PType.ICE;
							break;
						case 16:
							type = PType.DRAGON;
							break;
						case 17:
							type = PType.DARK;
							break;
						case 18:
							type = PType.FAIRY;
							break;
						case 10001:
							type = PType.UNKNOWN;
							break;
						case 10002:
							type = PType.SHADOW;
							break;
						}
						p.setType(type);
						DEX.put(Integer.parseInt(arr[0]), p);
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	//Parser for the effects of one offense type against type of defending pokemon
	public void parseTypeEffects(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {
					String[] arr = line.split(",");
					PType attack = null;
					switch (Integer.parseInt(arr[0])) {
					case 1:
						attack = PType.NORMAL;
						break;
					case 2:
						attack = PType.FIGHTING;
						break;
					case 3:
						attack = PType.FLYING;
						break;
					case 4:
						attack = PType.POISON;
						break;
					case 5:
						attack = PType.GROUND;
						break;
					case 6:
						attack = PType.ROCK;
						break;
					case 7:
						attack = PType.BUG;
						break;
					case 8:
						attack = PType.GHOST;
						break;
					case 9:
						attack = PType.STEEL;
						break;
					case 10:
						attack = PType.FIRE;
						break;
					case 11:
						attack = PType.WATER;
						break;
					case 12:
						attack = PType.GRASS;
						break;
					case 13:
						attack = PType.ELECTRIC;
						break;
					case 14:
						attack = PType.PSYCHIC;
						break;
					case 15:
						attack = PType.ICE;
						break;
					case 16:
						attack = PType.DRAGON;
						break;
					case 17:
						attack = PType.DARK;
						break;
					case 18:
						attack = PType.FAIRY;
						break;
					case 10001:
						attack = PType.UNKNOWN;
						break;
					case 10002:
						attack = PType.SHADOW;
						break;
					}

					PType def = null;
					switch (Integer.parseInt(arr[1])) {
					case 1:
						def = PType.NORMAL;
						break;
					case 2:
						def = PType.FIGHTING;
						break;
					case 3:
						def = PType.FLYING;
						break;
					case 4:
						def = PType.POISON;
						break;
					case 5:
						def = PType.GROUND;
						break;
					case 6:
						def = PType.ROCK;
						break;
					case 7:
						def = PType.BUG;
						break;
					case 8:
						def = PType.GHOST;
						break;
					case 9:
						def = PType.STEEL;
						break;
					case 10:
						def = PType.FIRE;
						break;
					case 11:
						def = PType.WATER;
						break;
					case 12:
						def = PType.GRASS;
						break;
					case 13:
						def = PType.ELECTRIC;
						break;
					case 14:
						def = PType.PSYCHIC;
						break;
					case 15:
						def = PType.ICE;
						break;
					case 16:
						def = PType.DRAGON;
						break;
					case 17:
						def = PType.DARK;
						break;
					case 18:
						def = PType.FAIRY;
						break;
					case 10001:
						def = PType.UNKNOWN;
						break;
					case 10002:
						def = PType.SHADOW;
						break;
					}
					//places effects into hashmap 
					//(Index of attacking type will result with maps for each defending type and its damageFactor on it)
					HashMap<PType, Double> effect;
					if (TYPECHECK.containsKey(attack)) {
						effect = TYPECHECK.get(attack);
					} else {
						effect = new HashMap<PType, Double>();
					}

					int damageFactor = Integer.parseInt(arr[2]);
					double dmgFactor = (damageFactor * 1.0) / 100;

					effect.put(def, dmgFactor);
					TYPECHECK.put(attack, effect);
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	//Parses color info for each pokemon
	public void colorParse(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {

					String[] arr = line.split(",");
					if (Integer.parseInt(arr[0]) <= 721) {
						Pokemon p = DEX.get(Integer.parseInt(arr[0]));
						String color = "";
						switch (Integer.parseInt(arr[5])) {
						case 1:
							color = "black";
							break;
						case 2:
							color = "blue";
							break;
						case 3:
							color = "brown";
							break;
						case 4:
							color = "gray";
							break;
						case 5:
							color = "green";
							break;
						case 6:
							color = "pink";
							break;
						case 7:
							color = "purple";
							break;
						case 8:
							color = "red";
							break;
						case 9:
							color = "white";
							break;
						case 10:
							color = "yellow";
							break;
						}
						p.setColor(color);
						DEX.put(Integer.parseInt(arr[0]), p);
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	//Parses stats
	public void statParse(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {

					String[] arr = line.split(",");
					if (Integer.parseInt(arr[0]) <= 721) {
						Pokemon p = DEX.get(Integer.parseInt(arr[0]));
						switch (Integer.parseInt(arr[1])) {
						case 1:
							p.setHp(Integer.parseInt(arr[2]));
							break;
						case 2:
							p.setAttack(Integer.parseInt(arr[2]));
							break;
						case 3:
							p.setDefense(Integer.parseInt(arr[2]));
							break;
						case 4:
							p.setSpAtk(Integer.parseInt(arr[2]));
							break;
						case 5:
							p.setSpDef(Integer.parseInt(arr[2]));
							break;
						case 6:
							p.setSpeed(Integer.parseInt(arr[2]));
							break;
						case 7:
							p.setAccuracy(Integer.parseInt(arr[2]));
							break;
						case 8:
							p.setEvasion(Integer.parseInt(arr[2]));
							break;
						}
						DEX.put(Integer.parseInt(arr[0]), p);
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	public void pokemonMoveIDParse(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {

					String[] arr = line.split(",");
					// if its first 721 pokemon
					if (Integer.parseInt(arr[0]) <= 721 && Integer.parseInt(arr[2]) <= 621
							&& Integer.parseInt(arr[1]) == 16) {
						// if it's the most recent gen moves
						Pokemon p = DEX.get(Integer.parseInt(arr[0]));
						p.addMoveIDs(Integer.parseInt(arr[2]));
						DEX.put(Integer.parseInt(arr[0]), p);

					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	//Parses moves
	public void moveParse(String fileName) {
		String line = null;
		boolean skipOne = false;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (skipOne == false) {
					skipOne = true;
				} else {

					String[] arr = line.split(",");
					if (Integer.parseInt(arr[0]) <= 621) {
						Move m = new Move();
						m.setId(Integer.parseInt(arr[0]));
						m.setName(arr[1]);
						if (!arr[4].equals("")) {
							m.setPower(Integer.parseInt(arr[4]));

						}
						if (m.getId() == 165) {
							// struggle has no pp
							m.setPp(50);
						} else {
							m.setPp(Integer.parseInt(arr[5]));
						}
						if (!arr[6].equals("")) {
							m.setAccuracy(Integer.parseInt(arr[6]));
						}
						PType type = null;
						switch (Integer.parseInt(arr[3])) {
						case 1:
							type = PType.NORMAL;
							break;
						case 2:
							type = PType.FIGHTING;
							break;
						case 3:
							type = PType.FLYING;
							break;
						case 4:
							type = PType.POISON;
							break;
						case 5:
							type = PType.GROUND;
							break;
						case 6:
							type = PType.ROCK;
							break;
						case 7:
							type = PType.BUG;
							break;
						case 8:
							type = PType.GHOST;
							break;
						case 9:
							type = PType.STEEL;
							break;
						case 10:
							type = PType.FIRE;
							break;
						case 11:
							type = PType.WATER;
							break;
						case 12:
							type = PType.GRASS;
							break;
						case 13:
							type = PType.ELECTRIC;
							break;
						case 14:
							type = PType.PSYCHIC;
							break;
						case 15:
							type = PType.ICE;
							break;
						case 16:
							type = PType.DRAGON;
							break;
						case 17:
							type = PType.DARK;
							break;
						case 18:
							type = PType.FAIRY;
							break;
						case 10001:
							type = PType.UNKNOWN;
							break;
						case 10002:
							type = PType.SHADOW;
							break;
						}

						m.setType(type);
						switch (Integer.parseInt(arr[9])) {
						case 1:
							m.setDamageClass(DamageClass.STATUS);
							break;
						case 2:
							m.setDamageClass(DamageClass.PHYSICAL);
							break;
						case 3:
							m.setDamageClass(DamageClass.SPECIAL);
							break;
						}
						MOVES.put(Integer.parseInt(arr[0]), m);
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	//Sets more understandable data structures for getting each pokemon's strengths and weaknesses directly
	public ArrayList<HashMap<PType, Double>> setAllRelations2(HashMap<PType, HashMap<PType, Double>> typeCheck,
			PType[] type) {
		ArrayList<HashMap<PType, Double>> returnlist = new ArrayList<HashMap<PType, Double>>();
		HashMap<PType, Double> weaknesses = new HashMap<PType, Double>();
		HashMap<PType, Double> strengths = new HashMap<PType, Double>();
		for (PType t : type) {
			// if other types work greater than 1 on this type, then add 
			//checks the damage factor for various combinations of types and adds it to the appropriate
			//data structure (either weaknesses or strengths)
			for (PType t3 : typeCheck.keySet()) {
				if (typeCheck.get(t3).get(t) > 1.0) {
					if (weaknesses.containsKey(t3)) {
						double x = weaknesses.get(t3) * typeCheck.get(t3).get(t);
						weaknesses.put(t3, x);
					} else if (strengths.containsKey(t3)) {
						strengths.remove(t3);
					} else {
						weaknesses.put(t3, typeCheck.get(t3).get(t));
					}
				} else if (typeCheck.get(t3).get(t) == 0) {
					strengths.put(t3, 0.0);
				} else if (typeCheck.get(t3).get(t) < 1.0 && typeCheck.get(t3).get(t) != 0) {
					if (strengths.containsKey(t3)) {
						double x = strengths.get(t3) * typeCheck.get(t3).get(t);
						strengths.put(t3, x);
					} else if (weaknesses.containsKey(t3)) {
						weaknesses.remove(t3);
					} else {
						strengths.put(t3, typeCheck.get(t3).get(t));
					}
				}
			}
		}
		returnlist.add(strengths);
		returnlist.add(weaknesses);
		return returnlist;
	}

	public HashMap<Integer, Move> getMOVES() {
		return MOVES;
	}

	public void setMOVES(HashMap<Integer, Move> mOVES) {
		MOVES = mOVES;
	}

	public HashMap<Integer, Pokemon> getDEX() {
		return DEX;
	}

	public void setDEX(HashMap<Integer, Pokemon> dEX) {
		DEX = dEX;
	}

	public HashMap<PType, HashMap<PType, Double>> getTYPECHECK() {
		return TYPECHECK;
	}

	public void setTYPECHECK(HashMap<PType, HashMap<PType, Double>> tYPECHECK) {
		TYPECHECK = tYPECHECK;
	}

	public HashMap<PType, HashMap<PType, Double>> getRELATION() {
		return RELATION;
	}

}
