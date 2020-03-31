package zi.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author balamurugan
 */
public class Snake {

	public static void main(String[] args) {
		SnakeBoard board = new SnakeBoard(20, 20, 20);
	}
}

enum Direction {
	UP, DOWN, LEFT, RIGHT;
}

enum Shape {
	HEAD, TAIL, BODY, BLANK, FOOD;
}

class SnakeBoard extends JFrame {

	private final int nr, nc, size;
	private final Cell[][] grid;
	private final Random random = new Random();
	private final ScheduledExecutorService executor;
	private int[] foodLoc;
	private Direction dir;
	private Queue<Direction> directions;
	private int[] tail, head;
	private final JPanel gamePanel;
	private final JLabel levelLabel = new JLabel("1");
	private final JLabel scoreLabel = new JLabel("0");
	private int score = 0;
	private int count = 0;
	private int level = 1;

	SnakeBoard(int nr, int nc, int size) {
		this.nr = nr;
		this.nc = nc;
		this.size = size;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		gamePanel = new JPanel();
		add(gamePanel);
		gamePanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gamePanel.setPreferredSize(new Dimension(nr * size, nc * size));
		gamePanel.setLayout(new GridLayout(0, nc));
		grid = new Cell[nr][nc];
		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				grid[r][c] = new Cell(size - 2);
				gamePanel.add(grid[r][c]);
			}
		}
		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				render(r, c, Shape.BLANK, null);
			}
		}
		gamePanel.add(new JLabel("L"));
		gamePanel.add(levelLabel);
		gamePanel.add(new JLabel("S"));
		gamePanel.add(scoreLabel);
		pack();

		dir = Direction.RIGHT;
		render(0, 0, Shape.TAIL, dir);
		render(0, 1, Shape.BODY, null);
		render(0, 2, Shape.HEAD, dir);
		generateFoodLoc();

		head = new int[]{0, 2};
		tail = new int[]{0, 0};
		directions = new LinkedList<>();
		directions.offer(Direction.RIGHT);
		directions.offer(Direction.RIGHT);

		executor = Executors.newSingleThreadScheduledExecutor();
		executor.schedule((Runnable) this::move, 250, TimeUnit.MILLISECONDS);

		for (Direction direction : Direction.values()) {
			gamePanel.getActionMap().put(direction, new MoveAction(direction));
			gamePanel.getInputMap().put(KeyStroke.getKeyStroke(direction.toString()), direction);
		}
		gamePanel.grabFocus();

	}

	void render(int r, int c, Shape s, Direction dir) {
		Cell cell = grid[r][c];
		cell.s = s;
		cell.dir = dir;
		cell.repaint();
	}

	void generateFoodLoc() {
		int max = nr * nc;
		while (true) {
			int cell = random.nextInt(max);
			int r = cell / nc, c = cell % nc;
			if (grid[r][c].s == Shape.BLANK) {
				foodLoc = new int[]{r, c};
				render(r, c, Shape.FOOD, null);
				return;
			}
		}
	}

	void move() {
		move(dir);
	}

	synchronized void move(Direction dir) {
		if (this.dir != dir)
			this.dir = dir;
		else
			executor.schedule((Runnable) this::move, 280L - 30 * level, TimeUnit.MILLISECONDS);
		render(head[0], head[1], Shape.BODY, null);
		if (!canMove(head, dir)) {
			gameOver();
			return;
		}

		directions.offer(dir);
		if (foodLoc[0] == head[0] && foodLoc[1] == head[1]) {
			generateFoodLoc();
			count++;
			score += level;
			if (count == 3) {
				level++;
				count = 0;
			}
			levelLabel.setText(level + "");
			scoreLabel.setText(score + "");
			return;
		}

		render(tail[0], tail[1], Shape.BLANK, null);
		Direction tailDir = directions.poll();
		canMove(tail, tailDir);
		render(tail[0], tail[1], Shape.TAIL, tailDir);

		if (grid[head[0]][head[1]].s != Shape.BLANK) {
			gameOver();
			return;
		}
		render(head[0], head[1], Shape.HEAD, dir);
	}

	void gameOver() {
		executor.shutdown();
		remove(gamePanel);
		JPanel gameOverPanel = new JPanel();
		gameOverPanel.add(new JLabel("Game over! Your score:" + score));
		add(gameOverPanel);
		pack();
	}

	boolean canMove(int[] pos, Direction dir) {
		switch (dir) {
			case UP:
				pos[0]--;
				break;
			case LEFT:
				pos[1]--;
				break;
			case RIGHT:
				pos[1]++;
				break;
			default:
				pos[0]++;
		}
		return pos[0] >= 0 && pos[0] < nr && pos[1] >= 0 && pos[1] < nc;
	}

	class MoveAction extends AbstractAction implements ActionListener {
		private final Direction dir;

		MoveAction(Direction dir) {
			this.dir = dir;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			move(dir);
		}
	}
}

class Cell extends JPanel {
	Shape s;
	Direction dir;
	private final int size;

	Cell(int size) {
		this.size = size;
		setPreferredSize(new Dimension(size, size));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void paint(Graphics g) {
		if (s == null)
			return;
		g.clearRect(0, 0, size, size);
		switch (s) {
			case BLANK:
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, size, size);
				break;
			case BODY:
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0, 0, size, size);
				break;
			case TAIL:
				g.setColor(Color.DARK_GRAY);
				switch (dir) {
					case UP:
						g.fillPolygon(new int[]{0, size, size / 2}, new int[]{0, 0, size}, 3);
						break;
					case DOWN:
						g.fillPolygon(new int[]{0, size, size / 2}, new int[]{size, size, 0}, 3);
						break;
					case RIGHT:
						g.fillPolygon(new int[]{size, size, 0}, new int[]{0, size, size / 2}, 3);
						break;
					case LEFT:
						g.fillPolygon(new int[]{0, 0, size}, new int[]{0, size, size / 2}, 3);
				}
				break;
			case HEAD:
				g.setColor(Color.DARK_GRAY);
				switch (dir) {
					case UP:
						g.fillArc(0, 0, size, 2 * size, 0, 180);
						break;
					case RIGHT:
						g.fillArc(-size, 0, 2 * size, size, 270, 180);
						break;
					case LEFT:
						g.fillArc(0, 0, 2 * size, size, 270, -180);
						break;
					case DOWN:
						g.fillArc(0, -size, size, 2 * size, 0, -180);
				}
				break;
			case FOOD:
				g.setColor(Color.DARK_GRAY);
				g.fillPolygon(new int[]{0, size / 2, size, size / 2}, new int[]{size / 2, 0, size / 2, size}, 4);
		}
	}
}

