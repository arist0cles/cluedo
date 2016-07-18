import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private int numOfPlayers;
	private List<Player> players = new ArrayList<>();
	private Die die;
	private List<Card> solution = new ArrayList<>();
	private Board board;

	public Game() {
		board = new Board();
		// ask how many players and pick char store in array
		getNumOfPlayers();
		
		// make die
		// make cards
		// get murder random
		// deal remaining cards
		// get board save to field
		// call playing game
		
	}
	
	/*
	 * Helper method for setup, prompts the user for how many players
	 * then assigns the players characters randomly
	 */
	private void getNumOfPlayers(){
		System.out.println("How many players?");		
		Scanner input = new Scanner(System.in);
		numOfPlayers = input.nextInt();
		while(numOfPlayers>6 || numOfPlayers<1){
			System.out.println("Sorry, player number must be 2-6");
			numOfPlayers = input.nextInt();
		}
		System.out.println(numOfPlayers);
		input.close();
	}

	public static void main(String args[]) {
		new Game();
	}

	public void playing() {

		boolean onGoing = true;
		System.out.println("as");
		while (onGoing) {
			// roll die
			// loop clockwise? call turn on each player which calls options
			// players turn method returns boolean if still in game 
			//move = true (in game still)
			//hyp = true in game
			//attempt correct sets has won true
			//attempt incorrect returns false out of game 
			// board,draw
			// check if player won
		}
	}
}
