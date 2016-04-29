import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main class
 * @author Nicole & Audrey
 *
 */
public class Mastermind {
	private static File geneticDataFile = new File("geneticdata.txt");
	private static File cspDataFile = new File("cspdata.txt");
	private static BufferedWriter writer;
	private static int numGeneration = 0;

	public static void main(String[] args){
		System.out.println("Welcome to Mastermind!");
		System.out.println("Program by Nicole Hoffler and Audrey Lemberger");

		//testPegs();			//test red and black pegs are working
		
		//playRandom();
		//playGenetic();
		playCSP();


	}
	
	
	private static int playCSP(){
		System.out.println("\nPlaying with Constraint Satisfaction.");
		try {
			cspDataFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(cspDataFile));
		} catch (IOException e1) {
			System.out.println("ERROR. COULD NOT CREATE GENETIC DATA FILE");
			e1.printStackTrace();
		}
		Solution sol = new Solution();
		CSatisfaction player = new CSatisfaction();
		int[] guess = {-1, -1, -1, -1};
		int numGuesses = 0;
		writeCSPStats(cspDataFile, numGeneration, player.getDomainCount() );
		
		
		
		while (!sol.checkCorrect(guess)){							//play until we find the answer
			
			guess = player.makeGuess();
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			numGuesses++;
			player.update(black, red, guess);
			numGeneration++;
			writeCSPStats(cspDataFile, numGeneration, player.getDomainCount() );
			
			
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );

			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + numGuesses + " guesses.");
				
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return numGuesses;
			}
		}
		return -1;
	}
	

	private static int playGenetic(){
		try {
			geneticDataFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(geneticDataFile));
		} catch (IOException e1) {
			System.out.println("ERROR. COULD NOT CREATE GENETIC DATA FILE");
			e1.printStackTrace();
		}

		System.out.println("\nPlaying with Genetic Algorithm.");
		Solution sol = new Solution();
		int[] solution = sol.getSolution();
		
		Genetic player = new Genetic();
		int[] guess = {-1, -1, -1, -1};
		int numGuesses = 0;
		
		while (!sol.checkCorrect(guess)){							//play until we find the answer
			if (player.getGenePoolSize() == 1000){
				System.out.println("Genetic Algorithms failed");
				return -1;
			}
			//System.out.println("\t\t\t\t\t\t\t\t\t\t\t(solution " + solution[0] + solution[1]+ solution[2] + solution[3]);
			guess = player.makeGuess();
			int black = sol.calcBlack(guess);
			int red = sol.calcRed(guess);
			numGuesses++;
			
			
			player.update(black, red, guess);
			numGeneration++;
			writeStats(geneticDataFile, numGeneration, player.getAverage() );
			
			
			System.out.println("Guess: " + guessToString(guess) + "\t\t Black: " + black + "\t Red: " + red );

			if (sol.checkCorrect(guess)){							//if the guess is correct, end
				System.out.println("Found solution " + guessToString(guess) + " in " + numGuesses + " guesses.");
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return numGeneration;
			}
		}
		return -1;
	}

	public static void writeStats(File file, int numGeneration, double average) 
	{

	    try {
			writer.write( numGeneration + " , " + average + " , " );
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   

	}
	
	public static void writeCSPStats(File file, int numGeneration, int numInDomain) 
	{

	    try {
			writer.write(numGeneration + " , " + numInDomain + " , ");
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
