package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import pokemon.p1.Move;
import pokemon.p1.Parser;
import pokemon.p1.Pokemon;
import pokemon.p1.TeamBuilder;
import pokemon.p1.Pokemon.PType;

public class Tests1 {
	HashMap<Integer, Pokemon> dex;
	HashMap<Integer, Move> moves;
	HashMap<PType, HashMap<PType, Double>> TYPECHECK;
	ArrayList<HashMap<PType, Double>> TYPECHART;
	TeamBuilder teamBuilder = TeamBuilder.getInstance();

	public void startTests() {
		String fileName = "input/pokemonNamesAndIDs.txt";
		Parser parse = Parser.getInstance();
		parse.mainParse(fileName);
		fileName = "input/typeIDs.txt";
		parse.parseType(fileName);
		fileName = "input/colors.txt";
		parse.colorParse(fileName);
		fileName = "input/stats.txt";
		parse.statParse(fileName);
		fileName = "input/moveSets.txt";
		parse.pokemonMoveIDParse(fileName);
		fileName = "input/moveData.txt";
		parse.moveParse(fileName);
		fileName = "input/typeEfficacy.txt";
		parse.parseTypeEffects(fileName);
		// Initial 3 data sets:
		dex = parse.getDEX();
		moves = parse.getMOVES();
		TYPECHECK = parse.getTYPECHECK();

		for (Pokemon p : dex.values()) {
			TYPECHART = teamBuilder.createTypeChart(TYPECHECK, p.getType());
			p.setAllRelations(TYPECHART);
			p.setMoves(moves);
		}
		teamBuilder.initTeamBuilder(dex, moves, TYPECHECK);
	}

	@org.junit.Test
	public void testDexInitialization() {
		startTests();
		assertTrue(dex.get(1).getName().equals("bulbasaur"));
		assertTrue(dex.size() == 721);
	}

	// Sorts all pink pokemon then sorts by special attack stat
	@org.junit.Test
	public void testColorSort() {
		startTests();
		ArrayList<Pokemon> output = new ArrayList<Pokemon>();
		for (Pokemon p : dex.values()) {
			if (p.getColor().equals("pink")) {
				output.add(p);
			}
		}
		Collections.sort(output, new Comparator<Pokemon>() {
			public int compare(Pokemon o1, Pokemon o2) {
				if (o1.getSpAtk() == o2.getSpAtk())
					return 0;
				return o1.getSpAtk() > o2.getSpAtk() ? -1 : 1;
			}
		});
		String out = "";
		for (Pokemon p : output) {
			out = out + (p.getName()) + " ";
		}
		assertTrue(out.equals(
				"gorebyss sylveon musharna mesprit slowbro mr-mime mew slowking milotic diancie aromatisse clefable cherrim wigglytuff porygon smoochum flaaffy lickilicky blissey mime-jr munna corsola spritzee cherubi clefairy exeggcute lickitung audino whismur jigglypuff cleffa slowpoke igglybuff snubbull miltank luvdisc alomomola chansey hoppip skitty happiny "));
	}

	// tests the scoring of arceus' moves, that each has a proper score and then
	// sorts them
	@org.junit.Test
	public void testMoveScore() {
		startTests();
		HashMap<Integer, Move> moves1 = dex.get(493).getMoves();
		ArrayList<Move> topScoring = new ArrayList<Move>();
		for (Move m : moves1.values()) {
			topScoring.add(m);
		}
		Collections.sort(topScoring, new Comparator<Move>() {
			public int compare(Move o1, Move o2) {
				if (o1.score() == o2.score())
					return 0;
				return o1.score() > o2.score() ? -1 : 1;
			}
		});
		String out = "";
		for (Move p : topScoring) {
			out = out + (p.getName() + " " + p.score() + " ");
		}
		assertTrue(out.equals(
				"hyper-beam 9 solar-beam 9 giga-impact 9 outrage 9 future-sight 9 overheat 8 last-resort 8 dream-eater 8 draco-meteor 8 fly 7 hyper-voice 7 flamethrower 7 surf 7 ice-beam 7 thunderbolt 7 earthquake 7 psychic 7 poison-jab 7 energy-ball 7 earth-power 7 sludge-bomb 7 judgment 7 heat-wave 6 facade 6 brick-break 6 secret-power 6 dive 6 signal-beam 6 strength 6 dragon-claw 6 fire-blast 6 waterfall 6 dark-pulse 6 aqua-tail 6 x-scissor 6 dragon-pulse 6 focus-blast 6 shadow-claw 6 zen-headbutt 6 flash-cannon 6 iron-head 6 giga-drain 6 psyshock 6 iron-tail 6 extreme-speed 6 shadow-ball 6 retaliate 5 bulldoze 5 cut 5 blizzard 5 rock-tomb 5 aerial-ace 5 thunder 5 shock-wave 5 water-pulse 5 rock-slide 5 stone-edge 5 hidden-power 5 round 5 incinerate 5 hail 4 will-o-wisp 4 swords-dance 4 trick 4 magic-coat 4 recycle 4 refresh 4 snarl 4 roar 4 cosmic-power 4 iron-defense 4 confide 4 thunder-wave 4 calm-mind 4 toxic 4 gravity 4 double-team 4 recover 4 tailwind 4 light-screen 4 reflect 4 payback 4 flash 4 rest 4 substitute 4 snore 4 trick-room 4 protect 4 stealth-rock 4 perish-song 4 icy-wind 4 sandstorm 4 swagger 4 hone-claws 4 sleep-talk 4 safeguard 4 rain-dance 4 sunny-day 4 echoed-voice 4 psych-up 4 rock-smash 4 quash 4 charge-beam 3 seismic-toss 1 natural-gift 1 grass-knot 1 return 1 frustration 1 punishment 0 "));
	}

	public void PokeRankChecker() {
		// TESTING ORIGINAL POKERANK
		HashMap<Integer, Pokemon> map = new HashMap<Integer, Pokemon>();
		for (Pokemon p : dex.values()) {
			// if(p.getDefense() > 120){ // !!!!!
			map.put(p.pokeRank(), p);
			// }
		}
		Set<Entry<Integer, Pokemon>> set = map.entrySet();
		List<Entry<Integer, Pokemon>> list = new ArrayList<Entry<Integer, Pokemon>>(set);
		Collections.sort(list, new Comparator<Map.Entry<Integer, Pokemon>>() {
			@Override
			public int compare(Entry<Integer, Pokemon> o1, Entry<Integer, Pokemon> o2) {
				if (o1.getKey() == o2.getKey())
					return 0;
				return o1.getKey() > o2.getKey() ? -1 : 1;
			}
		});
		for (Map.Entry<Integer, Pokemon> entry : list) {
			// System.out.println(entry.getValue().getName()+"
			// "+entry.getValue().getWeaknesses().toString());
		}
	}
}
