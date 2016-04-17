
public class BlackConstraint extends Constraint {

	int[] recordedGuess;
	int numPegs;
	public BlackConstraint(int[] recordedGuess, int numPegs) {
		super(recordedGuess, numPegs);
		
		// TODO Auto-generated constructor stub
	}

	//checks guess passed with new guess that we generated
	@Override
	public boolean validate(Integer[] guess) {
		// TODO Auto-generated method stub
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
