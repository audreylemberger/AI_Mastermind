import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;


/**
 * Genetic Algorithm
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class Genetic extends Technique {
	private double MUTATION = 100;											// x% chance that there will be mutation
	private static final int POOL_SIZE = 10;								// size of the gene pool: number of genomes to keep
	
	private PriorityQueue<Genome> genePool = new PriorityQueue<Genome>();	// priority queue of best Genome breeders
	private Stack<int[]> kidStack = new Stack<int[]>();	
	int average;// holds next guesses to offer
	
	
	
	/**
	 * based on the number of pegs a guess receives,
	 * assign weight/priority,
	 * add to queue of best breeders (bad genomes should fall of the end)
	 */
	public void update(int blackPegs, int redPegs, int[] guess) {
		Genome evaluatedGuess = new Genome(guess, blackPegs, redPegs);		//create genome given pegs
		genePool.add(evaluatedGuess);										//add genome to priority queue
		//printGenes();
		//average fitness per generation
		
		
		
		
		//writeStats(numGeneration, average);
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
			return randomGuess();
		}
		
		// or
		// pick one of kids based off best parents
		else{
			if (kidStack.empty()) {
				fillStack();
			}
			//System.out.println("size of genepool " +genePool.size());
			//System.out.println("best fit " + genePool.peek().getGuess()[0] + genePool.peek().getGuess()[1] +
			//		genePool.peek().getGuess()[2] + genePool.peek().getGuess()[3]);
			//System.out.println("kidStack first one " +kidStack.peek()[0] + kidStack.peek()[1] + kidStack.peek()[2] + kidStack.peek()[3]);
			return kidStack.pop();
		}
		
	}
	
	
	/**
	 * Make a new bunch of kids to guess
	 * Put them into the kidStack
	 */
	public void fillStack(){
		System.out.println("mating season");
		kidStack = new Stack<int[]>();
		
		Genome[] temp = genePool.toArray(new Genome[genePool.size() -1]);
//		System.out.println("1st in PQ " +temp[0].guess[0] + temp[0].guess[1] + temp[0].guess[2] + temp[0].guess[3]);
//		System.out.println("fitness " + temp[0].getFitness());
//		System.out.println("black pegs " + temp[0].getBlackPegs());
//		System.out.println("red pegs " + temp[0].getRedPegs());
//		
//		System.out.println("2nd in PQ " +temp[1].guess[0] + temp[1].guess[1] + temp[1].guess[2] + temp[1].guess[3]);
//		System.out.println("fitness " + temp[1].getFitness());
//		System.out.println("black pegs " + temp[1].getBlackPegs());
//		System.out.println("red pegs " + temp[1].getRedPegs());
//		
//		System.out.println("3rd in PQ " +temp[2].guess[0] + temp[2].guess[1] + temp[2].guess[2] + temp[2].guess[3]);
//		System.out.println("fitness " + temp[2].getFitness());
//		System.out.println("black pegs " + temp[2].getBlackPegs());
//		System.out.println("red pegs " + temp[2].getRedPegs());
//		printGenes();
		
		// we should talk on how we crossover different genes...
		//	even-numbered mate with best genome
		// odd-numbered mate with second-best genome
		for (int i = 2; i < POOL_SIZE; i++){

			if (i%2 == 0){
				//System.out.print("kid [0][" + i + "]: ");
				kidStack.push(makeKid(temp[0], temp[i]));
			}
			else{
				//System.out.print("kid [1][" + i + "]: ");
				kidStack.push(makeKid(temp[1], temp[i]));
			}
		} 
	}
	
	
	public void printGenes(){
		Genome[] temp = genePool.toArray(new Genome[genePool.size()]);
		System.out.println("Gene Pool: ");
		
		for (int i = 0; i < temp.length; i++){
			Genome g = genePool.poll();
			int[] p = g.getGuess();
			System.out.println("P" + ": " + p[0] + " " + p[1] + " " + p[2] + " " + p[3] + "\t\t fitness: " + g.getFitness() );
			temp[i] = g;
		}
		
		for (int i = 0; i < temp.length; i++){
			genePool.add(temp[i]);
		}
		
		System.out.println("end gene pool.\n");
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
		//System.out.println("PARENT 1 " +geneToString(p1));
		//System.out.println("PARENT 2 " +geneToString(p2));
		if (geneToString(p1).equals(geneToString(p2))){
//			System.out.println("INCEST");
//			int mutantNumber = (int) Math.floor(Math.random()* 10);
//			//System.out.println("mutant number " + mutantNumber);
//			int randomPlace = (int) Math.floor(Math.random()* Solution.NUM_PEGS);
//			p1[randomPlace] = mutantNumber;
			int one = (int) Math.floor(Math.random()* 10);
			int two = (int) Math.floor(Math.random()* 10);
			int three = (int) Math.floor(Math.random()* 10);
			int four = (int) Math.floor(Math.random()* 10);
			p1[0] = one;
			p1[1] = two;
			p1[2] = three;
			p1[3] = four;
			
		}
		
		for (int i = 0; i < Solution.NUM_PEGS; i++){
			if (i < Solution.NUM_PEGS/2){
				kid[i] = p1[i];
			}else{
				kid[i] = p2[i];
			}
		}
		
		
		//add mutation...
		Random random = new Random();
		int tempRan=random.nextInt(50);
		//System.out.println("random mutation " +tempRan);
		if (tempRan < MUTATION){
			
			//System.out.println("kid before mutation " +kid[0] + kid[1] + kid[2] + kid[3]);
			int mutantNumber = (int) Math.floor(Math.random()* 10);
			//System.out.println("mutant number " + mutantNumber);
			int randomPlace = (int) Math.floor(Math.random()* Solution.NUM_PEGS);
			//System.out.println("random place " + randomPlace);
			kid[randomPlace] = mutantNumber;
			
			//System.out.println("kid mutated " +kid[0] + kid[1] + kid[2] + kid[3]);
			
			
		}
		
		
		return kid;
	}
	
	public double getAverage(){
		Genome[] temp = genePool.toArray(new Genome[genePool.size()]);
		double average = 0;
		for (int i = 0; i < genePool.size();i++){
			average = average + temp[i].getFitness();
		}
		average = average/genePool.size();
		return average;
	}
	
	private String geneToString(int[] gene){
		String stringGene = "";
		for(int i = 0; i < gene.length; i++){
			stringGene = stringGene + gene[i];
		}
		return stringGene;
	}
	
	public int getGenePoolSize(){
		return genePool.size();
	}
	
	public Genome[] getGenePool(){
		Genome[] temp = genePool.toArray(new Genome[genePool.size() -1]);
		return temp;
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
		private int blackPegs;
		private int redPegs;
		private int fitness;		//holds fitnessfunction's calculation of total red and white peg value.
		
		private Genome(int[] guess, int blackPegs, int redPegs){
			this.guess = guess;
			this.blackPegs = blackPegs;
			this.redPegs = redPegs;
			fitness = blackPegs*2 + redPegs;
		}
		public int getBlackPegs(){
			return blackPegs;
		}
		
		public int getRedPegs(){
			return redPegs;
		}
		
		public int[] getGuess(){
			return guess;
		}
		
		public int getFitness(){
			return fitness;
		}

		public int compareTo(Genome arg) {
			return arg.fitness-fitness;
		}
	}
		
}
	


