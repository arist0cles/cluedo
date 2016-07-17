import java.util.ArrayList;

public abstract class Square {

	ArrayList<Square> conected = new ArrayList<>();

	public Square() {

	}

	public void conectSquares(ArrayList<Square> conect) {
		conected = conect;
	}

}
