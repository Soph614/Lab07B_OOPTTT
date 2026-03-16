import javax.swing.*;

public class TTTResultDeterminer {


    // INITIATING VARIABLES
    boolean xFlag = false;
    boolean oFlag = false;

    public void determineAndDisplayWhetherPlayerWasXorO() {
        if(TTTGameBoardVariables.move == 1 | TTTGameBoardVariables.move == 3 | TTTGameBoardVariables.move == 5 | TTTGameBoardVariables.move == 7 | TTTGameBoardVariables.move == 9)
        {
            TTTGameBoardVariables.boardButtons[TTTGameBoardVariables.rowMoveWasIn][TTTGameBoardVariables.colMoveWasIn].setText("X");
            TTTGameBoardVariables.boardButtons[TTTGameBoardVariables.rowMoveWasIn][TTTGameBoardVariables.colMoveWasIn].setEnabled(false);
            TTTGameBoardVariables.player = "X";

        }
        if(TTTGameBoardVariables.move == 2 | TTTGameBoardVariables.move == 4 | TTTGameBoardVariables.move == 6 | TTTGameBoardVariables.move == 8) {
            TTTGameBoardVariables.boardButtons[TTTGameBoardVariables.rowMoveWasIn][TTTGameBoardVariables.colMoveWasIn].setText("O");
            TTTGameBoardVariables.boardButtons[TTTGameBoardVariables.rowMoveWasIn][TTTGameBoardVariables.colMoveWasIn].setEnabled(false);
            TTTGameBoardVariables.player = "O";
        }
    }

    public boolean isWin() {
        return isColWin() || isRowWin() || isDiagonalWin();
    }
    public boolean isRowWin() {
        for(int row = 0; row < TTTGameBoardVariables.ROWS; row++) {
            if(TTTGameBoardVariables.boardButtons[row][0].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[row][1].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[row][2].getText().equals(TTTGameBoardVariables.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isColWin() {
        for(int col = 0; col < TTTGameBoardVariables.ROWS; col++) {
            if(TTTGameBoardVariables.boardButtons[0][col].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[1][col].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[2][col].getText().equals(TTTGameBoardVariables.player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDiagonalWin() {
        for(int row = 0; row < TTTGameBoardVariables.ROWS; row++) {
            if(TTTGameBoardVariables.boardButtons[0][0].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[1][1].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[2][2].getText().equals(TTTGameBoardVariables.player)) {
                return true;
            }
            if(TTTGameBoardVariables.boardButtons[2][0].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[1][1].getText().equals(TTTGameBoardVariables.player) && TTTGameBoardVariables.boardButtons[0][2].getText().equals(TTTGameBoardVariables.player)) {
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
        for(int row = 0; row < TTTGameBoardVariables.ROWS; row++)
        {
            if(TTTGameBoardVariables.boardButtons[row][0].getText().equals("X") ||
                    TTTGameBoardVariables.boardButtons[row][1].getText().equals("X") ||
                    TTTGameBoardVariables.boardButtons[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(TTTGameBoardVariables.boardButtons[row][0].getText().equals("O") ||
                    TTTGameBoardVariables.boardButtons[row][1].getText().equals("O") ||
                    TTTGameBoardVariables.boardButtons[row][2].getText().equals("O"))
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
        for(int col = 0; col < TTTGameBoardVariables.COLS; col++)
        {
            if(TTTGameBoardVariables.boardButtons[0][col].getText().equals("X") ||
                    TTTGameBoardVariables.boardButtons[1][col].getText().equals("X") ||
                    TTTGameBoardVariables.boardButtons[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(TTTGameBoardVariables.boardButtons[0][col].getText().equals("O") ||
                    TTTGameBoardVariables.boardButtons[1][col].getText().equals("O") ||
                    TTTGameBoardVariables.boardButtons[2][col].getText().equals("O"))
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
        if(TTTGameBoardVariables.boardButtons[0][0].getText().equals("X") ||
                TTTGameBoardVariables.boardButtons[1][1].getText().equals("X") ||
                TTTGameBoardVariables.boardButtons[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(TTTGameBoardVariables.boardButtons[0][0].getText().equals("O") ||
                TTTGameBoardVariables.boardButtons[1][1].getText().equals("O") ||
                TTTGameBoardVariables.boardButtons[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;
        if(TTTGameBoardVariables.boardButtons[0][2].getText().equals("X") ||
                TTTGameBoardVariables.boardButtons[1][1].getText().equals("X") ||
                TTTGameBoardVariables.boardButtons[2][0].getText().equals("X") )
        {
            xFlag = true;
        }
        if(TTTGameBoardVariables.boardButtons[0][2].getText().equals("O") ||
                TTTGameBoardVariables.boardButtons[1][1].getText().equals("O") ||
                TTTGameBoardVariables.boardButtons[2][0].getText().equals("O") )
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
        JOptionPane.showMessageDialog(null, TTTGameBoardVariables.player + " wins!");
        Object[] yesOrNoOptions = {"Yes", "No"};
        int answerIndex = JOptionPane.showOptionDialog(null, "Would you like\nto play again?", "Play Again?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesOrNoOptions, yesOrNoOptions[1]);
        if (answerIndex == 0) {
            for (int i = 0; i < TTTGameBoardVariables.ROWS; i++) {
                for (int j = 0; j < TTTGameBoardVariables.boardButtons[i].length; j++) {
                    TTTGameBoardVariables.boardButtons[i][j].setText(" ");
                    TTTGameBoardVariables.boardButtons[i][j].setEnabled(true);
                    TTTGameBoardVariables.move = 0;
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
            for (int i = 0; i < TTTGameBoardVariables.ROWS; i++) {
                for (int j = 0; j < TTTGameBoardVariables.boardButtons[i].length; j++) {
                    TTTGameBoardVariables.boardButtons[i][j].setText(" ");
                    TTTGameBoardVariables.boardButtons[i][j].setEnabled(true);
                    TTTGameBoardVariables.move = 0;
                }
            }
        }

        if (optionsInt == 1) {
            System.exit(0);
        }
    }
}
