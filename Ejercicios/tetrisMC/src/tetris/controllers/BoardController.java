package tetris.controllers;

import tetris.models.Model;
import tetris.models.Shape;
import tetris.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by saacsos on 1/12/2559.
 * http://zetcode.com/tutorials/javagamestutorial/tetris/
 */
public class BoardController {
	private View view;

	private int numLinesRemoved = 0;
	private Timer timer;
	private Model model;

	public BoardController(int boardWidth, int boardHeight, View tetrisBoard, Model model) {
		this.view = tetrisBoard;
		timer = new Timer(400, tetrisBoard);
		this.model = model;
		timer.start();
	}

	public boolean isStarted() {
		return model.isStarted();
	}

	public boolean isPaused() {
		return model.isPaused();
	}

	public void start() {
		model.start();
	}

	public void pause() {
		if (!model.isStarted())
			return;

		model.setisPaused(!model.isPaused());
		if (model.isPaused()) {
			timer.stop();
			view.setStatusText("paused");
		} else {
			timer.start();
			view.setStatusText(String.valueOf(numLinesRemoved));
		}
		view.repaint();
	}

	public void actualizarVistaModelo(Graphics g, double width, double height) {
		model.actualizarVista(g, width, height);
	}

	public void moveLeft() {
		model.moveLeft();
	}

	public void moveRight() {
		model.moveRight();
	}

	public void rotateLeft() {
		model.rotateLeft();
	}

	public void rotateRight() {
		model.rotateRight();
	}

	public void dropDown() {
		model.dropDown();
		
	}

	public void oneLineDown() {
		model.oneLineDown();
		
	}

}
