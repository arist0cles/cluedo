
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSplitPaneUI;

/**
 * Represents the concept of a player in the game. There can be 3-6 players
 * playing at the same time. A player has access to the cards in their hand to
 * help inform their suggestions and accusations.
 * 
 * @author Zach and Patrick
 *
 */
public class Player {

	private Square current;
	private String charName;
	private String code;
	private List<Card> hand = new ArrayList<>();
	private boolean hasWon = false;

	public Player(String name, Square s) {
		this.charName = name;
		current = s;
		current.setPlayer(this);
		String[] splitName = name.split(" ");
		code = splitName[0].substring(0, 1) + splitName[1].substring(0, 1);
	}

	/**
	 * Adds a card to the players hand
	 * 
	 * @param added
	 *            the card to be added
	 */
	public void addToHand(Card added) {
		if (added != null) {
			hand.add(added);
		}
	}

	/**
	 * Gets the code
	 * 
	 * @return the players abbreviated name as a string
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Prints the hand
	 */
	public void printHand() {
		System.out.println("-----------" + charName + "s hand-----------");
		for (Card card : hand) {
			System.out.println(card.getName());
		}
		System.out.println("--------------------------------------------");
	}

	/**
	 * Deals with player picking a square to move to. Must enter a number/letter
	 * combo to represent the square in the 2d array. Ensures the input is not
	 * garbage
	 * 
	 * Calls move as a helper method
	 * 
	 * @param dieRoll
	 *            an int between 1-6, how many squares they can move
	 * @return true or false dependent on the move being successfully executed
	 */
	public boolean moveTurn(int dieRoll) {
		String moveLocation = "flame";
		String[] letter = null;
		System.out.println("you have rolled:" + dieRoll);
		System.out.println("enter move location format number-letter ");

		while (!moveLocation.matches("[0-9][0-9]*\\-[a-yA-Y]")) {
			if (Game.input.hasNext()) {
				moveLocation = Game.input.nextLine();
			}
		}
		letter = moveLocation.split("-");
		int firstIndex = Integer.parseInt(letter[0]);

		char c = Character.toLowerCase(letter[1].charAt(0));

		if (firstIndex > 24 || firstIndex < 0)
			return moveTurn(dieRoll);

		return (move(firstIndex, c, dieRoll));

	}

	/**
	 * Moves the player to the square, if that square is within diceRoll range
	 * 
	 * @param b
	 *            how many squares the player has chosen to move by, must be
	 *            less than dieRoll
	 * @param a
	 *            the character representing the columns of our 2d array. eg "A"
	 *            - 0 "Z" - 25
	 * @param dieRoll
	 *            int between 1-6 representing how many squares the player can
	 *            move
	 * @return true if a successful move, false otherwise
	 */
	public boolean move(int b, Character a, int dieRoll) {
		if (!(current.move(b, (int) a - 97, dieRoll) == null)) {
			current.removePlayer();
			current = current.move(b, (int) a - 97, dieRoll);
			current.current = this;
			return true;
		}
		return false;
	}

	/**
	 * Gets the square the player is currently on
	 * 
	 * @return the current square
	 */
	public Square getSquare() {
		return current;
	}

	/**
	 * Checks if the player has won
	 * 
	 * @return whether or not the player has won
	 */
	public boolean hasWon() {
		return hasWon;
	}

	/**
	 * Prompts the user to see if they would like to make a suggestion, only
	 * prompts when they enter a room that was not the last room they were in.
	 * 
	 * @param weapons
	 *            weapons currently active in the game
	 * @param people
	 *            people currently active in the game
	 * @param rooms
	 *            rooms currently active in the game
	 * @param isAccusation
	 * @return the suggestion
	 */
	public Suggestion promptSuggestion(List<Card> weapons, List<Card> people, List<Card> rooms, boolean isAccusation) {
		String ans = " a";

		System.out.println("Do you want to make a suggestion? (y = yes , n = no)");
		while (!ans.matches("[y]|[n]")) {
			ans = Game.input.nextLine();
		}
		if (ans.equals("y")) {
			return getSuggestion(weapons, people, rooms, isAccusation);
		} else {
			return null;
		}
	}

