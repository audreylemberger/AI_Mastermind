import java.util.List;

/**
 * general code
 * 
 * general sequence of events:
 * 		makeSoultion();
 * 		while not correct guess:
 * 			tqn.makeGuess()
 * 			evaluateGuess()
 * 			tqn.updateConstraints
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class Mastermind {

	public static final int NUM_PEGS = 4;
	
	private Technique tnq; //of type ConstraintSatisfaction or Genetic. set in Mastermind constructor?? maybe not
	private int[] solution;
	
	/**
	 * Randomly generate 4 numbers
	 * @return
	 */
	private void makeSolution(){
		solution = new int[NUM_PEGS];
		// continut...
		
	}
	
	/**
	 * Use solution to evaluate the computer's guess
	 * ***!! May want to replace with a calcRed() and calcWhite()
	 * 
	 * @param solution
	 * @param guess
	 */
	private void evaluateGuess(int[] solution, int[] guess){
		//isTerminal()?
		
		//TODO int[NUM_PEGS] whitePegs;
		int redPegs;
		
		
	}
	
	public boolean isTerminal(int[] guess){
		return false;
		//check correctness
	}
	

	
	
	
	
}
