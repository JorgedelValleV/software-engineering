package tetris.models;

import java.awt.Graphics;

import javax.swing.Timer;

import tetris.views.View;

public class Model {
	private View view;
    private int boardWidth;
    private int boardHeight;
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    private boolean isPaused = false;

    private int numLinesRemoved = 0;
    private int currentX = 0;
    private int currentY = 0;
    private Timer timer;

    private Shape currentPiece;
    private Shape.Tetrominoes[] board;

	public Model(int boardWidth, int boardHeight, View tetrisBoard) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.view = tetrisBoard;
        currentPiece = new Shape();
        timer = new Timer(400, tetrisBoard);
        timer.start();
        board = new Shape.Tetrominoes[boardWidth * boardHeight];

        clearBoard();
	}
	public void start() {
        if (isPaused) return;
        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();
        newPiece();
        timer.start();

    }
    public void actualizarVista(Graphics g, double width, double height) {
        int squareWidth = (int) width / boardWidth;
        int squareHeight = (int) height / boardHeight;
        int boardTop = (int) height - boardHeight * squareHeight;


        for (int i = 0; i < boardHeight; ++i) {
            for (int j = 0; j < boardWidth; ++j) {
                tetris.models.Shape.Tetrominoes shape = shapeAt(j, boardHeight - i - 1);
                if (shape != tetris.models.Shape.Tetrominoes.NoShape)
                    view.drawSquare(g, j * squareWidth,
                            boardTop + i * squareHeight, shape);
            }
        }

        if (currentPiece.getPieceShape() != tetris.models.Shape.Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = currentX + currentPiece.x(i);
                int y = currentY - currentPiece.y(i);
                view.drawSquare(g, x * squareWidth,
                        boardTop + (boardHeight - y - 1) * squareHeight,
                        currentPiece.getPieceShape());
            }
        }
    }
	  public boolean isCurrentPieceNoShaped() {
	        return currentPiece.getPieceShape() == Shape.Tetrominoes.NoShape;
	    }


	    public void gameAction() {
	        if (isFallingFinished) {
	            isFallingFinished = false;
	            newPiece();
	        } else {
	            oneLineDown();
	        }
	    }
	 
	private void clearBoard()
    {
        for (int i = 0; i < boardHeight * boardWidth; ++i)
            board[i] = Shape.Tetrominoes.NoShape;
    }

    public void dropDown()
    {
        int newY = currentY;
        while (newY > 0) {
            if (!tryMove(currentPiece, currentX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private Shape.Tetrominoes shapeAt(int x, int y) {
        return board[(y * boardWidth) + x];
    }

    private void newPiece()
    {
        currentPiece.setRandomShape();
        currentX = boardWidth / 2 + 1;
        currentY = boardHeight - 1 + currentPiece.minY();

        if (!tryMove(currentPiece, currentX, currentY)) {
            currentPiece.setPieceShape(Shape.Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            view.setStatusText("game over");
        }
    }
    public void oneLineDown()
    {
        if (!tryMove(currentPiece, currentX, currentY - 1))
            pieceDropped();
    }

    public boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight)
                return false;
            if (shapeAt(x, y) != Shape.Tetrominoes.NoShape)
                return false;
        }
        currentPiece = newPiece;
        currentX = newX;
        currentY = newY;
        view.repaint();
        return true;
    }

    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = currentX + currentPiece.x(i);
            int y = currentY - currentPiece.y(i);
            board[(y * boardWidth) + x] = currentPiece.getPieceShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }

    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = boardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < boardWidth; ++j) {
                if (shapeAt(j, i) == Shape.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < boardHeight - 1; ++k) {
                    for (int j = 0; j < boardWidth; ++j)
                        board[(k * boardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            view.setStatusText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            currentPiece.setPieceShape(Shape.Tetrominoes.NoShape);
            view.repaint();
        }
    }
	public Shape getcurrentPiece() {
		return this.currentPiece;
	}
	public boolean isStarted() {
		return isStarted;
	}
	public boolean isPaused() {
		return isPaused;
	}
	public void setisPaused(boolean b) {
		this.isPaused = b;
		
	}

	public void moveLeft() {
		tryMove(getcurrentPiece(), currentX - 1, currentY);
	}

	public void moveRight() {
		tryMove(getcurrentPiece(), currentX + 1, currentY);
	}

	public void rotateLeft() {
		tryMove(getcurrentPiece().rotateLeft(), currentX, currentY);
	}

	public void rotateRight() {
		tryMove(getcurrentPiece().rotateRight(), currentX, currentY);
	}
}
