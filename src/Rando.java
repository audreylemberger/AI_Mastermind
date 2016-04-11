/**
 * Technique that simply guesses random numbers. No learning.
 * 
 * @author Nicole
 *
 */
public class Rando extends Technique {
	
	/**
	 * the Random class does not learn; update is empty
	 */
	@Override
	public void update(int blackPegs, int redPegs, int[] guess) {
		return;
	}
	
	/**
	 * create a series of random numbers as a guess
	 */
	public int[] makeGuess() {
		return randomGuess();
	}

	

}
