import javax.swing.*;

public class TicTacToeGameRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new TicTacToeFrame();
            }
        });
    }
}
