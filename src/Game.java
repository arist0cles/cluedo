import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private int numOfPlayers;
	private List<Player> players = new ArrayList<>();
	private Die die;
	private List<Card> people = new ArrayList<>();
	private List<Card> weapons = new ArrayList<>();
	private List<Card> rooms = new ArrayList<>();
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
	 * Helper method for setup, prompts the user for how many players then
	 * assigns the players characters randomly
	 */
	private void getNumOfPlayers() {
		System.out.println("How many players?");
		Scanner input = new Scanner(System.in);
		numOfPlayers = input.nextInt();
		while (numOfPlayers > 6 || numOfPlayers < 1) {
			System.out.println("Sorry, player number must be 2-6");
			numOfPlayers = input.nextInt();
		}
		System.out.println(numOfPlayers);
		input.close();
	}
	
	
	/*
	 * Helper method for setup, generates the deck
	 */
	private void setupCards(){
		Card missScarlett = new PersonCard("Miss Scarlett");
		Card professorPlum = new PersonCard("Professor Plum");
		Card mrsPeacock = new PersonCard("Mrs Peacock");
		Card reverendGreen = new PersonCard("Reverend Green");
		Card colonelMustard = new PersonCard("Colonel Mustard");
		Card mrsWhite = new PersonCard("Mrs White");
		people.add(missScarlett);
		people.add(professorPlum);
		people.add(mrsPeacock);
		people.add(reverendGreen);
		people.add(colonelMustard);
		people.add(mrsWhite);
		
		Card candlestick = new WeaponCard("candlestick");
		Card dagger = new WeaponCard("dagger");
		Card leadPipe = new WeaponCard("Lead Pipe");
		Card revolver = new WeaponCard("Revolver");
		Card rope = new WeaponCard("Rope");
		Card spanner = new WeaponCard("spanner");
		weapons.add(candlestick);
		weapons.add(dagger);
		weapons.add(leadPipe);
		weapons.add(revolver);
		weapons.add(rope);
		weapons.add(spanner);
		
		Card kitchen = new RoomCard("Kitchen");
		Card ballroom = new RoomCard("Ballroom");
		Card conservatory = new RoomCard("Conservatory");
		Card diningRoom = new RoomCard("Dining Room");
		//Card cellar = new RoomCard("Cellar");
		Card billardRoom = new RoomCard("Billard Room");
		Card library = new RoomCard("Library");
		Card lounge = new RoomCard("Lounge");
		Card hall = new RoomCard("Hall");
		Card study = new RoomCard("Study");
		rooms.add(kitchen);
		rooms.add(ballroom);
		rooms.add(conservatory);
		rooms.add(diningRoom);
		rooms.add(billardRoom);
		rooms.add(library);
		rooms.add(lounge);
		rooms.add(hall);
		rooms.add(study);
	}

	public void setUpPlayers(int numberOfPlayers){
		
	for(int player = 0 ; player <= numberOfPlayers ; player++){
		
		// ensure correct name 
		// construct using name and start square(comes from array field index by player #) and add to field(arraylist of player)
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
			// move = true (in game still)
			// hyp = true in game
			// attempt correct sets has won true
			// attempt incorrect returns false out of game
			// board,draw
			// check if player won
		}
	}
}
