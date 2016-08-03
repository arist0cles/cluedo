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
	private List<Square> startSquares = new ArrayList<>();
	private List<Card> ruledOut = new ArrayList<Card>();
	private Board board;
	public static Scanner input = new Scanner(System.in);

	public Game() {

		board = new Board();
		board.getStartSquares(startSquares);
		setupCards();
		setUpPlayers();
		die = new Die();
		generateSolution();
		ArrayList<Card> fullDeck = new ArrayList<>();
		fullDeck.addAll(people);
		fullDeck.addAll(weapons);
		fullDeck.addAll(rooms);
		dealCards(fullDeck);
		playing();

	}

	/**
	 * 
	 * Deals the weapon, player and room cards minus the ones in the solution
	 * 
	 * @param deal the arraylist of cards to be dealt
	 */
	public void dealCards(List<Card> deal) {

		while (!deal.isEmpty()) {

			if ((deal.size()) % numOfPlayers == 0) {
				for (Player p : players) {
					
					Card dealt = deal.get((int) (Math.random() * deal.size()));
					while(solution.contains(dealt)){
						deal.remove(dealt);
						if(deal.isEmpty()){return;}
						dealt = deal.get((int) (Math.random() * deal.size()));
					}
					p.addToHand(dealt);
					deal.remove(dealt);
				}
			} else {
				Card dealt = deal.get((int) (Math.random() * deal.size()));
				while(solution.contains(dealt)){
					deal.remove(dealt);
					if(deal.isEmpty()){return;}
					dealt = deal.get((int) (Math.random() * deal.size()));
				}
				ruledOut.add(dealt);
				deal.remove(dealt);
			}
		}

	}

	/**
	 * Randomly generates a solution made up of one person, weapon and room cards. These are
	 * not dealt to players
	 */
	public void generateSolution() {

		Card p = people.get((int) (Math.random() * 6));
		solution.add(p);
		

		Card w = weapons.get((int) (Math.random() * 6));
		solution.add(w);
		

		Card r = rooms.get((int) (Math.random() * 6));
		solution.add(r);
		

	}

	/**
	 * Helper method for setup, prompts the user for how many players then
	 * assigns the players characters randomly
	 */
	private void setUpPlayers() {

		System.out.println("How many players?");

		numOfPlayers = Game.input.nextInt();
		while (numOfPlayers > 6 || numOfPlayers < 2) {
			System.out.println("Sorry, player number must be 2-6");
			numOfPlayers = Game.input.nextInt();
		}
		for (int i = 0; i < numOfPlayers; i++) {
			players.add(new Player(people.get(i).getName(), startSquares.get(i)));
		}

	}

	/**
	 * Prints out the layout of the rooms to help the player
	 */
	public void printRooms() {
		System.out.println("__________________________________ROOMS______________________________________");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("-kitchen-------------------------ballRoom-----------------------conservatory-");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------billardRoom-");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("-dinningRoom-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------Library-");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("-Lounge----------------------------Hall--------------------------------Study-");
		System.out.println();
	}

	/**
	 * Prints out the cards which have been ruled out
	 */
	public void printRuledOut() {
		System.out.println("________________________________Ruled_Out____________________________________");
		for (Card c : ruledOut) {
			System.out.println(c.getName());
		}
		System.out.println("_____________________________________________________________________________");
	}

	/**
	 * Helper method for setup, generates the deck
	 */
	private void setupCards() {
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
		// Card cellar = new RoomCard("Cellar");
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

	/**
	 * Checks the suggestion against the players hands, if they have a matching card/cards they 
	 * must select the one to discard
	 * 
	 * @param suggestion an object containing the cards making up the suggestion
	 * @param suggester the player making the suggestion
	 */
	public void checkSuggestion(Suggestion suggestion, Player suggester) {
		for (Player p : players) {
			if (p != suggester) {
				ArrayList<Card> matches = p.checkHand(suggestion);
				if (matches.size() > 0) {
					Card discarded = p.selectDiscard(matches);
					ruledOut.add(discarded);
					return;
				}
			}
		}
	}

	/**
	 * Loops repeatedly while the game has not been won. Goes through each player 
	 * 
	 * 
	 */
	public void playing() {

		boolean onGoing = true;

		while (onGoing) {
			// roll die
			if (!players.isEmpty()) {
				for (Player p : players) {
					String startRoom = "";
					if (p.getSquare().getClass() != NormalSquare.class) {
						if (p.getSquare() instanceof RoomSquare) {
							startRoom = ((RoomSquare) p.getSquare()).getRoom();
						} else if (p.getSquare() instanceof RoomDoorSquare) {
							startRoom = ((RoomDoorSquare) p.getSquare()).getRoom();
						}
					}

					printRooms();
					board.draw();
					printRuledOut();
					for(Card c :solution){
						System.out.println(c.getName());
					}
					int dieRoll = die.roll();
					System.out.println("                  " + p.getCharName() + "s turn");

					while (p.moveTurn(dieRoll) == false) {
						System.out.println("cant move there");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

					}

					String newRoom = startRoom;

					if (p.getSquare().getClass() != NormalSquare.class) {
						if (p.getSquare() instanceof RoomSquare) {
							newRoom = ((RoomSquare) p.getSquare()).getRoom();
						} else if (p.getSquare() instanceof RoomDoorSquare) {
							newRoom = ((RoomDoorSquare) p.getSquare()).getRoom();
						}
					}

					if (!newRoom.equals(startRoom)) {
						Suggestion suggestion = p.promptSuggestion(weapons, people, rooms, false);
						if (suggestion != null) {
							checkSuggestion(suggestion, p);
						} else {
							System.out.println("did not make a suggestion");
						}
					}
					Suggestion attempt = p.promptAccusation(weapons, people, rooms, true);
					if (attempt != null) {
						if (checkAttempt(attempt)) {
							p.setHasWon(true);
						} else {
							p.remove();
							players.remove(p);
						}

					}
					if (p.hasWon()) {
						onGoing = false;
						break;
					}

				}
			}
		}
		if (onGoing == false) {
			System.out.println("a player has won");
			// win case 
		} else {
			onGoing = false;
			System.out.println("all players have lost");
		}
	}

	/**
	 * Checks to see if the suggestion/accusation was correct
	 * 
	 * @param attempt the accustaion
	 * @return whether the accusation was successful or not
	 */
	private boolean checkAttempt(Suggestion attempt) {
		for (Card c : solution) {
			if (!attempt.contains(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * The main
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new Game();
	}
}
