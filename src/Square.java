import java.util.ArrayList;

public abstract class Square {

	ArrayList<Square> conected = new ArrayList<>();
	Player current = null;
	Board board;

	public Square() {

	}

	public void addBoard(Board b) {
		board = b;
	}

	public void conectSquares(ArrayList<Square> conect) {
		conected.addAll(conect);
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

	public void removePlayer() {
		current = null;
		
	}
	

}
