//testing if this works hopefully if i doesnt i will pull out my hair and leave this school forEVERRRR
public class Mastermind {


	public static void main(String[] args){
		System.out.println("Welcome to Mastermind!");
		System.out.println("Program by Nicole Hoffler and Audrey Lemberger");
		System.out.println("Choose a technique: ....");				

		//if(....)

		//testPegs();			//test red and black pegs are working
		
		//playRandom();
		
		//playGenetic();
		
		playCSP();

	}
	
	
	private static void playCSP(){
		System.out.println("\nPlaying with Constraint Satisfaction.");
		Solution sol = new Solution();
		System.out.println(guessToString(sol.getSolution()));
		CSatisfaction player = new CSatisfaction();
		int[] guess = {-1, -1, -1, -1};
		int numGuesses = 0;
		
		
		
		while (!sol.checkCorrect(guess)){							//play until we find the answer
			guess = player.makeGuess();
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			numGuesses++;
			player.update(black, red, guess);
			
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );

			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + numGuesses + " guesses.");
				return;
			}
		}
	}
	

	private static void playGenetic(){
		System.out.println("\nPlaying with Genetic Algorithm.");
		Solution sol = new Solution();
		Genetic player = new Genetic();
		int[] guess = {-1, -1, -1, -1};
		int numGuesses = 0;
		
		while (!sol.checkCorrect(guess)){							//play until we find the answer
			guess = player.makeGuess();
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			numGuesses++;
			player.update(black, red, guess);
			
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );

			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + numGuesses + " guesses.");
				return;
			}
		}
	}

	
	private static void playRandom(){
		System.out.println("\nPlaying with technique Random.");
		Solution sol = new Solution();
		Rando player = new Rando();
		int[] guess = {-1, -1, -1, -1};
		int numGuesses = 0;

		while (!sol.checkCorrect(guess)){							//play until we find the answer
			guess = player.makeGuess();								//make a new guess    0251
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			numGuesses++;
			
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );

			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + numGuesses + " guesses.");
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


	/**
	 * used below code to test number of red and black pegs and both work, 
	 * feel welcome to test them
	 */
	private static void testPegs(){
		Solution sol = new Solution();
		Rando player = new Rando();
		int[] guess;

		guess = player.makeGuess();								//make a new guess    0251
		int black = sol.calcBlack(guess);
		int red = sol.calcRed(guess);

		System.out.println("red " +red);
		System.out.println("black " +black);
		System.out.println("solution");
		System.out.println(guessToString(sol.getSolution()));
		System.out.println("guess");
		System.out.println(guessToString(guess));
	}
	
}
