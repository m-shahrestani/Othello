/**
 * A class to make game board.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Board {
    //an Array to hold Cells.
    private Cell[][] cells;
    //score of black player.
    private int bScore;
    //score of white player.
    private int wScore;

    /**
     * Create a new Board.
     *
     */
    public Board () {
        cells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++ ) {
                cells[i][j] = new Cell();
            }
        }

        this.setWhite(4,4);
        this.setWhite(3,3);
        this.setBlack(3,4);
        this.setBlack(4,3);
        this.setPossibles(1, 2);
        this.setScore(2,2);
    }

    /**
     * get The cells.
     * @return cells.
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * get The score of black player.
     * @return bScore.
     */
    public int getBScore() {
        return bScore;
    }

    /**
     * get The score of white player.
     * @return wScore.
     */
    public int getWScore() {
        return wScore;
    }

    /**
     * @param bScore set the score of black player.
     * @param wScore set the score of white player.
     */
    public void setScore(int bScore, int wScore) {
        this.bScore = bScore;
        this.wScore = wScore;
    }

    /**
     * set Cell black color with given position.
     * @param i x position.
     * @param j y position.
     */
    public void setBlack(int i , int j) {
        cells[i][j].setBlack();
    }

    /**
     * set Cell white color with given position.
     * @param i x position.
     * @param j y position.
     */
    public void setWhite(int i , int j) {
        cells[i][j].setWhite();
    }

    /**
     * Determine validity of given position.
     * @param i x position.
     * @param j y position.
     * @return true if the given position is valid, false otherwise.
     */
    public boolean isValid(int i, int j){
        return cells[i][j].getPossible();
    }

    /**
     * Do a movement with the given position and current, enemy on the board.
     * @param row x position.
     * @param column y position.
     * @param current The colorId of current player.
     * @param enemy The colorId of enemy player.
     */
    public void doMovement(int row, int column, int current, int enemy) {
        int i = row, j = column, count = 0;
        cells[i][j].setByColor(current);
        
        //upper-right direction
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][j + 1].getColorId() == enemy) {
                    count++;
                    if ( (j + 2) < 8 && (i - 2) >= 0 && cells[i - 2][j + 2].getColorId() == current) {
                        j = column;
                        for (i = row; i > row - count; i--) {
                            cells[i - 1][j + 1].setByColor(current);
                            j++;
                        }
                        break;
                    }
                } else
                    break;
                j++;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        //lower-right direction
        j = column;
        for (i = row; i < 8; i++) {
            try {
                if (cells[i + 1][j + 1].getColorId() == enemy) {
                    count++;
                    if ( (j + 2) < 8 && (i + 2) < 8 && cells[i + 2][j + 2].getColorId() == current) {
                        j = column;
                        for (i = row; i < row + count; i++) {
                            cells[i + 1][j + 1].setByColor(current);
                            j++;
                        }
                        break;
                    }
                } else
                    break;
                j++;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        //lower-left direction
        j = column;
        for (i = row; i < 8; i++) {
            try {
                if (cells[i + 1][j - 1].getColorId() == enemy) {
                    count++;
                    if ((j - 2) >= 0 && (i + 2) < 8 && cells[i + 2][j - 2].getColorId() == current) {
                        j = column;
                        for (i = row; i < row + count; i++) {
                            cells[i + 1][j - 1].setByColor(current);
                            j--;
                        }
                        break;
                    }
                } else
                    break;
                j--;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        j = column;
        //upper-left direction
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][j - 1].getColorId() == enemy) {
                    count++;
                    if ((j - 2) >= 0 && (i - 2) >= 0 && cells[i - 2][j - 2].getColorId() == current ) {
                        j = column;
                        for (i = row; i > row - count; i--) {
                            cells[i - 1][j - 1].setByColor(current);
                            j--;
                        }
                        break;
                    }
                } else
                    break;
                j--;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        //upwards
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][column].getColorId() == enemy) {
                    count++;
                    if ((i - 2) >= 0 && cells[i - 2][column].getColorId() == current) {
                        for (j = row; j > row - count; j--)
                            cells[j - 1][column].setByColor(current);
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        //downwards
        for (i = row; i < 8; i++) {
            try {
                if (cells[i + 1][column].getColorId() == enemy) {
                    count++;
                    if ( (i + 2) < 8 && cells[i + 2][column].getColorId() == current) {
                        for (j = row; j < row + count; j++)
                            cells[j + 1][column].setByColor(current);
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        //rightwards
        for (i = column; i < 8; i++) {
            try {
                if (cells[row][i + 1].getColorId() == enemy) {
                    count++;
                    if ( (i + 2) < 8 && cells[row][i + 2].getColorId() == current ) {
                        for (j = column; j < column + count; j++)
                            cells[row][j + 1].setByColor(current);
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        count = 0;
        //leftwards
        for (j = column; j > 0; j--) {
            try {
                if (cells[row][j - 1].getColorId() == enemy) {
                    count++;
                    if ( (j - 2) >= 0 && cells[row][j - 2].getColorId() == current) {
                        for (j = column; j > column - count; j--)
                            cells[row][j - 1].setByColor(current);
                        break;
                    }
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
    }

    /**
     * set the possibility of movement with the given current, enemy.
     * @param current_player The colorId of current player.
     * @param enemy The colorId of enemy player.
     */
    public void setPossibles(int current_player, int enemy) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j].setPossible(isPossible(i,j,current_player,enemy));
            }
        }
    }

    /**
     * Determine possibility of given position and current, enemy.
     * @param row x position.
     * @param column y position.
     * @param current_player The colorId of current player.
     * @param enemy The colorId of enemy player.
     */
    public boolean isPossible( int row, int column, int current_player, int enemy) {
        int i = row, j = column, count = 0;
        if(cells[i][j].getColorId() != 0)
            return false;
        //upper-right direction
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][j + 1].getColorId() == enemy) {
                    if ((j + 2) < 8 && (i - 2) >= 0 && cells[i - 2][j + 2].getColorId() == current_player)
                        count++;
                } else
                    break;
                j++;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        //lower-right direction
        j = column;
        for (i = row; i < 8; i++) {
            try {
                if (cells[i + 1][j + 1].getColorId() == enemy) {
                    if ((j + 2) < 8 && (i + 2) < 8 && cells[i + 2][j + 2].getColorId() == current_player)
                        count++;
                } else
                    break;
                j++;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        //lower-left direction
        j = column;
        for (i = row; i < 8; i++) {
            try {
                if (cells[i + 1][j - 1].getColorId() == enemy) {
                    if (((j - 2) >= 0) && (i + 2) < 8 && cells[i + 2][j - 2].getColorId() == current_player)
                        count++;
                } else
                    break;
                j--;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        //upper-left direction
        j = column;
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][j - 1].getColorId() == enemy) {
                    if ((j - 2) >= 0 && (i - 2) >= 0 && cells[i - 2][j - 2].getColorId() == current_player)
                        count++;
                } else
                    break;
                j--;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        //upwards
        for (i = row; i > 0; i--) {
            try {
                if (cells[i - 1][column].getColorId() == enemy) {
                    if ((i - 2) >= 0 && cells[i - 2][column].getColorId() == current_player)
                        count++;
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        //rightwards
        for (i = column; i < 8; i++) {
            try {
                if (cells[row][i + 1].getColorId() == enemy) {
                    if ((i + 2) < 8 && cells[row][i + 2].getColorId() == current_player)
                        count++;
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }
        //downwards
        for (i = row; i < 8; i++) {
            try {
                if (cells[i + 1][column].getColorId() == enemy) {
                    if ((i + 2) < 8 && cells[i + 2][column].getColorId() == current_player)
                        count++;
                } else
                    break;
            } catch (IndexOutOfBoundsException ignored) {}
        }
        //leftwards
        for (j = column; j > 0; j--) {
            try {
                if (cells[row][j - 1].getColorId() == enemy) {
                    if ((j - 2) >= 0 && cells[row][j - 2].getColorId() == current_player)
                        count++;
                } else
                    break;
            }catch (IndexOutOfBoundsException ignored) {}
        }

        // possible
        return count != 0; // impossible
    }

    /**
     * Calculate score of players.
     */
    public void calculateScore() {
        int bScore = 0;
        int wScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cells[i][j].getColorId() == 1) {
                    bScore++;
                }
                if (cells[i][j].getColorId() == 2) {
                    wScore++;
                }
            }
        }
        setScore(bScore,wScore);
    }

    /**
     * Show the board.
     */
    public void showBoard() {
        System.out.println("      -------------------------------------------------");
        System.out.println("      | SCORE BOARD: " + "  Black ⚪ | " + bScore + " - " + wScore + " | ⚫ White   |");
        System.out.println("      -------------------------------------------------");
        System.out.println("      |  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |");
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            System.out.print( "  " + (i + 1) + "   |");
            for (int j = 0; j < 8; j++) {
                System.out.print("  " + cells[i][j] + "  |");
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------");
        }
    }
}
