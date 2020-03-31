package zi.leet.easy;

//https://leetcode.com/problems/available-captures-for-rook
public class RookCapture {
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        int[] rookPosition = null;
        for (int i = 0; i < 8 && rookPosition == null; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    rookPosition = new int[]{i, j};
                }
            }
        }
        for (int row = Math.max(rookPosition[0] - 1, 0); row >= 0; row--) {
            if (board[row][rookPosition[1]] == 'B')
                break;
            if (board[row][rookPosition[1]] == 'p') {
                ans++;
                break;
            }
        }
        for (int row = Math.min(rookPosition[0] + 1, 7); row <= 7; row++) {
            if (board[row][rookPosition[1]] == 'B')
                break;
            if (board[row][rookPosition[1]] == 'p') {
                ans++;
                break;
            }
        }
        for (int col = Math.max(rookPosition[1] - 1, 0); col >= 0; col--) {
            if (board[rookPosition[0]][col] == 'B')
                break;
            if (board[rookPosition[0]][col] == 'p') {
                ans++;
                break;
            }
        }
        for (int col = Math.min(rookPosition[1] + 1, 7); col <= 7; col++) {
            if (board[rookPosition[0]][col] == 'B')
                break;
            if (board[rookPosition[0]][col] == 'p') {
                ans++;
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        RookCapture rookCapture = new RookCapture();
        System.out.println(rookCapture.numRookCaptures(new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', 'R', '.', '.', '.', 'p'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}}));
        System.out.println(rookCapture.numRookCaptures(new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'}, {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'}, {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'}, {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'}, {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}}));
        System.out.println(rookCapture.numRookCaptures(new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'p', 'p', '.', 'R', '.', 'p', 'B', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'B', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}}));
    }
}
