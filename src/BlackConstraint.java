/**
 * Constraints dealing with Black Pegs
 * @author Audrey
 *
 */
public class BlackConstraint extends Constraint {
	int[] recordedGuess;
	int numPegs;						//number of black pages recordedGuess received
	
	
	public BlackConstraint(int[] recordedGuess, int numPegs) {
		super(recordedGuess, numPegs);
		
	}


	/**
	 * checks guess passed with new guess that we generated
	 * 
	 */
	@Override
	public boolean validate(int[] guess) {
		int keepTrackOfBlack = 0;
		
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			if (guess[i] == recordedGuess[i]){
				keepTrackOfBlack++;
			}
		}
		
		//if number of black pegs from guess is not equal to new guess, not correct or not accurate enough guess
		if (keepTrackOfBlack != numPegs){
			return false;
		}
		else{
			return true;
		}
		
	}

}
