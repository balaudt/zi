package zi.leet.w92;

import java.util.Scanner;

/**
 * @author balamurugan
 */
public class TicTacToe {
	private char[][] board = new char[3][3];

	public TicTacToe() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
	}

	public void addToken(char token, int row, int col) {
		board[row][col] = token;
	}

	public void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length - 1; j++) {
				System.out.print(board[i][j] + "|");
			}
			System.out.println(board[i][2]);
		}
	}

	public boolean isFull() {
		boolean isFull = true;
		for (int i = 0; i < board.length && isFull; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '-') {
					isFull = false;
					break;
				}
			}
		}
		return isFull;
	}

	public void hasAnyoneWon() {

	}

	public void makeAMove() throws Exception {
		if (isFull()) {
			throw new Exception("The board is full");
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '-') {
					board[i][j] = 'O';
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		TicTacToe ticTacToe = new TicTacToe();
		while (!ticTacToe.isFull()) {
			String[] in = scanner.nextLine().split(" ");
			boolean isValidInput = true;
			int row = -1, column = -1;
			if (in.length != 2) {
				isValidInput = false;
			} else {
				try {
					row = Integer.parseInt(in[0]);
					column = Integer.parseInt(in[1]);
				} catch (NumberFormatException e) {
					isValidInput = false;
				}
			}
			if (!isValidInput || row > 2 || column > 2 || row < 0 || column < 0) {
				System.out.println("Please enter a valid input in the format row, column [0 <= row, column <=2]");
				continue;
			}
			if (ticTacToe.board[row][column] != '-') {
				System.out.println(String.format("The cell (%d,%d) is already filled. Please try an another one", row, column));
				continue;
			}
			ticTacToe.addToken('X', row, column);
			ticTacToe.print();
			if (!ticTacToe.isFull()) {
				ticTacToe.makeAMove();
				System.out.println("System made a move");
				ticTacToe.print();
			}
		}
		System.out.println("Game over");
	}
}
