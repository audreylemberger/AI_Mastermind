
/**
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public abstract class Technique {
	
	//update gene pool or constraints according to technique
	public abstract void update(int redPegs, int[] guess);
	public int[] randomGuess() {
		int[] guess = new int[Solution.NUM_PEGS];
		
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			guess[i] = (int) Math.floor(Math.random()*10);
		}
		
		return guess;
	}
	
	
	public abstract int[] makeGuess();
}
