public class TTTResultDeterminer {
    private final int ROWS = 3;
    private final int COLS = 3;


    // INITIATING VARIABLES
    boolean xFlag = false;
    boolean oFlag = false;

    public void determineAndDisplayWhetherPlayerWasXorO() {
        if(TTTGameBoard.move == 1 | TTTGameBoard.move == 3 | TTTGameBoard.move == 5 | TTTGameBoard.move == 7 | TTTGameBoard.move == 9)
        {
            TTTGameBoard.boardButtons[TTTGameBoard.rowMoveWasIn][TTTGameBoard.colMoveWasIn].setText("X");
            TTTGameBoard.boardButtons[TTTGameBoard.rowMoveWasIn][TTTGameBoard.colMoveWasIn].setEnabled(false);
            TTTGameBoard.player = "X";

        }
        if(TTTGameBoard.move == 2 | TTTGameBoard.move == 4 | TTTGameBoard.move == 6 | TTTGameBoard.move == 8) {
            TTTGameBoard.boardButtons[TTTGameBoard.rowMoveWasIn][TTTGameBoard.colMoveWasIn].setText("O");
            TTTGameBoard.boardButtons[TTTGameBoard.rowMoveWasIn][TTTGameBoard.colMoveWasIn].setEnabled(false);
            TTTGameBoard.player = "O";
        }
    }

    public boolean isWin() {
        return isColWin() || isRowWin() || isDiagonalWin();
    }
    public boolean isRowWin() {
        for(int row = 0; row < ROWS; row++) {
            if(TTTGameBoard.boardButtons[row][0].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[row][1].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[row][2].getText().equals(TTTGameBoard.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isColWin() {
        for(int col = 0; col < ROWS; col++) {
            if(TTTGameBoard.boardButtons[0][col].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[1][col].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[2][col].getText().equals(TTTGameBoard.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDiagonalWin() {
        for(int row = 0; row < ROWS; row++) {
            if(TTTGameBoard.boardButtons[0][0].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[1][1].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[2][2].getText().equals(TTTGameBoard.player)) {
                return true;
            }
            if(TTTGameBoard.boardButtons[2][0].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[1][1].getText().equals(TTTGameBoard.player) && TTTGameBoard.boardButtons[0][2].getText().equals(TTTGameBoard.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTie()
    {
// Check all 8 win vectors for an X and O so
// no win is possible
// Check for row ties
        for(int row=0; row < ROWS; row++)
        {
            if(TTTGameBoard.boardButtons[row][0].getText().equals("X") ||
                    TTTGameBoard.boardButtons[row][1].getText().equals("X") ||
                    TTTGameBoard.boardButtons[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(TTTGameBoard.boardButtons[row][0].getText().equals("O") ||
                    TTTGameBoard.boardButtons[row][1].getText().equals("O") ||
                    TTTGameBoard.boardButtons[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }
            xFlag = oFlag = false;
        }
// Now scan the columns
        for(int col=0; col < COLS; col++)
        {
            if(TTTGameBoard.boardButtons[0][col].getText().equals("X") ||
                    TTTGameBoard.boardButtons[1][col].getText().equals("X") ||
                    TTTGameBoard.boardButtons[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(TTTGameBoard.boardButtons[0][col].getText().equals("O") ||
                    TTTGameBoard.boardButtons[1][col].getText().equals("O") ||
                    TTTGameBoard.boardButtons[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
// Now check for the diagonals
        xFlag = oFlag = false;
        if(TTTGameBoard.boardButtons[0][0].getText().equals("X") ||
                TTTGameBoard.boardButtons[1][1].getText().equals("X") ||
                TTTGameBoard.boardButtons[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(TTTGameBoard.boardButtons[0][0].getText().equals("O") ||
                TTTGameBoard.boardButtons[1][1].getText().equals("O") ||
                TTTGameBoard.boardButtons[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;
        if(TTTGameBoard.boardButtons[0][2].getText().equals("X") ||
                TTTGameBoard.boardButtons[1][1].getText().equals("X") ||
                TTTGameBoard.boardButtons[2][0].getText().equals("X") )
        {
            xFlag = true;
        }
        if(TTTGameBoard.boardButtons[0][2].getText().equals("O") ||
                TTTGameBoard.boardButtons[1][1].getText().equals("O") ||
                TTTGameBoard.boardButtons[2][0].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diagonal win
        }
// Checked every vector so I know I have a tie
        return true;
    }
}
