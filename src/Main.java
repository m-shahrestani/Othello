import java.util.Scanner;

/**
 * A class to drive program.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {

        //make Othello game
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mode:");
        System.out.println("1- two player.");
        System.out.println("2- with PC.");
        GameManager play = new GameManager(scanner.nextInt(), scanner);

        //2 players input
        if (play.getMode() == 1) {
            play.show();
            int turn = 0;
            while (play.getGameStatus()) {
                if (turn % 2 == 0) {
                    while (play.getGameStatus()) {
                        System.out.println("Black turn: (Enter number+ +character for example: 5 F)");
                        if (play.checkPass(turn)) {
                            System.out.println("pass.");
                            turn++;
                            if (play.checkPass(turn)) {
                                System.out.println("White turn: (Enter number+ +character for example: 5 F)");
                                System.out.println("pass.");
                                play.setGameStatus(false);
                            }
                            play.show();
                            break;
                        }
                        if (play.move(turn)) {
                            play.isFinish();
                            play.show();
                            turn++;
                            break;
                        }
                    }
                } else {
                    while (play.getGameStatus()) {
                        System.out.println("white turn: (Enter number+ +character for example: 5 F)");
                        if (play.checkPass(turn)) {
                            System.out.println("pass.");
                            turn++;
                            if (play.checkPass(turn)) {
                                System.out.println("Black turn: (Enter number+ +character for example: 5 F)");
                                System.out.println("pass.");
                                play.setGameStatus(false);
                            }
                            play.show();
                            break;
                        }
                        if (play.move(turn)) {
                            play.isFinish();
                            play.show();
                            turn++;
                            break;
                        }
                    }
                }
            }
        }

        //with PC input
        if (play.getMode() == 2) {
            play.show();
            int turn = 0;
            while (play.getGameStatus()) {
                if (turn % 2 == 0) {
                    while (true) {
                        System.out.println("Black turn: (Enter number+ +character for example: 5 F) ");
                        if (play.checkPass(turn)) {
                            System.out.println("pass. ");
                            turn++;
                            if (play.checkPass(turn)) {
                                System.out.println("White turn: (Enter number+ +character for example: 5 F)");
                                System.out.println("pass.");
                                play.setGameStatus(false);
                            }
                            play.show();
                            break;
                        }
                        if (play.move(turn)) {
                            play.isFinish();
                            play.show();
                            turn++;
                            break;
                        }
                    }
                } else {
                    System.out.println("white turn: (Enter number+ +character for example: 5 F)");
                    if (play.checkPass(turn)) {
                        System.out.println("pass.");
                        turn++;
                        if (play.checkPass(turn)) {
                            System.out.println("Black turn: (Enter number+ +character for example: 5 F)");
                            System.out.println("pass.");
                            play.setGameStatus(false);
                        }
                        play.show();
                    }
                    if(turn % 2 == 1) {
                        play.move(turn);
                        play.isFinish();
                        play.show();
                        turn++;
                    }
                }
            }
        }

        //other input
        else {
            System.exit(-1);
        }
    }
}
