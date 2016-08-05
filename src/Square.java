import java.util.ArrayList;

/**
 * An abstract representation of square, which is what the 25 x 25 grid is composed of. Squares can be either 
 * a room, normal or door square. Squares can be associated with a player if there is a player standing on them. 
 * 
 * @author Zach and Patrick
 *
 */
public abstract class Square {
	ArrayList<Square> conected = new ArrayList<>();
	Player current = null;
	Board board;

	public Square() {}

	public void addBoard(Board b) {
		board = b;
	}

	/**
	 * Connects and Arraylist of squares
	 * 
	 * @param connect the squares to connect
	 */
	public void conectSquares(ArrayList<Square> connect) {
		conected.addAll(connect);
	}
	
	public void connectSquare(Square s){
		conected.add(s);
	}

	public void setPlayer(Player p) {
		this.current = p;
	}

	public boolean hasPlayer() {
		return !(current == null);
	}

	public String getCode() {
		return current.getCode();
	}

	/**
	 * Floods the array until it finds the chosen square, then if this is a valid move, moves the player there
	 * 
	 * @param i "X" in the array
	 * @param b "Y" in the array
	 * @param moves the number of moves to flood until
	 * @return the square to move to if it's valid. Otherwise null
	 */
	public Square move(int i, int b, int moves) {
		if (moves == -1) {
			return null;
		}

		if (this.equals(board.GetSquare(i, b))) {
			return this;
			
		} else {
			for (Square s : conected) {
				
				Square Result =  s.move(i, b, moves-1);
				if(Result!=null){return Result;}
			}
		}
		return null;

	}

	/**
	 * Removes the player associated with this square and sets the field to null
	 */
	public void removePlayer() {
		current = null;
	}
	

}
