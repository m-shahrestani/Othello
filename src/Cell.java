/**
 * A class to hold details of a cell.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Cell {
    //color of Cell
    private char color;
    //colorID of Cell
    private int colorId;
    //possibility of Cell for move
    private boolean possible;

    /**
     * Create a new Cell.
     * Initialize color and colorId and possibility of Cell.
     *
     */
    public Cell() {
        color = ' ';
        colorId = 0;
        possible = false;
    }

    /**
     * get The colorId of the Cell.
     * @return colorId.
     */
    public int getColorId() {
        return colorId;
    }

    /**
     * get The possibility of Cell.
     * @return possible.
     */
    public boolean getPossible() {
        return possible;
    }

    /**
     * set Cell white color.
     */
    public void setWhite(){
        color = '⚫';
        colorId = 2;
    }

    /**
     * set Cell black color.
     */
    public void setBlack(){
        color = '⚪';
        colorId = 1;
    }

    /**
     * @param possible set possibility of Cell.
     */
    public void setPossible(boolean possible){
        this.possible = possible;
    }

    /**
     * @param colorId set color of Cell with given colorId.
     */
    public void setByColor(int colorId){
        if(colorId == 1)
            this.setBlack();
        else
            this.setWhite();
    }

    /**
     * get a String for show possibility of Cell on the board.
     * @return a String.
     */
    @Override
    public String toString() {
        return Character.toString(colorId == 0 && possible ? '#' : color);
    }
}
