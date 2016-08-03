
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

public String getRoom(){
		
		return room;
	}

	public String getrDirection() {
		return direction;
	}
}
