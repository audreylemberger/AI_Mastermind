import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Constraint Satisfaction Technique
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class CSatisfaction extends Technique {
	
	Map<String, Boolean> previousGuesses;						// just so we don't repeat guesses
	List<Constraint> constraints;								// growing list of constraints, each with scope and relation
	List<Integer>[] domains;									// list of possible values for each slot
	
	
	public CSatisfaction(){
		previousGuesses = new HashMap<String, Boolean>();		// new map for prev guesses
		constraints = new LinkedList<Constraint>();				// new list for constraints
		
		domains = new LinkedList[Solution.NUM_PEGS];			// set the domains
		for (int i = 0; i < domains.length; i++){				// cycle through to create the domain for each slot
			LinkedList<Integer> d = new LinkedList<Integer>();
			for (int j = 0; j <= 9; j++){						// add numbers 0-9 to the domain
				d.add(j);
			}
			domains[i] = d;
		}
	}
	
	/**
	 * function to make new constraints (or domains) out of peg counts!
	 */
	public void update(int blackPegs, int redPegs, int[] guess) {

		if (redPegs != 0){										// if there are red Pegs, make a red constraint
			RedConstraint evaluatedRed = new RedConstraint(guess, redPegs);
			constraints.add(evaluatedRed);
		}
		if (blackPegs!=0){										// if there are black Pegs, make a black constraint
			BlackConstraint evaluatedBlack = new BlackConstraint(guess, blackPegs);
			constraints.add(evaluatedBlack);
		}else{ 								//NO BLACK PEGS
			if (redPegs != 0){				//if no black pegs but red pegs, take each number out of its own domain							
				for (int i = 0; i < guess.length; i++){
					removeFromDomain(i, (Integer)guess[i]);
				}
			}else{							//if no black or red pegs, then numbers cannot be in solution
				for (int i = 0; i < guess.length; i++){
					for (int j = 0; j < guess.length; j++){
						removeFromDomain(j, (Integer)guess[i]);
					}	
				}
			}
		}
	}
	
	
	private void removeFromDomain(int slotNumber, Integer toDelete){
		domains[slotNumber].remove(toDelete);
	}
	
	
	/**
	 * function to pick next guess
	 * choose slot that is MOST constrained, value that is LEAST constrained...
	 */
	public int[] makeGuess() {
		//use random guesses for the first 10 guesses
		if (previousGuesses.isEmpty() ){
			int[] guess = randomGuess();
			previousGuesses.put(guessToString(guess), true);
			return randomGuess();
		}
		
		// generate a guess using (hopefully) slimmed down domains
		else{		
			int[] generatedGuess = null;
			while(generatedGuess == null){
				generatedGuess = checkPrev();
			}
			return generatedGuess;
		}
	}
	
	private int[] checkPrev(){
		//generate guess
		int[] generatedGuess = new int[Solution.NUM_PEGS];
		for (int i = 0; i< Solution.NUM_PEGS; i++){
			//get size of that list of domains for that particular slot and 
			//pick a random number in that domain
				int randomDomainNum = (int) Math.floor(Math.random()*domains[i].size());
				generatedGuess[i] = domains[i].get(randomDomainNum);
		}
		
		//check!
		if (previousGuesses.containsKey(guessToString(generatedGuess))){
			return null;
		}
		
		//turn guess into string and put in a hashmap to keep track of previous guesses
		previousGuesses.put(guessToString(generatedGuess), true);
		
		//check guess against constraints!
		Iterator<Constraint> iter = constraints.iterator();
		while(iter.hasNext()){
			Constraint test = iter.next();
			if (!test.validate(generatedGuess)){
				return null;
			}
		}
		
		return generatedGuess;
	}
	
	private String guessToString(int[] guess){
		String putInPrevious = "";
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			putInPrevious = putInPrevious + guess[i];
		}
		return putInPrevious;
	}
	

}
