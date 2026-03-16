import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TTTFrame extends JFrame {
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

        initializeButtonsAndMakeActionable();
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
    public void createQuitBtnPnl() {
        quitBtnPnl = new JPanel();

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Copperplate", Font.PLAIN, 45));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        quitBtnPnl.add(quitBtn);
    }

    public void initializeButtonsAndMakeActionable() {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));
        int row;
        int col;
        for (row = 0; row < TTTStaticVariables.ROWS; row++) {
            for (col = 0; col < TTTStaticVariables.boardButtons[row].length; col++) {
                TTTStaticVariables.boardButtons[row][col] = new TTTTileButton(row, col);
                TTTStaticVariables.boardButtons[row][col].setText(" ");
                TTTStaticVariables.boardButtons[row][col].setSize(2, 3);
                TTTStaticVariables.boardButtons[row][col].addActionListener((ActionEvent actionEvent) -> {
                    TTTStaticVariables.move++;
                    int rowIndex;
                    int colIndex;
                    for (rowIndex = 0; rowIndex < TTTStaticVariables.boardButtons.length; rowIndex++) {
                        for (colIndex = 0; colIndex < TTTStaticVariables.boardButtons.length; colIndex++) {
                            if(actionEvent.getSource() == TTTStaticVariables.boardButtons[rowIndex][colIndex]) {
                                TTTStaticVariables.rowMoveWasIn = rowIndex;
                                TTTStaticVariables.colMoveWasIn = colIndex;
                                resultDeterminer.determineAndDisplayWhetherPlayerWasXorO();
                                boolean isWin = resultDeterminer.isWin();
                                boolean isTie = resultDeterminer.isTie();
                                if (TTTStaticVariables.move >= 5 && isWin) {
                                    TTTResultDeterminer.whatHappensIfSomeoneWins();
                                }
                                if (TTTStaticVariables.move >= 7 && isTie) {
                                    TTTResultDeterminer.whatHappensIfThereIsATie();
                                }
                            }
                        }
                    }
                });
                boardPnl.add(TTTStaticVariables.boardButtons[row][col]);
            }
        }
    }
}