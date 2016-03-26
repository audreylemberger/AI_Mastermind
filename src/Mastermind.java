
public class Mastermind {
	
	
	public static void main(String[] args){
		System.out.println("Welcome to Mastermind!");
		System.out.println("Program by Nicole Hoffler and Audrey Lemberger");
		System.out.println("Choose a technique: ....");
		
		//if(....)
		playRandom();
		
	}
	
	
	private static void playRandom(){
		System.out.println("\nPlaying with technique Random.");
		Solution sol = new Solution();
		Random player = new Random();
		int[] guess;
		
		for (int i = 1; i < 100; i++){								//100 guesses allowed
			guess = player.makeGuess();								//make a new guess    0251
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );
			
			
			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + i + " guesses.");
				return;
			}
			
			
			
		}
		
		System.out.println("Did not find solution " + guessToString(sol.getSolution()) + " in allotted time.");
	}
	
	
	private static String guessToString(int[] guess){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for (int i = 0; i < guess.length; i++){
			sb.append(guess[i]);
			if (i < guess.length -1){
				sb.append(" ");
			}
		}
		
		sb.append("]");
		return sb.toString();
	}
	
}
