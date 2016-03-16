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
		List[] domains = new List[Mastermind.NUM_PEGS];
		//List constraints =
	}
	
	/**
	 * function to make new constraints (or domains) out of peg counts!
	 */
	public void update(int redPegs, int[] guess) {
		// TODO Auto-generated method stub

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
