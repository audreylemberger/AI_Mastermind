import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


/**
 * Genetic Algorithm
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class Genetic extends Technique {
	private double MUTATION = 50;					// x% chance that there will be mutation
	private static final int POOL_SIZE = 10;		// size of the gene pool: number of genomes to keep
	private Solution solution;
	
	private PriorityQueue<Genome> genePool;			// priority queue of best Genome breeders
	private Iterator kidIterator;						// holds next guesses to offer
	private int counter = 0;
	
	
	
	public Genetic(Solution solution){
		this.solution = solution;
		genePool = new PriorityQueue<Genome>();
	}
	
	
	/**
	 * based on the number of pegs a guess receives,
	 * assign weight/priority,
	 * add to queue of best breeders (bad genomes should fall of the end)
	 */
	public void update(int blackPegs, int redPegs, int[] guess) {
		Genome evaluatedGuess = new Genome(guess, blackPegs, redPegs);		//create genome given pegs
		genePool.add(evaluatedGuess);										//add genome to priority queue
	}
	
	
	/**
	 * Offer up a guess from the 
	 * if there aren't many good breeders, make random guesses!
	 * Maybe make many guesses at once- work in generations
	 */
	public int[] makeGuess() {
		
		// beginning the game
		// add randomGuess() genomes to begin the genePool
		if (genePool.size() < POOL_SIZE){
			counter++;
			return randomGuess();
		}
		
		// or
		// pick one of kids based off best parents
		else{
			return (int[]) kidIterator.next();
		}
	}
	
	
	/**
	 * Make a new bunch of kids to guess
	 * Put them into the kidIterator
	 */
	public void fillIterator(){
		
		int [][] kids = new int[POOL_SIZE][Solution.NUM_PEGS];
		Genome[] temp = (Genome[]) genePool.toArray();



		// we should talk on how we crossover different genes...
		//	even-numbered mate with best genome
		// odd-numbered mate with second-best genome
		for (int i = 2; i < POOL_SIZE; i++){

			if (i%2 == 0){
				kids[i] = makeKid(temp[0], temp[i]);
			}
			else{
				kids[i] = makeKid(temp[1], temp[i]);
			}
		} 

		kidIterator = Arrays.stream(kids).iterator();
	}
	
	
	/**
	 * Take two parents, make a child with mutation
	 * @param firstParent
	 * @param secondParent
	 * @return
	 */
	public int[] makeKid(Genome firstParent, Genome secondParent){
		
		int[] p1 = firstParent.getGuess();
		int[] p2 = secondParent.getGuess();
		int kid[] = new int[Solution.NUM_PEGS];
		
		//first half to parent 1
		//second half to parent 2
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			if (i < Solution.NUM_PEGS/2){
				kid[i] = p1[i];
			}
			kid[i] = p2[i];
		}
		
		//add mutation...
		Random random = new Random();
		if (random.nextInt(100) < MUTATION){
			
			int mutantNumber = (int) Math.floor(Math.random()* 10);
			int randomPlace = (int) Math.floor(Math.random()* Solution.NUM_PEGS);
			kid[randomPlace] = mutantNumber;
			
		}

		return kid;
	}
	
	
	
	
	/**
	 * holds a genome:
	 * four peg guesses and the strength of the guess as a whole
	 * 
	 * @author Nicole
	 *
	 */
	
	private static class Genome implements Comparable<Genome> {
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

		public int compareTo(Genome arg) {
			if (this.getFitness() < ((Genome) arg).getFitness()){
				return -1;
			}else{
				return 1;
			}
		}
	}
		
}
	


