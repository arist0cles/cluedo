/**
 * Represents the concept of a door, connecting a room to the normal squares around it 
 * 
 * @author Zach and Patrick
 * 
 */
public class RoomDoorSquare extends Square {

	String direction;
	String room;

	public RoomDoorSquare(String direction) {
		String RoomCode = direction.substring(3, 4);
		this.direction = direction.substring(0, 3);

		if (RoomCode.equals("1")) {
			room = "Kitchen";
		}
		if (RoomCode.equals("2")) {
			room = "Ballroom";
		}
		if (RoomCode.equals("3")) {
			room = "Conservatory";
		}
		if (RoomCode.equals("4")) {
			room = "Dining Room";
		}
		if (RoomCode.equals("5")) {
			room = "Billard Room";
		}
		if (RoomCode.equals("6")) {
			room = "Library";
		}
		if (RoomCode.equals("7")) {
			room = "Lounge";
		}
		if (RoomCode.equals("8")) {
			room = "Hall";
		}
		if (RoomCode.equals("9")) {
			room = "Study";
		}
	}

	/**
	 * Get the room associated with this door
	 * 
	 * @return the name of the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * Get the direction this door faces
	 * 
	 * @return the direction this door faces
	 */
	public String getrDirection() {
		return direction;
	}
}
