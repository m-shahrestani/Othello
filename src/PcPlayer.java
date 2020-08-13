import java.util.Scanner;

/**
 * A class to make PC player.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class PcPlayer extends Player{

    /**
     * Create a new PcPlayer with a given board and input.
     *
     * @param board board of game.
     * @param scanner input socket.
     */
    public PcPlayer(Board board, Scanner scanner) {
        super(board,scanner);
    }

    /**
     * This is an override method give input for move.
     *
     * @param turn the turn of game.
     * @return a String of (posX + " " + posY).
     */
    @Override
    public String move(int turn) {
        int current_player;
        int enemy;
        if (turn % 2 == 0) {
            current_player = 1;
            enemy = 2;
        } else {
            current_player = 2;
            enemy = 1;
        }

        int x = 0, y = 0;
        int maxScore = (board.getWScore() - board.getBScore());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getCells()[i][j].getPossible()) {
                    Board think = new Board();
                    for (int k = 0; k < 8; k++) {
                        for (int m = 0; m < 8; m++) {
                            think.getCells()[k][m].setByColor(board.getCells()[k][m].getColorId());
                            think.getCells()[k][m].setPossible(board.getCells()[k][m].getPossible());
                        }
                    }
                    think.doMovement(i, j, current_player, enemy);
                    think.calculateScore();
                    if (maxScore <= (think.getWScore() - think.getBScore())) {
                        maxScore = (think.getWScore() - think.getBScore());
                        x = i;
                        y = j;
                    }
                }
            }
        }
        System.out.println((x + 1) + " " + (char) ('A' + y));
        return (x + " " + y);
    }
}