
public class RoomSquare extends Square{
	String room ;
	public RoomSquare(String RoomCode){
		if(RoomCode.equals("R1")){room = "Kitchen";}
		if(RoomCode.equals("R2")){room = "Ballroom";}
		if(RoomCode.equals("R3")){room = "Conservatory";}
		if(RoomCode.equals("R4")){room = "Dining Room";}
		if(RoomCode.equals("R5")){room = "Billard Room";}
		if(RoomCode.equals("R6")){room = "Library";}
		if(RoomCode.equals("R7")){room = "Lounge";}
		if(RoomCode.equals("R8")){room = "Hall";}
		if(RoomCode.equals("R9")){room = "Study";}
		
	}
public String getRoom(){
		
		return room;
	}
}
