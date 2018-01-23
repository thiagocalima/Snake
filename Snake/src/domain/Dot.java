package domain;

import java.awt.Image;

import snake.Board;

//public abstract class Dot implements DotElement {
public abstract class Dot {
	private int posX;
	private int posY;
	
	public Dot (int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return this.posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public abstract Image getImage();
	
	public abstract void accept(Dot d, Board o);
	public abstract void colide(Apple a, Board o);
	public abstract void colide(Body b, Board o);
	public abstract void colide(Bonus b, Board o);
}