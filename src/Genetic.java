import java.util.PriorityQueue;

/**
 * Genetic Algorithm
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class Genetic extends Technique {
	private static final int POOL_SIZE = 10; //size of the gene pool: number of genomes to keep
	private PriorityQueue genePool;
	
	//priority queue of best Genome breeders?
	
	
	/**
	 * based on the number of pegs a guess receives,
	 * assign weight/priority,
	 * add to queue of best breeders (bad genomes should fall of the end)
	 */
	public void update(int blackPegs, int redPegs int[] guess) {
		Genome evaluatedGuess = new Genome(guess, blackPegs, redPegs);
		
		if
		
		
		// TODO Auto-generated method stub

	}
	
	/**
	 * if there aren't many good breeders, make random guesses!
	 * Maybe make many guesses at once- work in generations
	 */
	public int[] makeGuess() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * holds a genome:
	 * four peg guesses and the strength of the guess as a whole
	 * 
	 * @author Nicole
	 *
	 */
	private static class Genome implements Comparable {
		private int[] guess;
		private int fitness;		//holds fitnessfunction's calculation of total red and white peg value.
		
		private Genome(int[] guess, int blackPegs, int redPegs){
			this.guess = guess;
			fitness = blackPegs*2 + redPegs;
		}
		
		public int[] getGuess(){
			return guess;
		}
		
		public int getFitness(){
			return fitness;
		}

		public int compareTo(Object arg) {
			//TODO fix this
			return fitness-competitor.getFitness();
		}

		}
		
	}
	

}
