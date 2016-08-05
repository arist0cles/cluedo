/**
 * 
 * Represents the Abstract concept of card. What is a card? A card is either a weapon, room or a person
 * 3 cards are stored at the start of them game as a solution and the rest are dealt to the players as a hand
 * 
 * @author Zach and Patrick
 *
 */
public abstract class Card {

	private String Name;

	public Card(String name) {
		Name = name;
	}

	/**
	 * Helper method returning the name of the card
	 * 
	 * @return the name of the card
	 */
	public String getName() {
		return Name;
	}

}
