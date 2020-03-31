package zi.leet.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author balamurugan
 */
public class SnakeGame {

	private char[][] board;
	private int R, C, size, foodPos;
	private int[][] food;
	private int[] tail, head;
	private Queue<Character> directions;

	/**
	 * Initialize your data structure here.
	 *
	 * @param width  - screen width
	 * @param height - screen height
	 * @param food   - A list of food positions
	 *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
	 */
	public SnakeGame(int width, int height, int[][] food) {
		R = height;
		C = width;
		board = new char[R][C];
		this.food = food;
		board[0][0] = 'S';
		tail = new int[]{0, 0};
		head = new int[]{0, 0};
		directions = new LinkedList<>();
	}

	/**
	 * Moves the snake.
	 *
	 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 * @return The game's score after the move. Return -1 if game over.
	 * Game over when snake crosses the screen boundary or bites its body.
	 */
	public int move(String direction) {
		char curDir = direction.charAt(0);
		if (!move(head, curDir))
			return -1;
		boolean didEat = false;
		if (foodPos < food.length) {
			int[] foodLoc = food[foodPos];
			if (foodLoc[0] == head[0] && foodLoc[1] == head[1]) {
				size++;
				foodPos++;
				didEat = true;
			}
		}

		directions.offer(curDir);

		if (didEat) {
			if (board[head[0]][head[1]] == 'S')
				return -1;
			board[head[0]][head[1]] = 'S';
			return size;
		}

		board[tail[0]][tail[1]] = ' ';
		int[] newTail = new int[]{tail[0], tail[1]};
		char tailDir = directions.poll();
		if (move(newTail, tailDir)) {
			tail[0] = newTail[0];
			tail[1] = newTail[1];
		} else {
			tailDir = directions.poll();
			move(tail, tailDir);
		}
		if (board[head[0]][head[1]] == 'S')
			return -1;
		board[head[0]][head[1]] = 'S';
		return size;
	}

	private boolean move(int[] pos, char dir) {
		switch (dir) {
			case 'U':
				pos[0]--;
				break;
			case 'L':
				pos[1]--;
				break;
			case 'R':
				pos[1]++;
				break;
			case 'D':
				pos[0]++;
				break;
		}
		return pos[0] >= 0 && pos[0] < R && pos[1] >= 0 && pos[1] < C;
	}
}
