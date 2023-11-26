package org.example.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import org.example.BlockBoxData;
import org.example.BlockBoxPanel;
import org.example.BlockGenerator;
import org.example.Counter;
import org.example.GameController;
import org.example.GameTimer;
import org.example.RandomBlockGenerator;
import org.example.Shape;
import org.example.Square;
import org.example.Tetrominoes;

public abstract class Board extends JPanel implements Square {

	private transient GameTimer gameTimer;
	private transient BlockBoxPanel blockBoxPanel;
	public final int BoardWidth = 10;
	public final int BoardHeight = 22;
	private transient BlockGenerator blockGenerator;
	private transient GameController controller;
	private boolean isFallingFinished = false;
	private boolean isStarted = false;
	private boolean isPaused = false;
	private boolean isHolding = false;
	private int numLinesRemoved = 0;
	private transient Shape curPiece;
	private transient Shape ghostPiece;
	private Tetrominoes hold = Tetrominoes.NoShape;
	private Tetrominoes[] board;

	public Board() {
		setFocusable(true);
		curPiece = new Shape();
		ghostPiece = new Shape();
		controller = new GameController(600, this);
		controller.start();
		blockBoxPanel = new BlockBoxPanel();
		board = new Tetrominoes[BoardWidth * BoardHeight];
		addKeyListener(new TAdapter());
		clearBoard();
		setBlockGenerator(new RandomBlockGenerator());
		setGameTimer(new Counter(this));
	}

	public JPanel getComponent(){
		int boardWidth = 250;
		int boardHeight = 500;
		int blockBoxPanelWidth = 100;
		int blockBoxPanelHeight = 500;

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(blockBoxPanel);
		panel.add(this);

		blockBoxPanel.setBounds(0, 0, blockBoxPanelWidth, blockBoxPanelHeight);
		this.setBounds(blockBoxPanelWidth,0,boardWidth, boardHeight);

		return panel;
	}

	public void setGameTimer(GameTimer timer){
		this.gameTimer = timer;
	}

	public GameTimer getGameTimer(){
		return gameTimer;
	}

	public void actionPerformed(ActionEvent e) {
		if (isFallingFinished) {
			isFallingFinished = false;
			newPiece();
		} else {
			oneLineDownCurPiece();
		}
	}
	@Override
	public int squareWidth() {
		return (int) getSize().getWidth() / BoardWidth;
	}
	@Override
	public int squareHeight() {
		return (int) getSize().getHeight() / BoardHeight;
	}

	public Tetrominoes shapeAt(int x, int y) {
		return board[(y * BoardWidth) + x];
	}

	public void setShapeAt(int x, int y, Tetrominoes tetrominoes){
		board[(y * BoardWidth) + x] = tetrominoes;
	}

	public void start() {
		if (isPaused)
			return;

		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		clearBoard();
		newPiece();
		controller.start();
	}

	public void pause() {
		if (!isStarted)
			return;

		isPaused = !isPaused;

		if (isPaused) {
			controller.stop();
		} else {
			controller.start();
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		drawBoard(g);
		drawPiece(g, curPiece);
		drawPiece(g, ghostPiece);
	}

	private void drawBoard(Graphics g){
		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();

		for (int i = 0; i < BoardHeight; ++i) {
			for (int j = 0; j < BoardWidth; ++j) {
				Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
				if (shape != Tetrominoes.NoShape)
					drawSquare(g, 0 + j * squareWidth(), boardTop + i * squareHeight(), shape);
			}
		}
	}

	private void drawPiece(Graphics g, Shape piece){
		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();
		if (curPiece.getShape() != Tetrominoes.NoShape) {
			for (int i = 0; i < 4; ++i) {
				int x = piece.getCurX() + piece.x(i);
				int y = piece.getCurY() - piece.y(i);
				drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(),
						piece.getShape());
			}
		}
	}

	private void dropDownCurPiece() {
		dropDownPiece(curPiece);
		droppedCurPiece();
	}

	private void dropDownPiece(Shape piece){
		int newY = piece.getCurY();
		while (newY > 0) {
			if (!tryMovePiece(piece, piece.getCurX(), newY - 1))
				break;
			--newY;
		}
	}

	private void oneLineDownCurPiece() {
		if (!tryMoveCurPiece(curPiece, curPiece.getCurX(), curPiece.getCurY() - 1))
			droppedCurPiece();
	}

