import javax.swing.*;

public class TTTResultDeterminer {
    // INITIATING VARIABLES
    boolean xFlag = false;
    boolean oFlag = false;

    public void determineAndDisplayWhetherPlayerWasXorO() {
        if(TTTStaticVariables.move == 1 | TTTStaticVariables.move == 3 | TTTStaticVariables.move == 5 | TTTStaticVariables.move == 7 | TTTStaticVariables.move == 9)
        {
            TTTStaticVariables.boardButtons[TTTStaticVariables.rowMoveWasIn][TTTStaticVariables.colMoveWasIn].setText("X");
            TTTStaticVariables.boardButtons[TTTStaticVariables.rowMoveWasIn][TTTStaticVariables.colMoveWasIn].setEnabled(false);
            TTTStaticVariables.player = "X";

        }
        if(TTTStaticVariables.move == 2 | TTTStaticVariables.move == 4 | TTTStaticVariables.move == 6 | TTTStaticVariables.move == 8) {
            TTTStaticVariables.boardButtons[TTTStaticVariables.rowMoveWasIn][TTTStaticVariables.colMoveWasIn].setText("O");
            TTTStaticVariables.boardButtons[TTTStaticVariables.rowMoveWasIn][TTTStaticVariables.colMoveWasIn].setEnabled(false);
            TTTStaticVariables.player = "O";
        }
    }

    public boolean isWin() {
        return isColWin() || isRowWin() || isDiagonalWin();
    }
    public boolean isRowWin() {
        for(int row = 0; row < TTTStaticVariables.ROWS; row++) {
            if(TTTStaticVariables.boardButtons[row][0].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[row][1].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[row][2].getText().equals(TTTStaticVariables.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isColWin() {
        for(int col = 0; col < TTTStaticVariables.ROWS; col++) {
            if(TTTStaticVariables.boardButtons[0][col].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[1][col].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[2][col].getText().equals(TTTStaticVariables.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDiagonalWin() {
        for(int row = 0; row < TTTStaticVariables.ROWS; row++) {
            if(TTTStaticVariables.boardButtons[0][0].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[1][1].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[2][2].getText().equals(TTTStaticVariables.player)) {
                return true;
            }
            if(TTTStaticVariables.boardButtons[2][0].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[1][1].getText().equals(TTTStaticVariables.player) && TTTStaticVariables.boardButtons[0][2].getText().equals(TTTStaticVariables.player)) {
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
        for(int row = 0; row < TTTStaticVariables.ROWS; row++)
        {
            if(TTTStaticVariables.boardButtons[row][0].getText().equals("X") ||
                    TTTStaticVariables.boardButtons[row][1].getText().equals("X") ||
                    TTTStaticVariables.boardButtons[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(TTTStaticVariables.boardButtons[row][0].getText().equals("O") ||
                    TTTStaticVariables.boardButtons[row][1].getText().equals("O") ||
                    TTTStaticVariables.boardButtons[row][2].getText().equals("O"))
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
        for(int col = 0; col < TTTStaticVariables.COLS; col++)
        {
            if(TTTStaticVariables.boardButtons[0][col].getText().equals("X") ||
                    TTTStaticVariables.boardButtons[1][col].getText().equals("X") ||
                    TTTStaticVariables.boardButtons[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(TTTStaticVariables.boardButtons[0][col].getText().equals("O") ||
                    TTTStaticVariables.boardButtons[1][col].getText().equals("O") ||
                    TTTStaticVariables.boardButtons[2][col].getText().equals("O"))
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
        if(TTTStaticVariables.boardButtons[0][0].getText().equals("X") ||
                TTTStaticVariables.boardButtons[1][1].getText().equals("X") ||
                TTTStaticVariables.boardButtons[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(TTTStaticVariables.boardButtons[0][0].getText().equals("O") ||
                TTTStaticVariables.boardButtons[1][1].getText().equals("O") ||
                TTTStaticVariables.boardButtons[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;
        if(TTTStaticVariables.boardButtons[0][2].getText().equals("X") ||
                TTTStaticVariables.boardButtons[1][1].getText().equals("X") ||
                TTTStaticVariables.boardButtons[2][0].getText().equals("X") )
        {
            xFlag = true;
        }
        if(TTTStaticVariables.boardButtons[0][2].getText().equals("O") ||
                TTTStaticVariables.boardButtons[1][1].getText().equals("O") ||
                TTTStaticVariables.boardButtons[2][0].getText().equals("O") )
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

    public static void whatHappensIfSomeoneWins() {
        JOptionPane.showMessageDialog(null, TTTStaticVariables.player + " wins!");
        Object[] yesOrNoOptions = {"Yes", "No"};
        int answerIndex = JOptionPane.showOptionDialog(null, "Would you like\nto play again?", "Play Again?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesOrNoOptions, yesOrNoOptions[1]);
        if (answerIndex == 0) {
            for (int i = 0; i < TTTStaticVariables.ROWS; i++) {
                for (int j = 0; j < TTTStaticVariables.boardButtons[i].length; j++) {
                    TTTStaticVariables.boardButtons[i][j].setText(" ");
                    TTTStaticVariables.boardButtons[i][j].setEnabled(true);
                    TTTStaticVariables.move = 0;
                }
            }
        }
        if (answerIndex == 1) {
            System.exit(0);
        }
    }

    public static void whatHappensIfThereIsATie() {
        JOptionPane.showMessageDialog(null,"It's a tie!");
        Object[] yesOrNoOptions = {"Yes", "No"};
        int optionsInt = JOptionPane.showOptionDialog(null, "Would you like\nto play again?", "Play Again?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesOrNoOptions, yesOrNoOptions[1]);
        if (optionsInt == 0) {
            for (int i = 0; i < TTTStaticVariables.ROWS; i++) {
                for (int j = 0; j < TTTStaticVariables.boardButtons[i].length; j++) {
                    TTTStaticVariables.boardButtons[i][j].setText(" ");
                    TTTStaticVariables.boardButtons[i][j].setEnabled(true);
                    TTTStaticVariables.move = 0;
                }
            }
        }

        if (optionsInt == 1) {
            System.exit(0);
        }
    }
}
