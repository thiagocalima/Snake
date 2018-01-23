package domain;

import java.awt.Image;

import javax.swing.ImageIcon;

import snake.Board;

//public class Head extends Dot implements DotElement {
public class Head extends Dot {
	private Image image;
	
	public Head(int x, int y) {
		super(x, y);
		
		ImageIcon iia = new ImageIcon(this.getClass().getResource("head.png"));
		image = iia.getImage();
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image i) {
		this.image = i;
	}
	
	public void colide(Body b, Board o) {
		if((this.getPosX() == b.getPosX()) && (this.getPosY() == b.getPosY())) {
			o.endGame();			
		}	
	}
	
	public void colide(Apple a, Board o) {
		if((this.getPosX() == a.getPosX()) && (this.getPosY() == a.getPosY())) {
			o.incrementSnake();
			a.randomizePos();
		}	
	}
	
	public void colide(Bonus b, Board o) {
		if((this.getPosX() == b.getPosX()) && (this.getPosY() == b.getPosY())) {
			b.setPosX(-10);
			b.setPosY(-10);
			
			o.incrementScore(10);
		}
	}

	public void accept(Head h, Board o) { }
	public void accept(Dot d, Board o) { }
}