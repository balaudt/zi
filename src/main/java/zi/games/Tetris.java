package zi.games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author balamurugan
 */
public class Tetris extends JFrame {
	final int nr = 40, nc = 10, cellSize = 10;
	final StatsBoard statsBoard = new StatsBoard();
	final GameBoard gameBoard = new GameBoard(nr, nc, cellSize, statsBoard);

	enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	Tetris() {
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setResizable(false);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		getContentPane().add(gameBoard);
		statsBoard.init();
		gameBoard.init();
		getContentPane().add(statsBoard);
		pack();
		gameBoard.grabFocus();
	}

	public static void main(String[] args) {
		Tetris tetris = new Tetris();
	}

	enum Shape {
		S(new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}}),
		Z(new int[][]{{0, 1}, {1, 0}, {1, 1}, {2, 0}}),
		T(new int[][]{{0, 1}, {1, 0}, {1, 1}, {1, 2}}),
		L(new int[][]{{0, 0}, {0, 1}, {1, 0}, {2, 0}}),
		LINE(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}}),
		MIRROR_L(new int[][]{{0, 0}, {0, 1}, {1, 1}, {2, 1}}),
		SQUARE(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
		final int[][] cells;
		static final Random random = new Random();

		Shape(int[][] cells) {
			this.cells = cells;
		}

		static Shape getRandom() {
			return values()[random.nextInt(values().length)];
		}

		static int[][] rotate(int[][] in) {
			int[][] out = new int[4][2];
			int R = 0;
			for (int[] cell : in)
				R = Math.max(R, cell[0]);
			for (int i = 0; i < in.length; i++) {
				int[] cell = in[i];
				out[i][0] = cell[1];
				out[i][1] = R - cell[0];
			}
			return out;
		}
	}

	class GameBoard extends JPanel {
		final int nr, nc, cellSize;
		Cell[][] grid;
		final StatsBoard statsBoard;
		List<Shape> nextShapes;
		int r, c;
		int[][] shapeRendered;
		final int[] rowCounts;

		GameBoard(int nr, int nc, int cellSize, StatsBoard statsBoard) {
			this.nr = nr;
			this.nc = nc;
			this.cellSize = cellSize;
			this.statsBoard = statsBoard;
			rowCounts = new int[nr];
		}

		void init() {
//			setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			setPreferredSize(new Dimension(nc * cellSize, nr * cellSize));
			setLayout(new GridLayout(0, nc));
			grid = new Cell[nr][nc];
			for (int r = 0; r < nr; r++)
				for (int c = 0; c < nc; c++) {
					grid[r][c] = new Cell(cellSize);
					add(grid[r][c]);
				}
			for (int r = 0; r < nr; r++)
				for (int c = 0; c < nc; c++)
					grid[r][c].repaint();
			nextShapes = new LinkedList<>();
			statsBoard.nextShapes = nextShapes;
			for (int i = 0; i < 3; i++)
				nextShapes.add(Shape.getRandom());
			statsBoard.render();
			shapeRendered = Shape.L.cells;
			r = 0;
			c = nc / 2;
			render(true);

			getActionMap().put("rotate", new CustomAction(this::rotate));
			getInputMap().put(KeyStroke.getKeyStroke('r'), "rotate");

			for (Direction direction : Direction.values()) {
				getActionMap().put(direction, new CustomAction(() -> {
					render(false);
					switch (direction) {
						case LEFT:
							c--;
							break;
						case RIGHT:
							c++;
							break;
						case DOWN:
							r++;
							break;
					}
					render(true);
				}));
				getInputMap().put(KeyStroke.getKeyStroke(direction.toString()), direction);
			}
		}

		void rotate() {
			render(false);
			shapeRendered = Shape.rotate(shapeRendered);
			render(true);
		}

		void render(boolean isFilled) {
			for (int[] cell : shapeRendered) {
				grid[r + cell[0]][c + cell[1]].isFilled = isFilled;
				grid[r + cell[0]][c + cell[1]].repaint();
			}
		}
	}

	class CustomAction extends AbstractAction {
		final Runnable action;

		CustomAction(Runnable action) {
			this.action = action;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			action.run();
		}
	}

	class StatsBoard extends JPanel {
		final JLabel[] nextShapeLabels = new JLabel[3];
		final JLabel levelLabel = new JLabel();
		final JLabel scoreLabel = new JLabel();
		List<Shape> nextShapes;
		int level = 1, score = 0;

		void init() {
			setLayout(new GridLayout(0, 3));
//			setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			for (int i = 0; i < nextShapeLabels.length; i++) {
				nextShapeLabels[i] = new JLabel();
				add(nextShapeLabels[i]);
			}
			add(levelLabel);
			add(scoreLabel);
		}

		void render() {
			levelLabel.setText(level + "");
			scoreLabel.setText(score + "");
			int i = 0;
			for (Shape nextShape : nextShapes) {
				nextShapeLabels[i++].setText(nextShape.name());
			}
		}
	}

	class Cell extends JPanel {
		boolean isFilled;
		final int cellSize;

		Cell(int cellSize) {
			this.cellSize = cellSize;
		}

		@Override
		public void paint(Graphics g) {
			g.setColor(isFilled ? Color.DARK_GRAY : Color.LIGHT_GRAY);
			g.clearRect(0, 0, cellSize, cellSize);
			g.fillRect(0, 0, cellSize, cellSize);
		}

	}
}
