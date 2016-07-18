import java.util.ArrayList;

public abstract class Square {

	ArrayList<Square> conected = new ArrayList<>();
	Player current;

	public Square() {

	}

	public void conectSquares(ArrayList<Square> conect) {
		conected = conect;
	}
	
	public void setPlayer(Player p){
		this.current = p;
	}

}
