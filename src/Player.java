
public class Player {

	Square current;
	PersonCard charName;
	PersonCard weapon;
	PersonCard room;

	public Player(PersonCard charName, Square s) {
		this.charName = charName;
		current = s;
	}
}
