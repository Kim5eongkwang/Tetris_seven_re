package org.example.entity;

import org.example.data.Tetrominoes;

public class Shape {

	private Tetrominoes pieceShape;
	private int coords[][];
	private int curX = 0;
	private int curY = 0;
	public static final int[][][] coordsTable = new int[][][]{
		{{0, 0}, {0, 0}, {0, 0}, {0, 0}},
		{{-1, -1}, {0, -1}, {0, 0}, {1, 0}},    //ZShape
		{{-1, 0}, {0, 0}, {0, -1}, {1, -1}},    //SShape
		{{-1, 0}, {0, 0}, {1, 0}, {2, 0}},      //LineShape
		{{-1, 0}, {0, 0}, {1, 0}, {0, -1}},     //TShape
		{{0, 0}, {1, 0}, {0, -1}, {1, -1}},     //squareShape
		{{-1, -1}, {-1, 0}, {0, 0}, {1, 0}},    //LShape,
		{{-1, 0}, {0, 0}, {1, 0}, {1, -1}},     //MirroredLShape
		{{0, 0}}    							//TutorialBackground
	};
	private int rotateIndex = 0;
	public final int[][][] srsKick = new int[][][]{    //증가는 right로테이션 감소는 left로테이션, x, y순서임
			{{0,0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},   //0>>1
			{{0,0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},       //1>>2
			{{0,0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},      //2>>3
			{{0,0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}},    //3>>0
			{{0,0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},      //0>>3
			{{0,0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},       //1>>0
			{{0,0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},   //2>>1
			{{0,0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}},    //3>>2
	};

	public final int[][][] IShapeSrsKick = new int[][][]{
			{{0,0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}},     //0>>1
			{{0,0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}},     //1>>2
			{{0,0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},     //2>>3
			{{0,0}, {1, 0}, {-2, 0}, {1, -2}, {-2, 1}},     //3>>0
			{{0,0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}},     //0>>3
			{{0,0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},     //1>>0
			{{0,0}, {1, 0}, {-2, 1}, {1, -2}, {-2, 1}},     //2>>1
			{{0,0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}}      //3>>2
	};


	public Shape() {
		coords = new int[4][2];
		initShape(Tetrominoes.NoShape);
	}

	public void initShape(Tetrominoes shape) {
		for(int i = 0; i < 4; i++) {
			System.arraycopy(coordsTable[shape.ordinal()][i], 0, coords[i], 0, 2);
		}
		pieceShape = shape;
		this.rotateIndex = 0;
	}

	public void copyShape(Shape shape){
		pieceShape = shape.pieceShape;
		for(int i = 0; i < 4; i++){
			System.arraycopy(shape.coords[i],0,coords[i],0,2);
		}
		setRotateIndex(shape.getRotateIndex());
		setCurX(shape.getCurX());
		setCurY(shape.getCurY());
	}

	public void setRotateIndex(int num){
		if(num <= 4 && num > 0){	//예외 처리 부분
			rotateIndex = num;
			return;
		}
		rotateIndex = 0;
	}

	public int getRotateIndex() {
		return rotateIndex;
	}

	public void pluseRotateIndex(){
		rotateIndex++;
		rotateIndex %= 4;
	}

	public void minusRotateindex(){
		this.rotateIndex = (3 + this.rotateIndex) % 4;
	}

	private void setX(int index, int x) {
		coords[index][0] = x;
	}

	private void setY(int index, int y) {
		coords[index][1] = y;
	}

	public int x(int index) {
		return coords[index][0];
	}

	public int y(int index) {
		return coords[index][1];
	}

	public Tetrominoes getShape() {
		return pieceShape;
	}

	public int minX() {
		int m = coords[0][0];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][0]);
		}
		return m;
	}

	public int minY() {
		int m = coords[0][1];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][1]);
		}
		return m;
	}

	public Shape rotateLeft() {
		if (pieceShape == Tetrominoes.SquareShape)
			return this;

		Shape result = new Shape();
		result.copyShape(this);

		for (int i = 0; i < 4; ++i) {
			result.setX(i, y(i));
			result.setY(i, -x(i));
		}
		result.minusRotateindex();
		return result;
	}

	public Shape rotateRight() {
		if (pieceShape == Tetrominoes.SquareShape)
			return this;

		Shape result = new Shape();
		result.copyShape(this);

		for (int i = 0; i < 4; ++i) {
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}
		result.pluseRotateIndex();
		return result;
	}

	public void setCurX(int x){
		curX = x;
	}

	public void setCurY(int y){
		curY = y;
	}

	public int getCurX(){
		return curX;
	}

	public int getCurY(){
		return curY;
	}

}