import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Constraint Satisfaction Technique
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class CSatisfaction extends Technique {
	//list of possible values for each slot
	List[] domains; 
	
	//growing list of constraints, each with scope and relation
	List constraints; //not best data structure for this?...
	
	public CSatisfaction(){
		List<Integer>[] domains = new LinkedList[Solution.NUM_PEGS];
		
		//List constraints =
	}
	
	/**
	 * function to make new constraints (or domains) out of peg counts!
	 */
	public void update(int blackPegs, int redPegs, int[] guess) {
		// TODO Auto-generated method stub
		
		//make random guess
		//make a hashmap with all previous guesses 
		
		//if no black or red pegs, then numbers cannot be in solution
		
		//if no black pegs but red pegs, take each number out of its own domain
		
		//if black pegs, but no red pegs, then (ie 1 black peg) one of numbers has to be in that slot
		
		//compare to another guess and see how many black have in common
		//come up with all valid guesses a
		
		
		

	}
	
	/**
	 * function to pick next guess
	 * choose slot that is MOST constrained, value that is LEAST constrained...
	 */
	public int[] makeGuess() {
		// TODO Auto-generated method stub
		return null;
	}

}
