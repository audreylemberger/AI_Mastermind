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
public class Solution {

	public static final int NUM_PEGS = 4;
	private int[] solution;
	
	
	/**
	 * Randomly generate numbers 0-9, create the solution array
	 * 
	 */
	public Solution(){
		solution = new int[NUM_PEGS];
		
		for (int i = 0; i < NUM_PEGS; i++){
			solution[i] = (int) Math.floor(Math.random()*10);
		}
	}
	
	
	/**
	 * Check if the guess is correct
	 * 
	 * @param guess
	 * @return true if the guess is the solution.
	 */
	public boolean checkCorrect(int[] guess){
		if (calcBlack(guess) == NUM_PEGS)
			return true;
		return false;
	}
	
	
	/**
	 * Find and return number of slots that have the correct number in the correct slot
	 * 
	 * @param guess
	 * @return
	 */
	public int calcBlack(int[] guess){
		int blackPegs = 0;
		for (int i = 0; i < NUM_PEGS; i++){
			if (guess[i] == solution[i]){
				blackPegs++;
			}
		}
		return blackPegs;
	}
	
	
	/**
	 * Find and return number of correct numbers that are in the wrong slot
	 * 
	 * @param guess
	 * @return
	 */
	public int calcRed(int[] guess){
		//TODO
		return -1;
	}
	
	
	public int[] getSolution(){
		return solution;
	}
	
	
	
	
	
}
