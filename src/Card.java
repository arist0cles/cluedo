
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
