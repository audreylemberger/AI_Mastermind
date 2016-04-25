import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

		//just to check if a color is in the right place, makes it easier to check for colors in
		//in the wrong place
		List<Boolean> isRightPlace = new LinkedList<Boolean>();
		//store all slots that do not have color in the right place with the number
		//of times they occur in the guess
		Map<Integer, Integer> possibleRed = new HashMap<Integer, Integer>();
		int redPegs = 0;
		
		
		for (int i = 0; i < NUM_PEGS; i++){
			if (guess[i] == solution[i]){
				
				isRightPlace.add(true);
			}
			else{
				
				isRightPlace.add(false);
				//add to map if not already there, occurs once so far
				if (! possibleRed.containsKey(guess[i])){
					possibleRed.put(guess[i], 1);
				}
				else{
					//if in map add 1 to number of times it has already occurred
					possibleRed.put(guess[i], possibleRed.get(guess[i]) +1);
					
				
				}
			}
			
		}
		for (int i = 0; i< NUM_PEGS ; i++){
			if (isRightPlace.get(i) == true){
				//do nothing
			}
			else{
				if (possibleRed.containsKey(solution[i])){
					//do not count as red peg because color does not occur again
					//did this because possibility of having 2 of the same colors in the solution not 
					//in the right position 
					if (possibleRed.get(solution[i]) == 0){
						
					}
					else{
						redPegs++;
						possibleRed.put(solution[i], possibleRed.get(solution[i]) -1);
					}
					
				}
			}
		}
		return redPegs;
	}
	
	
	public int[] getSolution(){
		return solution;
	}
	
	
	
	
	
}
