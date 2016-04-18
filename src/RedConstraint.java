import java.util.HashMap;
import java.util.Map;

/**
 * Constraints dealing with Red Pegs
 * @author Audrey
 *
 */
public class RedConstraint extends Constraint{
	int[] recordedGuess;
	int numPegs;								//number of red pages recordedGuess received
	Map<Integer, Integer> keepTrack;
	
	
	public RedConstraint(int[] recordedGuess, int numPegs) {
		super(recordedGuess, numPegs);
		Map<Integer, Integer> keepTrack = new HashMap<Integer, Integer>();
		//similar with how i calculated number of red pegs
		//used a map to keep track of how many instances I see the number in the guess
		
		for (int i = 0; i < recordedGuess.length; i++){
			if (!keepTrack.containsKey(recordedGuess[i])){
				keepTrack.put(recordedGuess[i], 1);
			}
			else{
				keepTrack.put(recordedGuess[i], keepTrack.get(recordedGuess[i] +1));
			}
		}
	}

	@Override
	public boolean validate(Integer[] guess) {
		int keepTrackOfRed = 0;
		
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			
			//skip over when both guesses have same slot so don't mess up hashmap
			if (guess[i] != recordedGuess[i]){
				//see if guess we took in has similar number in it
				// by looking at hashmap (that has recorded guess' numbers) 
				if (keepTrack.containsKey(guess[i])){
					//make sure we are keeping track of how many times we have used a number
					if (keepTrack.get(guess[i]) > 0){
					keepTrack.put(guess[i], keepTrack.get(guess[i] -1));
					keepTrackOfRed++;
					//TODO re-find keeptrack?
					}
				}
			}
			
		}
		
		//if number of red pegs from guess is not equal to new guess, not correct or not accurate enough guess
		if (keepTrackOfRed != numPegs){
			return false;
		}
		else{
			return true;
		}
	}

}
