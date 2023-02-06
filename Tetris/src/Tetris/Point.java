package Tetris;

public class Point {
	private int x;
	private int y;
	
	public Point() {
		x = 999;
		y = 999;
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point getPoint() {
		return this;
	}
}
