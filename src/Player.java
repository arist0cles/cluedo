
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSplitPaneUI;

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

	public void addToHand(Card added) {

		if (added != null) {
			hand.add(added);
		}
	}

	public String getCode() {
		return code;
	}

	public void printHand() {
		System.out.println("-----------" + charName + "s hand-----------");
		for (Card card : hand) {
			System.out.println(card.getName());
		}
		System.out.println("--------------------------------------------");
	}

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

		if (Integer.parseInt(letter[0]) > 24 || Integer.parseInt(letter[0]) < 0) {

			return moveTurn(dieRoll);
		}

		return (move(Integer.parseInt(letter[0]), letter[1].charAt(0), dieRoll));

	}

	public boolean move(int b, Character a, int dieRoll) {
		int hold = dieRoll;
		if (!(current.move(b, (int) a - 97, dieRoll) == null)) {
			current.removePlayer();
			current = current.move(b, (int) a - 97, hold);
			current.current = this;
			return true;
		}
		return false;
	}

	public Square getSquare() {

		return current;
	}

	public boolean hasWon() {
		return hasWon;
	}

	public Suggestion promptSuggestion(List<Card> weapons, List<Card> people, List<Card> rooms, boolean isAccusation) {
		String ans = " a";

		System.out.println("do you want to make a suggestion? (y = yes , n = no)");
		while (!ans.matches("[y]|[n]")) {

			ans = Game.input.nextLine();
		}
		if (ans.equals("y")) {

			return getSuggestion(weapons, people, rooms, isAccusation);
		} else {
			return null;
		}
	}

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
			System.out.println("select a roomusing the number on the left ");
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

	public ArrayList<Card> checkHand(Suggestion suggestion) {
		ArrayList<Card> result = new ArrayList<>();
		for (Card card : this.hand) {
			if (suggestion.contains(card)) {
				result.add(card);
			}
		}
		return result;
	}

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

	public String getCharName() {

		return charName;
	}

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

	public void setHasWon(boolean b) {
		hasWon = b;

	}

	public void remove() {
		this.current.setPlayer(null);
	}

}
