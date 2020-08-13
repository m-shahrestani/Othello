import java.util.Scanner;

/**
 * A class to manage Othello game.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class GameManager {
    //mode of game 1 for two players and 2 for with PC.
    private int mode;
    //the status of game for continue.
    private boolean gameStatus;
    //board game.
    private Board board;
    //human player1
    private Player humanPlayer1;
    //human player2
    private Player humanPlayer2;
    //PC player
    private Player pc;

    /**
     * Create a new GameManager with given mode and input.
     *
     * @param mode mode of game.
     * @param scanner input socket.
     */
    public GameManager(int mode, Scanner scanner) {
        this.mode = mode;
        this.gameStatus = true;
        board = new Board();
        humanPlayer1 = new Player(board, scanner);
        if (mode == 1) {
            humanPlayer2 = new Player(board, scanner);
        }
        if (mode == 2) {
            pc = new PcPlayer(board, scanner);
        }
    }

    /**
     * move with the given turn.
     * @param turn turn of player.
     * @return true if the move was made, false otherwise.
     */
    public boolean move(int turn) {
        int current_player;
        int enemy;
        if (turn % 2 == 0) {
            current_player = 1;
            enemy = 2;
        } else {
            current_player = 2;
            enemy = 1;
        }

        if (mode == 1) {
            if (turn % 2 == 0) {
                String input = humanPlayer1.move(turn);
                int posX = input.charAt(0) - '0';
                int posY = input.charAt(2) - '0';
                if (validMove(posX, posY)) {
                    board.doMovement(posX, posY, current_player, enemy);
                    board.setPossibles(enemy, current_player);
                    board.calculateScore();
                    return true;
                }
                return false;
            }
            if (turn % 2 == 1) {
                String input = humanPlayer2.move(turn);
                int posX = input.charAt(0) - '0';
                int posY = input.charAt(2) - '0';
                if (validMove(posX, posY)) {
                    board.doMovement(posX, posY, current_player, enemy);
                    board.setPossibles(enemy, current_player);
                    board.calculateScore();
                    return true;
                }
                return false;
            }
        }
        if (mode == 2) {
            if (turn % 2 == 0) {
                String input = humanPlayer1.move(turn);
                int posX = input.charAt(0) - '0';
                int posY = input.charAt(2) - '0';
                if (validMove(posX, posY)) {
                    board.doMovement(posX, posY, current_player, enemy);
                    board.setPossibles(enemy, current_player);
                    board.calculateScore();
                    return true;
                }
                return false;
            }
            if (turn % 2 == 1) {
                String input = pc.move(turn);
                int posX = input.charAt(0) - '0';
                int posY = input.charAt(2) - '0';
                if (validMove(posX, posY)) {
                    board.doMovement(posX, posY, current_player, enemy);
                    board.setPossibles(enemy, current_player);
                    board.calculateScore();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * get The mode of game.
     * @return mode.
     */
    public int getMode() {
        return mode;
    }

    /**
     * get The status of game.
     * @return gameStatus.
     */
    public boolean getGameStatus() {
        return gameStatus;
    }

    /**
     * @param gameStatus set the status of game for continue.
     */
    public void setGameStatus(boolean gameStatus) {
        System.out.println("Game finished.");
        this.gameStatus = gameStatus;
    }

    /**
     * Show the board.
     */
    public void show() {
        board.showBoard();
    }

    /**
     * Determine validity of given position for move.
     * @param x x position.
     * @param y y position.
     * @return true if the given position is valid for move, false otherwise.
     */
    public boolean validMove(int x, int y) {
        if(0 <= x && x < 8 && 0 <= y && y < 8 && board.isValid(x, y)) {
            return true;
        }
        else {
            System.out.println("Invalid choice.");
            return false;
        }
    }

    /**
     * Determine condition of passing.
     * @param turn turn of player.
     * @return true if the turn is pass, false otherwise.
     */
    public boolean checkPass(int turn) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.isValid(i, j)) {
                    count++;
                }
            }
        }
        if (count != 0) {
            return false;
        }
        else {
            if (turn % 2 == 0) {
                board.setPossibles( 2, 1);
            }
            else {
                board.setPossibles(1, 2);
            }
            return true;
        }
    }

    /**
     * Determine status of game finishing.
     * if board was full, set gameStatus false/
     */
    public void isFinish(){
        if (board.getBScore() + board.getWScore() == 64) {
            setGameStatus(false);
        }
    }
}
