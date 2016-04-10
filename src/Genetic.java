import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Genetic Algorithm
 * 
 * @author masters Hoffler, Lemberger
 *
 */
public class Genetic extends Technique {
	private static final int POOL_SIZE = 10; //size of the gene pool: number of genomes to keep
	private PriorityQueue<Genome> genePool = new PriorityQueue<Genome>();
	private Solution solution;
	
	private int counter =0;
	
	//priority queue of best Genome breeders?
	
	public Genetic(Solution solution){
		this.solution = solution;
	}
	/**
	 * based on the number of pegs a guess receives,
	 * assign weight/priority,
	 * add to queue of best breeders (bad genomes should fall of the end)
	 */
	public void update(int blackPegs, int redPegs, int[] guess) {
		Genome evaluatedGuess = new Genome(guess, blackPegs, redPegs);
		//copied how to use a priority queue here: http://stackoverflow.com/questions/18355932/java-priority-queues-and-comparable-interface
		//i'm assuming the compareTo method takes care of adding elements in the right
		//place in the queue(?)
		genePool.add(evaluatedGuess);
		
		//add to priority
		
		
		// TODO Auto-generated method stub

	}
	
	/**
	 * if there aren't many good breeders, make random guesses!
	 * Maybe make many guesses at once- work in generations
	 */
	public int[] makeGuess() {
		// TODO Auto-generated method stub
		
		//for 10 making randomGuess()
		//put in priorityQueue
		
		//make 10 kids based off of parents
		//only go so far into queue
		//everyone mates with top of priority
		//dont mate 2nd and 3rd with each other
		//3 for priority queue
		if (counter <10){
			int[] guess = new int[Solution.NUM_PEGS];
		
			for (int i = 0; i < Solution.NUM_PEGS; i++){
				guess[i] = (int) Math.floor(Math.random()*10);
			}
			counter++;
			return guess;
			
		}
		else{
			//take a random successor from the pool we made, I might
			//have interpreted this wrong way because I saw that we made a note
			//that we evalute the fitness of the successors in the makePool method
			//but how can we evalute fitness if the only way we can is to acutally make a guess?
			int randomKid = (int) Math.floor(Math.random()*10);
			Genome[] possibleKids = (Genome[]) makePool().toArray();
			int[] guess = possibleKids[randomKid].getGuess();
			return guess;
		}
	}
	
	
	public List<Genome> makePool(){
		
		 List<Genome> kids = new ArrayList<Genome>();
		 Genome[] temp = (Genome[]) genePool.toArray();
		 // tenth of a chance that there will be mutation
		 // didn't have time to figure out how to incoporate this
		 int mutateChance = (int) Math.floor(Math.random()*10);
		 //update, test all of them for fitness
		 for (int i = 0; i < 10; i++){
			 //used the mod to just make 5 kids through mating with 1st and 2nd genome in queue
			 // and 5 kids mating with 1st and 3rd genome
			 // took half of 1st parent from priority queue and second half to whoever is mating with it
			 // we should talk on how we crossover different genes, I was just going with intuition 
			 
			 if (i%2 == 0){
				 int[] kid = {temp[0].getGuess()[0], temp[0].getGuess()[1], temp[1].getGuess()[2], temp[1].getGuess()[3] };
				 Genome add = new Genome(kid, 0, 0);
				 kids.add(add);
			 }
			 else{
				 int[] kid = {temp[0].getGuess()[0], temp[0].getGuess()[1], temp[2].getGuess()[2], temp[2].getGuess()[3] };
				 Genome add = new Genome(kid, 0, 0); 
				 kids.add(add);
			 }
		 }
		 return kids;
	}
	
	public int[] makeMutantSuccessor(Genome firstParent, Genome secondParent){
		int[] mutant = {firstParent.getGuess()[0], firstParent.getGuess()[1], secondParent.getGuess()[2], secondParent.getGuess()[3] };
		int mutantNumber =(int) Math.floor(Math.random()*10);
		int randomPlace = (int) Math.floor(Math.random()*3);
		mutant[randomPlace] = mutantNumber;
		return mutant;
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
			}
			else{
				return 1;
			}
			
		}

		

		}

	@Override
	public void update(int redPegs, int[] guess) {
		// TODO Auto-generated method stub
		
	}
	
		
}
	


