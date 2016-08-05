/**Represents the concept of a suggestion that the player can make upon entering a room that is no the previous room they
 * were in. A suggestion must be comprised of a Person, weapon and room.
 * 
 * @author Zach and Patrick
 *
 */
public class Suggestion {
	private Card Person;
	private Card Weapon;
	private Card Room;

	public Suggestion(Card person, Card weapon, Card room) {
		this.Person = person;
		this.Weapon = weapon;
		this.Room = room;
	}

	/**
	 * Standard contains method. Checks the name of the card passed in against 
	 * 
	 * @param card the card to check against
	 * @return true if the card is contained 
	 */
	public boolean contains(Card card) {
		if(card.getName() == Person.getName()){
			return true;
		}
		if(card.getName() == Weapon.getName()){
			return true;
		}
		if(card.getName() == Room.getName()){
			return true;
		}
		return false;
	}
}
