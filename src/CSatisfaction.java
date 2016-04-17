import java.util.ArrayList;
import java.util.HashMap;
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
	//list of possible values for each slot
	List<Integer>[] domains;
	
	Map<String, Boolean> previousGuesses;
	
	//growing list of constraints, each with scope and relation
	List constraints; //not best data structure for this?...
	
	public CSatisfaction(){
		domains = new LinkedList[Solution.NUM_PEGS];
		previousGuesses = new HashMap<String, Boolean>();
		
		//List constraints =
	}
	
	/**
	 * function to make new constraints (or domains) out of peg counts!
	 */
	public void update(int blackPegs, int redPegs, int[] guess) {
		// TODO Auto-generated method stub
		BlackConstraint evaluatedBlack = new BlackConstraint(guess, blackPegs);		
		RedConstraint evaluatedRed = new RedConstraint(guess, redPegs);	
		
		
		
		
		//make random guess
		//make a hashmap with all previous guesses 
		
		//if no black or red pegs, then numbers cannot be in solution
		
		//if no black pegs but red pegs, take each number out of its own domain
		
		//if black pegs, but no red pegs, then (ie 1 black peg) one of numbers has to be in that slot
		
		//compare to another guess and see how many black have in common
		//come up with all valid guesses a
		
		//update domain
		
		
		

	}
	
	/**
	 * function to pick next guess
	 * choose slot that is MOST constrained, value that is LEAST constrained...
	 */
	public int[] makeGuess() {
		// TODO Auto-generated method stub
		String putInPrevious = "";
		//if map is empty, means this is the first guess
		if (previousGuesses.isEmpty()){
			int[] random = randomGuess();
			
			
			//turn guess into string and put in a hashmap to keep track of previous guesses
			for (int i = 0; i < Solution.NUM_PEGS; i++){
				putInPrevious = putInPrevious + random[i];
			}
			previousGuesses.put(putInPrevious, true);
			return random;
			
		}
		else{
			int[] generatedGuess = new int[Solution.NUM_PEGS];
			for (int i = 0; i< Solution.NUM_PEGS; i++){
				//get size of that list of domains for that particular slot and pick a random
				//number in that domain
					int randomDomainNum = (int) Math.floor(Math.random()*domains[i].size());
					generatedGuess[i] = domains[i].get(randomDomainNum);
				
				
			}
			for (int i = 0; i < Solution.NUM_PEGS; i++){
				putInPrevious = putInPrevious + generatedGuess[i];
			}
			previousGuesses.put(putInPrevious, true);
			return generatedGuess;
		}
		
	}

}
