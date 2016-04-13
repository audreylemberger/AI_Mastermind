
public class Mastermind {


	public static void main(String[] args){
		System.out.println("Welcome to Mastermind!");
		System.out.println("Program by Nicole Hoffler and Audrey Lemberger");
		System.out.println("Choose a technique: ....");				

		//if(....)

		//testPegs();			//test red and black pegs are working
		
		//playRandom();
		
		playGenetic();

	}

	private static void playGenetic(){
		System.out.println("\nPlaying with Genetic Algorithm.");
		Solution sol = new Solution();
		Genetic player = new Genetic();
		int[] guess;

		for (int i = 1; i < 20; i++){								//100 guesses allowed
			guess = player.makeGuess();								//make a new guess    0251
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			player.update(black, red, guess);
			
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );

			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + i + " guesses.");
				return;
			}
		}

		System.out.println("Did not find solution " + guessToString(sol.getSolution()) + " in allotted time.");
	}

	private static void playRandom(){
		System.out.println("\nPlaying with technique Random.");
		Solution sol = new Solution();
		Rando player = new Rando();
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
