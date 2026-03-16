import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TTTFrame extends JFrame {
    private final int ROWS = 3;
    private final int COLS = 3;
    TTTResultDeterminer resultDeterminer = new TTTResultDeterminer();

    JPanel mainPnl;
    JPanel boardPnl;
    JPanel quitBtnPnl;

    JButton quitBtn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new TTTFrame();
            }
        });
    }

    public TTTFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(2, 1));

        displayBoard();
        mainPnl.add(boardPnl);

        createQuitBtnPnl();
        mainPnl.add(quitBtnPnl);

        add(mainPnl);
        {
            // CENTER FRAME IN SCREEN...CODE ADAPTED FROM CAY HORSTMANN
            Toolkit kit = Toolkit.getDefaultToolkit();                                  // getting toolkit from system and naming it "kit"
            Dimension screenSize = kit.getScreenSize();                                 // getting screen size from toolkit
            int screenHeight = screenSize.height;                                       // getting height from screen size and naming it "screenHeight"
            int screenWidth = screenSize.width;                                         // getting width from screen size and naming it "screenWidth"

            setSize(screenWidth / 11, screenHeight / 4);
            double twoPointTwo = 2.2;
            setLocation((int) (screenWidth / twoPointTwo), screenHeight / 3);        // put left side of frame a little more than half the screenWidth away from the left side of the user's screen
                                                                                        // and put top of frame a third if the way down from the top of the user's screen


            setTitle("Tic Tac Toe");                                                    // name JFrame Tic Tac Toe
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             // make program end when user closes JFramee
            setVisible(true);                                                           // make JFrame visible
        }
    }

    // METHODS...
    public void clearBoard() {
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {
                TTTGameBoard.boardButtons[row][col].setText(" ");
            }
        }
    }

    public void createQuitBtnPnl() {
        quitBtnPnl = new JPanel();

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Copperplate", Font.PLAIN, 45));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        quitBtnPnl.add(quitBtn);
    }

    public void displayBoard() {
        initializeButtonsAndMakeActionable();
    }

    public void initializeButtonsAndMakeActionable() {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));
        int row;
        int col;
        for (row = 0; row < ROWS; row++) {
            for (col = 0; col < TTTGameBoard.boardButtons[row].length; col++) {
                TTTGameBoard.boardButtons[row][col] = new TTTTileButton(row, col);
                TTTGameBoard.boardButtons[row][col].setText(" ");
                TTTGameBoard.boardButtons[row][col].setSize(2, 3);
                TTTGameBoard.boardButtons[row][col].addActionListener((ActionEvent actionEvent) -> {
                    TTTGameBoard.move++;
                    int rowIndex;
                    int colIndex;
                    for (rowIndex = 0; rowIndex < TTTGameBoard.boardButtons.length; rowIndex++) {
                        for (colIndex = 0; colIndex < TTTGameBoard.boardButtons.length; colIndex++) {
                            if(actionEvent.getSource() == TTTGameBoard.boardButtons[rowIndex][colIndex]) {
                                TTTGameBoard.rowMoveWasIn = rowIndex;
                                TTTGameBoard.colMoveWasIn = colIndex;
                                resultDeterminer.determineAndDisplayWhetherPlayerWasXorO();
                                boolean isWin = resultDeterminer.isWin();
                                boolean isTie = resultDeterminer.isTie();
                                if (TTTGameBoard.move >= 5 && isWin) {
                                    JOptionPane.showMessageDialog(null,TTTGameBoard.player + " wins!");
                                    Object[] yesOrNoOptions = {"Yes", "No"};
                                    int answerIndex = JOptionPane.showOptionDialog(null, "Would you like\nto play again?", "Play Again?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesOrNoOptions, yesOrNoOptions[1]);
                                    if (answerIndex == 0) {
                                        for (int i = 0; i < ROWS; i++) {
                                            for (int j = 0; j < TTTGameBoard.boardButtons[i].length; j++) {
                                                TTTGameBoard.boardButtons[i][j].setText(" ");
                                                TTTGameBoard.boardButtons[i][j].setEnabled(true);
                                                TTTGameBoard.move = 0;
                                            }
                                        }
                                    }
                                    if (answerIndex == 1) {
                                        System.exit(0);
                                    }
                                }
                                if (TTTGameBoard.move >= 7 && isTie) {
                                    JOptionPane.showMessageDialog(null,"It's a tie!");
                                    Object[] yesOrNoOptions = {"Yes", "No"};
                                    int optionsInt = JOptionPane.showOptionDialog(null, "Would you like\nto play again?", "Play Again?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesOrNoOptions, yesOrNoOptions[1]);
                                    if (optionsInt == 0) {
                                        for (int i = 0; i < ROWS; i++) {
                                            for (int j = 0; j < TTTGameBoard.boardButtons[i].length; j++) {
                                                TTTGameBoard.boardButtons[i][j].setText(" ");
                                                TTTGameBoard.boardButtons[i][j].setEnabled(true);
                                                TTTGameBoard.move = 0;
                                            }
                                        }
                                    }
                                    if (optionsInt == 1) {
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                });
                boardPnl.add(TTTGameBoard.boardButtons[row][col]);
            }
        }
    }
}