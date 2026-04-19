import java.util.Scanner;

// Game Board
class GameBoard {
    private char[][] board;
    private int rows, cols;

    public GameBoard(int r, int c) {
        rows = r;
        cols = c;
        board = new char[rows][cols];
        initBoard();
    }

    private void initBoard() {
        // Create outer walls and food
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    board[i][j] = '#';
                } else {
                    board[i][j] = '.';
                }
            }
        }

        // Fixed internal obstacles (maze style)
        int[][] walls = {
            {2,2},{2,3},{2,4},{2,6},{2,7},{2,8},{2,10},{2,11},{2,12},{2,14},{2,15},{2,16},
            {4,2},{5,2},{6,2},{4,5},{5,5},{6,5},{4,8},{5,8},{6,8},{4,11},{5,11},{6,11},{4,14},{5,14},{6,14},{4,17},{5,17},{6,17},
            {7,4},{7,5},{7,6},{7,10},{7,11},{7,12},{7,16},{7,17}
        };

        for (int[] w : walls) {
            board[w[0]][w[1]] = '#';
        }
    }

    public void drawBoard(Pacman pacman, Ghost ghost) {
        char[][] temp = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, temp[i], 0, cols);
        }

        temp[pacman.getX()][pacman.getY()] = 'P';
        temp[ghost.getX()][ghost.getY()] = 'G';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isWall(int x, int y) {
        return board[x][y] == '#';
    }

    public boolean eatFood(int x, int y) {
        if (board[x][y] == '.') {
            board[x][y] = ' ';
            return true;
        }
        return false;
    }
}

// Pacman Class
class Pacman {
    private int x, y;
    private int score;

    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
        score = 0;
    }

    public void move(char direction, GameBoard board) {
        int newX = x, newY = y;

        switch (direction) {
            case '^': newX--; break;
            case 'v': newX++; break;
            case '<': newY--; break;
            case '>': newY++; break;
        }

        if (!board.isWall(newX, newY)) {
            x = newX;
            y = newY;

            if (board.eatFood(x, y)) {
                score += 10;
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getScore() { return score; }
}

// Ghost Class
class Ghost {
    private int x, y;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(GameBoard board, Pacman pacman) {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    // Try moving toward Pacman first
    int bestX = Integer.compare(pacman.getX(), x);
    int bestY = Integer.compare(pacman.getY(), y);

    if (!board.isWall(x + bestX, y + bestY)) {
        x += bestX;
        y += bestY;
        return;
    }

    // Otherwise try all directions
    for (int i = 0; i < 4; i++) {
        int newX = x + dx[i];
        int newY = y + dy[i];

        if (!board.isWall(newX, newY)) {
            x = newX;
            y = newY;
            return;
        }
    }
}

    public int getX() { return x; }
    public int getY() { return y; }
}

// Food Class
class Food {
    private int value = 10;

    public int getValue() {
        return value;
    }
}

// Main Game Class
public class PacmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameBoard board = new GameBoard(10, 20);
        Pacman pacman = new Pacman(1, 1);
        Ghost ghost = new Ghost(8, 18);

        boolean running = true;

        while (running) {
            System.out.println("\nScore: " + pacman.getScore());
            board.drawBoard(pacman, ghost);

            System.out.print("Move (<, >, ^, v) or q to quit: ");
            char input = scanner.next().charAt(0);

            if (input == 'q') break;

            pacman.move(input, board);
            ghost.move(board, pacman);

            if (pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()) {
                System.out.println("Game Over! Caught by Ghost.");
                running = false;
            }
        }

        System.out.println("Final Score: " + pacman.getScore());
        scanner.close();
    }
}
