/**
 * Represents the Die object in a Cluedo game
 * 
 * @author Zach and Patrick
 *
 */
public class Die {
	/**
	 * Returns a random number between 1-6 representing the roll of the dice
	 * 
	 * @return random number
	 */
	public int roll() {
		return (int)((Math.random()*6)+1);
	}
}
