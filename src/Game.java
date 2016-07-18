
public class Game {

	// players arraylist
	// die
	// solution array of cards
	private Board board;

	public Game() {
		// ask how many players and pick char store in array
		// make die
		// make cards
		// get murder random
		// deal remaining cards
		// get board save to field
		// call playing game
		try{
		board = new Board();
		} catch(Exception e){
			
		}
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
