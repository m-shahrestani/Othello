import java.util.Scanner;

/**
 * A class to make player.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Player {
    //Board
    protected Board board;
    //scanner
    protected Scanner scanner;

    /**
     * Create a new Player with a given board and input.
     *
     * @param board board of game.
     * @param scanner input socket.
     */
    public Player(Board board, Scanner scanner) {
        this.board = board;
        this.scanner = scanner;
    }

    /**
     * This method give input for move.
     *
     * @param turn the turn of game.
     * @return a String of (posX + " " + posY).
     */
    public String move(int turn) {
        int posX = (scanner.next().trim().charAt(0)) - '0' - 1;
        int posY = (scanner.next().trim().charAt(0)) - 'A';
        return (posX + " " + posY);
    }
}