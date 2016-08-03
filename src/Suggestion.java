
public class Suggestion {

	private Card Person;
	private Card Weapon;
	private Card Room;

	public Suggestion(Card person, Card weapon, Card room) {
		
		this.Person = person;
		this.Weapon = weapon;
		this.Room = room;

	}

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
