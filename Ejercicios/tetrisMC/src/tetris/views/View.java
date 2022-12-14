package tetris.views;

import tetris.TetrisFrame;
import tetris.controllers.BoardController;
import tetris.models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by saacsos on 1/12/2559.
 * http://zetcode.com/tutorials/javagamestutorial/tetris/
 */
public class View extends JPanel implements ActionListener {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    private JLabel statusBar;
    private Model model;

    private BoardController controller;


    public View(TetrisFrame parent) {
        setFocusable(true);
        model = new Model(BOARD_WIDTH,BOARD_HEIGHT,this);
        controller = new BoardController(BOARD_WIDTH, BOARD_HEIGHT, this,model);
        statusBar = parent.getStatusBar();
        addKeyListener(new TAdapter());
    }

    public void start() {
        controller.start();
    }

    @Override
    
    public void actionPerformed(ActionEvent e) {
        model.gameAction();
    }

    public void paint(Graphics g) {
        super.paint(g);
        controller.actualizarVistaModelo(g, getSize().getWidth(), getSize().getHeight());
    }

    private int squareWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int squareHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }

    public void drawSquare(Graphics g, int x, int y, tetris.models.Shape.Tetrominoes shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    public void setStatusText(String text) {
        statusBar.setText(text);
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
      
            int keycode = e.getKeyCode();
            if (keycode == 'p' || keycode == 'P') {
                controller.pause();
                return;
            }

            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    controller.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    controller.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    controller.rotateRight();
                    break;
                case KeyEvent.VK_UP:
                    controller.rotateLeft();
                    break;
                case KeyEvent.VK_SPACE:
                    controller.dropDown();
                    break;
                case 'd':
                case 'D':
                    controller.oneLineDown();
                    break;
            }

        }
    }
}
