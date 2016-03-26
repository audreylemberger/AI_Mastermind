/**
 * Technique that simply guesses random numbers. No learning.
 * 
 * @author Nicole
 *
 */
public class Random extends Technique {
	

	
	public void update(int redPegs, int[] guess) {
		// TODO Auto-generated method stub

	}
	
	public int[] makeGuess() {
		int[] guess = new int[Solution.NUM_PEGS];
		
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			guess[i] = (int) Math.floor(Math.random()*10);
		}
		
		return guess;
	}

}
