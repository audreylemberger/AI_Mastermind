import java.util.HashMap;
import java.util.Map;

public abstract class Constraint {

	private int[] recordedGuess;
	int numPegs;
	
	public abstract boolean validate(Integer[] guess );
	public Constraint(int[] recordedGuess, int numPegs){
		this.recordedGuess = recordedGuess;
		this.numPegs = numPegs;
		
	}
}
