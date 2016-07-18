
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	private Square[][] board = new Square[25][25];

	// read all squares int 2d array including types doors ?(first letter of
	// room name)D(door)S(direction south east...)
	// connect all squares that are not walls
	// connect stairs
	public Board() {
		getSquares();

	}

	public void getSquares() {
		Scanner s;
		try {

			s = new Scanner(new File("board.txt"));
			int row = 0;
			int col = 0;

			while (s.hasNext()) {

				String line = s.next();
				String[] squares = line.split(",");

				for (String square : squares) {

					if (col == 25) {
						col = 0;
						row++;
					}
					if (square.equals("N")) {

						board[row][col] = new NormalSquare();
						col++;
					}
					if (square.startsWith("RD")) {

						board[row][col] = new RoomDoorSquare(square);
						col++;
					}
					if (square.equals("R")) {

						board[row][col] = new RoomSquare();
						col++;
					}
				}
			}

			s.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		link();
		draw();
	}

	public void link() {
		for (int row = 0; row < 25; row++) {
			for (int col = 0; col < 25; col++) {

				ArrayList<Square> conected = new ArrayList<>();

				if (row > 0) {
					Square above = board[row - 1][col];
					if (above.getClass() == board[row][col].getClass()) {
						conected.add(above);
					}
				}

				if (row < 24) {
					Square below = board[row + 1][col];
					if (below.getClass() == board[row][col].getClass()) {
						conected.add(below);
					}
				}

				if (col > 0) {
					Square left = board[row][col - 1];
					if (left.getClass() == board[row][col].getClass()) {
						conected.add(left);
					}
				}

				if (col < 24) {
					Square right = board[row][col + 1];
					if (right.getClass() == board[row][col].getClass()) {
						conected.add(right);
					}
				}

				if (board[row][col].getClass() == RoomDoorSquare.class) {
					Square door = board[row][col];
					if (door instanceof RoomDoorSquare) {
						if (((RoomDoorSquare) door).getrDirection().equals("RDN")) {
							conected.add(board[row - 1][col]);
						}
						if (((RoomDoorSquare) door).getrDirection().equals("RDE")) {
							conected.add(board[row][col + 1]);
						}
						if (((RoomDoorSquare) door).getrDirection().equals("RDS")) {
							conected.add(board[row + 1][col]);
						}
						if (((RoomDoorSquare) door).getrDirection().equals("RDW")) {
							conected.add(board[row][col - 1]);
						}

						if (row > 0) {
							Square above = board[row - 1][col];
							if (above.getClass() == board[row][col].getClass()) {
								conected.add(above);
							}
						}

						if (row < 24) {
							Square below = board[row + 1][col];
							if (below.getClass() == RoomSquare.class) {
								conected.add(below);
							}
						}

						if (col > 0) {
							Square left = board[row][col - 1];
							if (left.getClass() == RoomSquare.class) {
								conected.add(left);
							}
						}

						if (col < 24) {
							Square right = board[row][col + 1];
							if (right.getClass() == RoomSquare.class) {
								conected.add(right);
							}
						}

					}
				}

				board[row][col].conectSquares(conected);
			}

		}
	}

	public void draw() {
		String[] alpha = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y" };
		System.out.print("   ");
		for (int i = 0; i < alpha.length; i++) {
			System.out.print(alpha[i] + "  ");
		}
		System.out.println();
		int i = 0 ;
		for (Square[] row : board) {
			if(i<10){
				System.out.print(i++ + "  ");
			}else{System.out.print(i++ + " ");}
			
			for (Square s : row) {

				if (s instanceof NormalSquare) {
					// do draw method
					System.out.print("0  ");
				}

				if (s instanceof RoomSquare) {
					// do draw method
					System.out.print("#  ");
				}

				if (s instanceof RoomDoorSquare) {
					// do draw method
					if (((RoomDoorSquare) s).getrDirection().equals("RDS")
							|| ((RoomDoorSquare) s).getrDirection().equals("RDN")) {
						System.out.print("|| ");
					} else {
						System.out.print("=  ");
					}
				}

			}

			System.out.print("\n");
		}
	}
}
