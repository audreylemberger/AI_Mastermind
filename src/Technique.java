
/**
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public abstract class Technique {
	
	//update gene pool or constraints according to technique
	public abstract void update(int redPegs, int[] guess);
	
	
	public abstract int[] makeGuess();
}