	/**
	 * Once the user has indicated that they would like to make a suggestion
	 * gets the relevant information from them. Gets the user to chooser a
	 * weapon, room and person card
	 * 
	 * @param weapons
	 *            the arraylist of weapons
	 * @param people
	 *            the arraylist of people
	 * @param rooms
	 *            the arraylist of rooms
	 * @param isAccusastion
	 *            whether of not the suggestion is an accusation
	 * @return a suggestions object
	 */
	private Suggestion getSuggestion(List<Card> weapons, List<Card> people, List<Card> rooms, boolean isAccusastion) {
		printHand();
		System.out.println("select a weapon using the number on the left ");
		for (int i = 0; i < weapons.size(); i++) {
			System.out.println(i + ":" + weapons.get(i).getName());
		}

		String ans = " a";

		while (!ans.matches("[0-5]")) {
			ans = Game.input.nextLine();
		}

		Card weapon = weapons.get(Integer.parseInt(ans));
		ans = "";

		System.out.println("select a person using the number on the left ");
		for (int i = 0; i < people.size(); i++) {
			System.out.println(i + ":" + people.get(i).getName());
		}

		while (!ans.matches("[0-5]")) {
			ans = Game.input.nextLine();
		}

		Card person = people.get(Integer.parseInt(ans));
		Card room = null;
		if (isAccusastion) {
			System.out.println("select a room using the number on the left ");
			for (int i = 0; i < rooms.size(); i++) {
				System.out.println(i + ":" + rooms.get(i).getName());
			}

			ans = " a";

			while (!ans.matches("[0-5]")) {
				ans = Game.input.nextLine();
			}
			room = rooms.get(Integer.parseInt(ans));
		} else {
			room = getRoom(rooms);
		}
		System.out.println(
				"your suggestion :" + person.getName() + " with the " + weapon.getName() + " in the " + room.getName());
		return new Suggestion(person, weapon, room);
	}

	/**
	 * Obtains the room the user is currently in. The suggested room can only be
	 * this room.
	 * 
	 * @param rooms
	 *            the arraylist of rooms
	 * @return the card representing the room the user is currently in
	 */
	private Card getRoom(List<Card> rooms) {
		String roomName = "";
		if (current instanceof RoomSquare) {
			roomName = ((RoomSquare) current).getRoom();
		}
		if (current instanceof RoomDoorSquare) {
			roomName = ((RoomDoorSquare) current).getRoom();
		}
		if (!roomName.equals("")) {
			for (Card c : rooms) {
				if (c.getName().equals(roomName)) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * Checks the hand to see if any of the suggestion cards are contained
	 * within
	 * 
	 * @param suggestion
	 *            the suggestion object
	 * @return an arraylist composed of all matching cards contained in this
	 *         players hand
	 */
	public ArrayList<Card> checkHand(Suggestion suggestion) {
		ArrayList<Card> result = new ArrayList<>();
		for (Card card : this.hand) {
			if (suggestion.contains(card)) {
				result.add(card);
			}
		}
		return result;
	}

	/**
	 * Allows the user to select a card to discard
	 * 
	 * @param matches
	 *            the arraylist the player can select a card to discard from
	 * @return the card to discard
	 */
	public Card selectDiscard(ArrayList<Card> matches) {
		System.out.println(charName + " must discard one of the following by selecting its index");
		for (int i = 0; i < matches.size(); i++) {
			System.out.println(i + " " + matches.get(i).getName());
		}
		String ans = "";
		int index = 999;

		while (index < 0 | index > matches.size()) {

			ans = Game.input.nextLine();
			index = Integer.parseInt(ans);

		}
		System.out.println("you have selected " + ans + ';' + matches.get(index).getName());

		Card discarded = matches.get(index);
		hand.remove(discarded);
		return discarded;
	}

	/**
	 * Gets the name of the character played by this player
	 * 
	 * @return the name
	 */
	public String getCharName() {
		return charName;
	}

	/**
	 * Prompts the user to see if they would like to make an accusation, only
	 * prompts when they enter a room that was not the last room they were in.
	 * 
	 * @param weapons
	 *            weapons currently active in the game
	 * @param people
	 *            people currently active in the game
	 * @param rooms
	 *            rooms currently active in the game
	 * @param isAccusation
	 * @return the suggestion
	 */
	public Suggestion promptAccusation(List<Card> weapons, List<Card> people, List<Card> rooms, boolean isAccusation) {
		String ans = " a";

		System.out.println("do you want to make a your attempt at the solution? (y = yes , n = no)");
		while (!ans.matches("[y]|[n]")) {

			ans = Game.input.nextLine();
		}
		if (ans.equals("y")) {
			return getSuggestion(weapons, people, rooms, isAccusation);

		} else {
			return null;
		}

	}

	/**
	 * Sets this player as the winner
	 * 
	 * @param b
	 *            has the player won or not
	 */
	public void setHasWon(boolean b) {
		hasWon = b;
	}

	/**
	 * Sets the players current square to not have a player associated with it
	 */
	public void remove() {
		this.current.setPlayer(null);
	}
	
	public List<Card> getHand(){
		return hand;
	}

}
