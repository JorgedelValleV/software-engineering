package tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import tetris.views.View;

/**
 * Created by saacsos on 1/12/2559.
 * http://zetcode.com/tutorials/javagamestutorial/tetris/
 */
public class TetrisFrame extends JFrame {
    private JLabel statusBar;
    private View board;

    public TetrisFrame() {
        statusBar = new JLabel(" 0");
        board = new View(this);
    }

    public void init() {
        setLayout(new BorderLayout());
        add(statusBar, BorderLayout.SOUTH);
        add(board, BorderLayout.CENTER);
        board.start();
        setSize(400, 800);
        setPreferredSize(new Dimension(400, 800));
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }

    public JLabel getStatusBar() {
        return statusBar;
    }
    public static void main(String[] args) {
        TetrisFrame game = new TetrisFrame();
        game.setLocationRelativeTo(null);
        game.init();
    }
}
