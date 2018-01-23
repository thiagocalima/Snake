package domain;

import java.awt.Image;

import javax.swing.ImageIcon;

import snake.Board;

//public class Bonus extends Dot implements DotElement {
public class Bonus extends Dot {
	private Image image;
	
	public Bonus(int x, int y) {
		super(x, y);
		
		ImageIcon iia = new ImageIcon(this.getClass().getResource("bonus.png"));
		image = iia.getImage();
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image i) {
		this.image = i;
	}
	
	public void randomizePos(){ //O randomico tem que ser 29 (300 pixels / 10 para formar a malha - 1 ([0-29] = 30). Depois disso precisa multiplicar por 10 para cair em um lugar por onde a Snake vai andar
		final int dotSize = 10;
		final int randPos = 29;
		
		this.setPosX(((int) (Math.random() * randPos)) * dotSize);
		this.setPosY(((int) (Math.random() * randPos)) * dotSize);
	}
	
	public void accept(Dot d, Board o) {
		d.colide(this, o);
	}
	
	public void accept(Head h, Board o) { }
	public void colide(Apple a, Board o) { }
	public void colide(Body b, Board o) { }
	public void colide(Bonus b, Board o) { }
}