package pokemon.p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import pokemon.p1.Pokemon.PType;

/**
 * Run this class and interact with the command line to make your Pokemon team!
 */
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Parsing data csv files
		String fileName = "input/pokemonNamesAndIDsUpdated.txt";
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

		// Initial data sets:
		HashMap<Integer, Pokemon> dex = parse.getDEX();
		HashMap<Integer, Move> moves = parse.getMOVES();
		HashMap<PType, HashMap<PType, Double>> TYPECHECK = parse.getTYPECHECK();
		ArrayList<HashMap<PType, Double>> TYPECHART;

		TeamBuilder teamBuilder = TeamBuilder.getInstance();
		SortParam sortparam = SortParam.getInstance();

		/****************************************************************************/
		System.out.println("Your team can be created based on a Pokemon's type, color, and/or stat."
				+ "\nThe less preferences you have, the more likely you'll get a strong and balanced team."
				+ "\n\nEnter a preference for Pokemon type"
				+ "\n[NORMAL,FIGHTING,FLYING,POISON,GROUND,ROCK,BUG,GHOST,STEEL,\nFIRE,WATER,GRASS,ELECTRIC,PSYCHIC,ICE,DRAGON,DARK]"
				+ "\nOr enter NONE if no preference:");
		String type = scan.next();
		System.out.println("\nEnter a preference for Pokemon color"
				+ "\n[red, black, blue, brown, green, yellow, white, pink, purple, gray]"
				+ "\nOr enter NONE if no preference:");
		String color = scan.next();
		System.out.println("\nEnter a preference for Pokemon stat"
				+ "\n[hp, attack, defense, spattack, spdefense, speed, total]" + "\nOr enter NONE if no preference:");
		String stat = scan.next();
		System.out.println("\nDo you want legendary Pokemon on your team? (Y/N)");
		String yn = scan.next();
		System.out.println();
		/****************************************************************************/

		String[] args2 = { type.toUpperCase(), color.toLowerCase(), stat.toUpperCase(), yn.toLowerCase()};

		sortparam.initSortParam(args2);
		
		for (Pokemon p : dex.values()) {
			TYPECHART = teamBuilder.createTypeChart(TYPECHECK, p.getType());
			p.setAllRelations(TYPECHART);
			p.setMoves(moves);
		}
		
		teamBuilder.initTeamBuilder(dex, moves, TYPECHECK);

		List<Entry<Integer, Pokemon>> critList = teamBuilder.rankPokemon(sortparam);
		teamBuilder.determine(critList);
	}
}
