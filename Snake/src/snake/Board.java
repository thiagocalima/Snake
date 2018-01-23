package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import domain.Apple;
import domain.Body;
import domain.Bonus;
import domain.Dot;
import domain.Head;
import domain.Score;


public class Board extends JPanel implements ActionListener {

	private final int boardWidth = 300;
	private final int boardHeight = 300;
	private final int dotSize = 10;
	private Score score = new Score();
	private Timer timer;
	private int count = 0;
	/* 
	 * Aqui esta o comeco do Strategy.
	 */
	private Context context; 

	/* 
	 * Aqui esta acontecendo o Polimorfismo Universal Parametrico porque estamos parametrizando 
	 * o ArrayList para o tipo Dot. Sem o tipo Dot o ArrayList em si n‹o existiria.
	 */
	private ArrayList<Dot> dot = new ArrayList<Dot>();
	private Apple apple = new Apple(0, 0);
	private Bonus bonus = new Bonus(-10, -10);
	
	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;
	private boolean begin = false;
	private boolean inGame = true;
	private boolean pause = false;
	
	
	public Board() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
	}


	public int getBoardWidth() {
		return this.boardWidth;
	}


	public int getBoardHeight() {
		return this.boardHeight;
	}
	
	
	public int getDotSize() {
		return this.dotSize;
	}
	
	
	public void initGame() {

		/*
		 * Aqui e um lugar onde est‡ acontecendo o Polimorfismo Universal por Inclusao. Isso so e 
		 * possivel porque Dot e abstract e tem o metodo getImage Abstract
		 */
		for (int z = 0; z < 3; z++) {
			if (z == 0) {
				dot.add(new Head(50 - z * 10, 50));
			} else {
				dot.add(new Body(50 - z * 10, 50));
			}
		}

		apple.randomizePos();
		
		timer = new Timer(140, this);
		timer.start();
	}


	public void paint(Graphics g) {
		super.paint(g);

		if ((inGame) && (begin)) {
			g.drawImage(apple.getImage(), apple.getPosX(), apple.getPosY(), this);
			g.drawImage(bonus.getImage(), bonus.getPosX(), bonus.getPosY(), this);

			/*
			 * Aqui e outro lugar onde esta acontecendo o Polimorfismo Universal por Inclusao. Isso so e 
			 * possivel porque Dot e abstract e tem o metodo getImage Abstract
			 */
			for (int z = 0; z < dot.size(); z++) {
				g.drawImage(dot.get(z).getImage(), dot.get(z).getPosX(), dot.get(z).getPosY(), this);
			}

			Toolkit.getDefaultToolkit().sync();
			drawScore(g);			
			g.dispose();
		} else if ((!inGame) && (begin)){
			drawGameOver(g);
		} else {
			mainScreen(g);
		}
	}

	
	public void mainScreen(Graphics g) {
		Font title = new Font("Helvetica", Font.BOLD, 32);
		FontMetrics metrTitle = this.getFontMetrics(title);
		
		Font text = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metrLine1 = this.getFontMetrics(text);
		FontMetrics metrLine2 = this.getFontMetrics(text);
		FontMetrics metrLine3 = this.getFontMetrics(text);
		FontMetrics metrLine4 = this.getFontMetrics(text);
		FontMetrics metrLine5 = this.getFontMetrics(text);
		
		String str = "Snake!";
		
		String line1 = "Instructions:";
		String line2 = "Use the arrows to move the Snake;";
		String line3 = "Press 'S' to Start;";
		String line4 = "Press 'P' to Pause;";
		String line5 = "Press 'Q' to Quit.";

		g.setColor(Color.white);
		g.setFont(title);
		g.drawString(str, (boardWidth - metrTitle.stringWidth(str)) / 2, boardHeight / 4);
		
		g.setFont(text);
		g.drawString(line1, (boardWidth - metrLine1.stringWidth(line1)) / 2, boardHeight / 2);
		g.drawString(line2, (boardWidth - metrLine2.stringWidth(line2)) / 2, (boardHeight / 2) + 20);
		g.drawString(line3, (boardWidth - metrLine3.stringWidth(line3)) / 2, (boardHeight / 2) + 40);
		g.drawString(line4, (boardWidth - metrLine4.stringWidth(line4)) / 2, (boardHeight / 2) + 60);
		g.drawString(line5, (boardWidth - metrLine5.stringWidth(line5)) / 2, (boardHeight / 2) + 80);
	}
	
	
	public void drawGameOver(Graphics g) {
		String str = Integer.toString(score.getScore());
		String msg = "Game Over. Score: " + str;
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (boardWidth - metr.stringWidth(msg)) / 2, boardHeight / 2);
	}

	
	public void drawScore(Graphics g) {
		String str = Integer.toString(score.getScore());
		String msg = "Score: " + str;
		Font small = new Font("Helvetica", Font.BOLD, 14);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (int) (boardWidth - 295), (int) (boardHeight - 285));
	}


	public void move() {		
		for (int z = (dot.size() - 1); z > 0; z--) {
			dot.get(z).setPosX(dot.get(z-1).getPosX());
			dot.get(z).setPosY(dot.get(z-1).getPosY());
		}

		if (left) {
			dot.get(0).setPosX(dot.get(0).getPosX() - dotSize);
		} else if (right) {
			dot.get(0).setPosX(dot.get(0).getPosX() + dotSize);
		} else if (up) {
			dot.get(0).setPosY(dot.get(0).getPosY() - dotSize);
		} else if (down) {
			dot.get(0).setPosY(dot.get(0).getPosY() + dotSize);
		}
	}


	public void checkCollision() {
		
		/* 
		 * Aqui esta acontecendo o Visitor - Head visitando uma Maca
		 */
		apple.accept(dot.get(0), this);
		
		/*
		 * Aqui esta acontecendo o Visitor - Head visitando um Bonus
		 */
		bonus.accept(dot.get(0), this);
		
		for (int z = (dot.size() - 1); z > 0; z--)
			/*
			 * Aqui esta acontecendo o Visitor - Head visitando um Body
			 */
			dot.get(z).accept(dot.get(0), this);

		/* 
		 * Aqui estou chamado o Strategy para cuidar das colisoes com a Parede. De acordo com a estrategia vigente
		 */
		context.executeStrategy(dot.get(0), this);
	}


	public void incrementSnake() {
		incrementScore();
		dot.add(new Body(dot.get(dot.size() - 1).getPosX(), dot.get(dot.size() - 1).getPosY()));
		timer.setDelay((int) (timer.getDelay() - (timer.getDelay() * .025)));
	}
	
	
	public void incrementScore() {
		this.incrementScore(1);
	}

	
	public void incrementScore(int i) {
		score.incrementScore(i);
	}
	
	
	public void endGame() {
		inGame = false;
	}
	
	
	public boolean decideBonus() {
		count--;
		
		if (((int) (Math.random() * 50) == 7) && (bonus.getPosX() == -10) && (bonus.getPosY() == -10)) {
			count = 50;
			return true;
		} else if (count == 0) {
			bonus.setPosX(-10);
			bonus.setPosY(-10);
		}

		return false;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (inGame) {
			if(decideBonus())
				bonus.randomizePos();

			checkCollision();
			move();
		}
		
		repaint();
	}


	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if ((key == KeyEvent.VK_S) && (!begin)) {
				/* 
				 * Aqui esta acontecendo o Strategy - Definindo estrategia de colisao com parede
				 */
				context = new Context(new ConcreteStrategyRealWall());
				begin = true;
				initGame();
			} else if ((key == KeyEvent.VK_F) && (!begin)) {
				/*
				 * Aqui esta acontecendo o Strategy - Definindo estrategia sem colisao com parede
				 */
				context = new Context(new ConcreteStrategyFakeWall());
				begin = true;
				initGame();
			} else if ((key == KeyEvent.VK_Q) && (!pause)) {
				endGame();
			} else if ((key == KeyEvent.VK_P) && (!pause)) {
				timer.stop();
				pause = true;
			} else if ((key == KeyEvent.VK_P) && (pause)) {
				timer.start();
				pause = false;
			} else if ((key == KeyEvent.VK_LEFT) && (!right) && (!pause)) {
				left = true;
				up = false;
				down = false;
			} else if ((key == KeyEvent.VK_RIGHT) && (!left) && (!pause)) {
				right = true;
				up = false;
				down = false;
			} else if ((key == KeyEvent.VK_UP) && (!down) && (!pause)) {
				up = true;
				right = false;
				left = false;
			} else if ((key == KeyEvent.VK_DOWN) && (!up) && (!pause)) {
				down = true;
				right = false;
				left = false;
			}
		}
	}
}