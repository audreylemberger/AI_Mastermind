
/**
 * Constraint superclass
 * 
 * @author Audrey
 *
 */
public abstract class Constraint {

	private int[] recordedGuess;
	int numPegs;
	
	public abstract boolean validate(int[] guess );
	
	public Constraint(int[] recordedGuess, int numPegs){
		this.recordedGuess = recordedGuess;
		this.numPegs = numPegs;
		
	}
}