	public void clearBoard() {
		for (int i = 0; i < BoardHeight * BoardWidth; ++i)
			board[i] = Tetrominoes.NoShape;
	}

	public void droppedCurPiece() {
		for (int i = 0; i < 4; ++i) {
			int x = curPiece.getCurX() + curPiece.x(i);
			int y = curPiece.getCurY() - curPiece.y(i);
			board[(y * BoardWidth) + x] = curPiece.getShape();
		}
		removeFullLines();

		if(!isFallingFinished)
			newPiece();
	}

	private void initNewPiecePosition(){
		curPiece.setCurX(BoardWidth / 2);
		curPiece.setCurY(BoardHeight - 1 + curPiece.minY());

		if (!tryMoveCurPiece(curPiece, curPiece.getCurX(), curPiece.getCurY())) {
			gameOver();
		}
	}

	public void newPiece() {
		Tetrominoes newShape = blockGenerator.generateTetrominoes();
		curPiece.initShape(newShape);
		initNewPiecePosition();
		updateBlockBox();
		isHolding = false;
	}

	private void newPiece(Tetrominoes shape) {
		curPiece.initShape(shape);
		initNewPiecePosition();
		updateBlockBox();
	}

	private boolean canMove(Shape newPiece, int newX, int newY){
		for (int i = 0; i < 4; ++i) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
				return false;
			if (shapeAt(x, y) != Tetrominoes.NoShape)
				return false;
		}
		return true;
	}

	private boolean tryMoveCurPiece(Shape newPiece, int newX, int newY) {
		if(!canMove(newPiece, newX, newY)){
			return false;
		}
		newPiece.setCurX(newX);
		newPiece.setCurY(newY);
		curPiece.copyShape(newPiece);
		updateGhostPiece();
		repaint();
		return true;
	}

	private boolean tryMovePiece(Shape newPiece, int newX, int newY){
		if(!canMove(newPiece, newX, newY)){
			return false;
		}
		newPiece.setCurX(newX);
		newPiece.setCurY(newY);
		repaint();
		return true;
	}


	private boolean isLineFull(int lineNumber) {
		for (int j = 0; j < BoardWidth; ++j) {
			if (shapeAt(j, lineNumber) == Tetrominoes.NoShape) {
				return false;
			}
		}
		return true;
	}

	private void removeLine(int lineNumber) {
		for (int k = lineNumber; k < BoardHeight - 1; ++k) {
			for (int j = 0; j < BoardWidth; ++j)
				board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
		}
	}

	public int removeFullLines() {
		int numFullLines = 0;

		for (int i = BoardHeight - 1; i >= 0; --i) {
			if (isLineFull(i)) {
				++numFullLines;
				removeLine(i);
			}
		}
		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
			curPiece.initShape(Tetrominoes.NoShape);
			repaint();
		}
		return numFullLines;
	}

	public int getNumLinesRemoved(){
		return numLinesRemoved;
	}


	public void rotateRightCurPiece(){
		boolean canRotate = false;
		Shape piece = curPiece;
		int currentX = curPiece.getCurX();
		int currentY = curPiece.getCurY();
		if(piece.getShape() == Tetrominoes.SquareShape){
			return;
		}
		if (piece.getShape() == Tetrominoes.LineShape) {
			for(int i = 0; i < 5; i++){
				int moveX = piece.IShapeSrsKick[piece.getRotateIndex()][i][0];
				int moveY = piece.IShapeSrsKick[piece.getRotateIndex()][i][1];
				canRotate = tryMoveCurPiece(piece.rotateRight(),currentX + moveX, currentY + moveY);
				if(canRotate)	break;
			}
		} else {
			for(int i = 0; i < 5; i++){
				int moveX = piece.srsKick[piece.getRotateIndex()][i][0];
				int moveY = piece.srsKick[piece.getRotateIndex()][i][1];
				canRotate = tryMoveCurPiece(piece.rotateRight(), currentX + moveX, currentY + moveY);
				if(canRotate)	break;
			}
		}
		System.out.println(curPiece.getRotateIndex());
	}

	public void rotateLeftCurPiece(){
		boolean canRotate = false;
		Shape piece = curPiece;
		int currentX = curPiece.getCurX();
		int currentY = curPiece.getCurY();
		if(piece.getShape() == Tetrominoes.SquareShape){
			return;
		}
		if (piece.getShape() == Tetrominoes.LineShape) {
			for(int i = 0; i < 5; i++){
				int moveX = piece.IShapeSrsKick[piece.getRotateIndex()+4][i][0];
				int moveY = piece.IShapeSrsKick[piece.getRotateIndex()+4][i][1];
				canRotate = tryMoveCurPiece(piece.rotateLeft(),currentX + moveX, currentY + moveY);
				if(canRotate)	break;
			}
		} else {
			for(int i = 0; i < 5; i++){
				int moveX = piece.srsKick[piece.getRotateIndex()+4][i][0];
				int moveY = piece.srsKick[piece.getRotateIndex()+4][i][1];
				canRotate = tryMoveCurPiece(piece.rotateLeft(), currentX + moveX, currentY + moveY);
				if(canRotate)	break;
			}
		}
		System.out.println(curPiece.getRotateIndex());
	}
	@Override
	public void drawSquare(Graphics g, int x, int y, Tetrominoes shape) {
		Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
				new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
				new Color(218, 170, 0), new Color(59, 59, 59)};

		Color color = colors[shape.ordinal()];

		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}

	public void gameOver(){
		curPiece.initShape(Tetrominoes.NoShape);
		controller.stop();
		gameTimer.pause();
		isStarted = false;
	}
	public void gameClear(){
		curPiece.initShape(Tetrominoes.NoShape);
		controller.stop();
		gameTimer.pause();
		isStarted = false;
	}
	public boolean getIsStarted(){
		return isStarted;
	}
	public void setTimer(GameTimer gameTimer){
		this.gameTimer = gameTimer;
	}
	public void setBlockGenerator(BlockGenerator blockGenerator){
		this.blockGenerator = blockGenerator;
	}
	public BlockGenerator getBlockGenerator(){
		return  blockGenerator;
	}
	public void updateBlockBox(){
		Tetrominoes next1;
		Tetrominoes next2;
		Tetrominoes next3;
		Tetrominoes hold;

		next1 = blockGenerator.getFirstNextTetrominoes();
		next2 = blockGenerator.getSecondNextTetrominoes();
		next3 = blockGenerator.getThirdnextTetrominoes();
		hold = this.hold;

		BlockBoxData blockBoxData = new BlockBoxData();
		blockBoxData.setNext1(next1);
		blockBoxData.setNext2(next2);
		blockBoxData.setNext3(next3);
		blockBoxData.setHold(hold);

		blockBoxPanel.updateBlockBox(blockBoxData);
	}

	private void updateGhostPiece(){
		ghostPiece.copyShape(curPiece);
		dropDownPiece(ghostPiece);
	}

	public void holdPiece(){
		if(isHolding) return;

		if(hold == Tetrominoes.NoShape) {
			hold = curPiece.getShape();
			newPiece();
			isHolding = true;
			return;
		}

		Tetrominoes holdTmp = hold;
		hold = curPiece.getShape();
		newPiece(holdTmp);
		isHolding = true;
	}

	public GameTimer getTimer(){
		return gameTimer;
	}

	public GameController getController() {
		return controller;
	}

	public abstract void updateTimerLabel(String time);

	class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

			if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {
				return;
			}

			int keycode = e.getKeyCode();

			if (keycode == 'p' || keycode == 'P') {
				pause();
				return;
			}

			if (isPaused)
				return;

			switch (keycode) {
				case KeyEvent.VK_LEFT:
					tryMoveCurPiece(curPiece, curPiece.getCurX() - 1, curPiece.getCurY());
					break;
				case KeyEvent.VK_RIGHT:
					tryMoveCurPiece(curPiece, curPiece.getCurX() + 1, curPiece.getCurY());
					break;
				case KeyEvent.VK_DOWN:
					rotateRightCurPiece();
					break;
				case KeyEvent.VK_UP:
					rotateLeftCurPiece();
					break;
				case KeyEvent.VK_SPACE:
					dropDownCurPiece();
					break;
				case 'd', 'D':
					oneLineDownCurPiece();
					break;
				case 'h', 'H':
					holdPiece();
					break;
				case 'p', 'P':
					pause();
					break;
				default:
					break;
			}
		}
	}
}