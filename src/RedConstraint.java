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
		super();
		this.recordedGuess = recordedGuess;
		this.numPegs = numPegs;
		
		keepTrack = new HashMap<Integer, Integer>();
		
	}
	
	/**
	 * similar with how i calculated number of red pegs
	 * used a map to keep track of how many instances I see the number in the guess
	 */
	private void refreshMap(){
		keepTrack.clear();
		
		for (int i = 0; i < recordedGuess.length; i++){
			if (!keepTrack.containsKey(recordedGuess[i])){
				keepTrack.put(recordedGuess[i], 1);
				//System.out.println("recordedGuess " +recordedGuess[0] +recordedGuess[1]+ recordedGuess[2]+ recordedGuess[3]);
				
			}
			else{
//				System.out.println("recordedGuess in else" +recordedGuess[0] +recordedGuess[1]+ recordedGuess[2]+ recordedGuess[3]);
//				System.out.println("before " +keepTrack.get(recordedGuess[i]));
				keepTrack.put(recordedGuess[i], keepTrack.get(recordedGuess[i]) +1);
				
				
			}
		}
	}

	@Override
	public boolean validate(int[] guess) {
		int keepTrackOfRed = 0;
		refreshMap();
		
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			
			//skip over when both guesses have same slot so don't mess up hashmap
			if (guess[i] != recordedGuess[i]){
				//see if guess we took in has similar number in it
				// by looking at hashmap (that has recorded guess' numbers) 
				if (keepTrack.containsKey(guess[i])){
					//make sure we are keeping track of how many times we have used a number
//					System.out.println("guess " + guess[0] +guess[1] + guess[2] + guess[3]);
//					System.out.println("map"+keepTrack);
//					System.out.println("keepTrack.get(guess[i])" +keepTrack.get(guess[i]));
					
					if (keepTrack.get(guess[i])  != null){
						
						
					keepTrack.put(guess[i], keepTrack.get(guess[i] -1));
					keepTrackOfRed++;
					//TODO re-find keeptrack?
					}
				}
			}
			refreshMap();
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