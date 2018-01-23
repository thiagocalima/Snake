package domain;

import java.awt.Image;

import javax.swing.ImageIcon;

import snake.Board;

//public class Body extends Dot implements DotElement {
public class Body extends Dot {
	private Image image;
	
	public Body(int x, int y) {
		super(x, y);
		
		ImageIcon iia = new ImageIcon(this.getClass().getResource("dot.png"));
		image = iia.getImage();
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image i) {
		this.image = i;
	}
	
	public void accept(Dot d, Board o) {
		d.colide(this, o);
	}

	public void accept(Head h, Board o) { }
	public void colide(Apple a, Board o) { }
	public void colide(Body b, Board o) { }
	public void colide(Bonus b, Board o) { }
}